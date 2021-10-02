package com.github.m5rian.shilu.client.mods.impl;

import com.github.m5rian.shilu.client.hud.ScreenPosition;
import com.github.m5rian.shilu.client.mods.ModDraggable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

import java.util.Arrays;

public class ArmourStatus extends ModDraggable {
    @Override
    public int getWidth() {
        return 64;
    }

    @Override
    public int getHeight() {
        return 64;
    }

    @Override
    public void render(ScreenPosition position) {
        final EntityPlayerSP player = Minecraft.getMinecraft().thePlayer; // Get the player
        final ItemStack[] equippedArmour = player.inventory.armorInventory; // Get equipped armour of player

        for (int i = 0; i < equippedArmour.length; i++) {
            renderItem(position, ArmourPiece.getByIndex(i), equippedArmour[i]);
        }

    }

    @Override
    public void renderDummy(ScreenPosition position) {
        renderItem(position, ArmourPiece.BOOTS, new ItemStack(Items.diamond_boots));
        renderItem(position, ArmourPiece.LEGGINGS, new ItemStack(Items.diamond_leggings));
        renderItem(position, ArmourPiece.CHESTPLATE, new ItemStack(Items.diamond_chestplate));
        renderItem(position, ArmourPiece.HELMET, new ItemStack(Items.diamond_helmet));
    }

    private void renderItem(ScreenPosition position, ArmourPiece armour, ItemStack item) {
        if (item == null) {
            return;
        }

        GL11.glPushMatrix();
        int yAdd = (-16 * armour.getIndex()) + 48;

        // Item can be damaged
        if (item.getItem().isDamageable()) {
            final int damage = item.getMaxDamage() - item.getItemDamage(); // Get item damage
            final double damagePercent = damage / (double) item.getMaxDamage() * 100; // Get item damage as percent

            fontRenderer.drawString(String.format("%.2f%%", damagePercent), position.getAbsoluteX() + 20, position.getAbsoluteY() + yAdd + 5, -1);
        }

        RenderHelper.enableGUIStandardItemLighting();
        Minecraft.getMinecraft().getRenderItem().renderItemIntoGUI(item, position.getAbsoluteX(), position.getAbsoluteY() + yAdd);
        GL11.glPopMatrix();
    }

    /**
     * Represents an armour item.
     */
    enum ArmourPiece {
        BOOTS(0),
        LEGGINGS(1),
        CHESTPLATE(2),
        HELMET(3);

        private final int index; // The armour inventory item index

        /**
         * @param index The {@link InventoryPlayer#armorInventory} item index.
         */
        ArmourPiece(int index) {
            this.index = index;
        }

        /**
         * @return Returns the {@link ArmourPiece#index} of the Armour.
         */
        public int getIndex() {
            return this.index;
        }

        /**
         * @param index The index to search for.
         * @return Returns a {@link ArmourPiece}, which has the same index as the {@param index}.
         */
        public static ArmourPiece getByIndex(int index) {
            return Arrays.stream(values()).filter(armour -> armour.getIndex() == index).findFirst().get();
        }
    }
}
