package com.github.m5rian.shilu.client.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public class ScreenPosition {
    private double x;
    private double y;

    public ScreenPosition(double x, double y) {
        this.setRelative(x, y);
    }

    public ScreenPosition(int x, int y) {
        this.setAbsolute(x, y);
    }

    public static ScreenPosition fromRelativePosition(double x, double y) {
        return new ScreenPosition(x, y);
    }

    public static ScreenPosition fromAbsolutePosition(double x, double y) {
        return new ScreenPosition(x, y);
    }

    public int getAbsoluteX() {
        final ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        return (int) (this.x * scaledResolution.getScaledWidth_double());
    }

    public int getAbsoluteY() {
        final ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        return (int) (this.y * scaledResolution.getScaledHeight_double());
    }

    public double getRelativeX() {
        return this.x;
    }

    public double getRelativeY() {
        return this.y;
    }

    public void setRelative(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setAbsolute(int x, int y) {
        final ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        this.x = (double) x / scaledResolution.getScaledWidth_double();
        this.y = (double) y / scaledResolution.getScaledHeight_double();
    }
}
