package com.github.m5rian.shilu.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;

public class ModSetting implements GuiListExtended.IGuiListEntry {
    private final ModMenuGui gui;

    public ModSetting(ModMenuGui gui) {
        this.gui = gui;
    }

    @Override
    public void setSelected(int p_178011_1_, int p_178011_2_, int p_178011_3_) {

    }

    @Override
    public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected) {
        this.gui.drawCenteredString(Minecraft.getMinecraft().fontRendererObj, "lol", x, y + 4, -1);
    }

    @Override
    public boolean mousePressed(int slotIndex, int p_148278_2_, int p_148278_3_, int p_148278_4_, int p_148278_5_, int p_148278_6_) {
        return false;
    }

    @Override
    public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {

    }
}
