package com.github.m5rian.shilu.client.mods;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class Mod {
    private boolean isEnabled = true;

    public final FontRenderer fontRenderer;

    public Mod() {
        this.fontRenderer = Minecraft.getMinecraft().fontRendererObj;
    }

    public void setEnabled(boolean enabled) {
        this.isEnabled = enabled;
    }

    public boolean isEnabled() {
        return this.isEnabled;
    }

}