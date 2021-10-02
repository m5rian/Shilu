package com.github.m5rian.shilu.client.mods.impl;

import com.github.m5rian.shilu.client.hud.ScreenPosition;
import com.github.m5rian.shilu.client.mods.ModDraggable;
import net.minecraft.client.Minecraft;

public class FpsCount extends ModDraggable {
    @Override
    public int getWidth() {
        return 50;
    }

    @Override
    public int getHeight() {
        return this.fontRenderer.FONT_HEIGHT;
    }

    @Override
    public void render(ScreenPosition position) {
        this.fontRenderer.drawString("FPS: " + Minecraft.getDebugFPS(), position.getAbsoluteX(), position.getAbsoluteY(), -1);
    }
}
