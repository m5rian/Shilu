package com.github.m5rian.shilu.client.mods.impl;

import com.github.m5rian.shilu.client.hud.ScreenPosition;
import com.github.m5rian.shilu.client.mods.ModDraggable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Display the current using texture pack on the screen including its pack.png
 */
public class PackDisplay extends ModDraggable {
    @Override
    public int getWidth() {
        return 25 * 3;
    }

    @Override
    public int getHeight() {
        return 25;
    }

    @Override
    public void render(ScreenPosition position) {

        try {
            final List<ResourcePackRepository.Entry> packs = Minecraft.getMinecraft().getResourcePackRepository().getRepositoryEntries();
            final IResourcePack pack = packs.size() > 0 ? packs.get(0).getResourcePack() : Minecraft.getMinecraft().getResourcePackRepository().rprDefaultResourcePack;


            DynamicTexture dynamicTexture;
            try {
                dynamicTexture = new DynamicTexture(pack.getPackImage());
            } catch (FileNotFoundException e) {
                final BufferedImage packImage = Minecraft.getMinecraft().getResourcePackRepository().rprDefaultResourcePack.getPackImage();
                dynamicTexture = new DynamicTexture(packImage);
            }
            final ResourceLocation rs = Minecraft.getMinecraft().getTextureManager().getDynamicTextureLocation("packImage", dynamicTexture);

            fontRenderer.drawString(pack.getPackName(), this.position.getAbsoluteX() + 25 + 5, this.position.getAbsoluteY(), -1);

            Minecraft.getMinecraft().getTextureManager().bindTexture(rs);
            Gui.drawModalRectWithCustomSizedTexture(
                    this.position.getAbsoluteX(), this.position.getAbsoluteY(),
                    0, 0,
                    25, 25,
                    25, 25
            );


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
