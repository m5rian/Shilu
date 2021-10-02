package com.github.m5rian.shilu.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class SplashScreen {

    public static void drawSplashScreen(TextureManager textureManager) {
        final ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        final int scaleFactor = scaledResolution.getScaleFactor();

        Framebuffer frameBuffer = new Framebuffer(scaledResolution.getScaledWidth() * scaleFactor, scaledResolution.getScaledHeight() * scaleFactor, true);
        frameBuffer.bindFramebuffer(false);

        GlStateManager.matrixMode(GL11.GL_PROJECTION);
        GlStateManager.loadIdentity();
        GlStateManager.ortho(0.0d, scaledResolution.getScaledWidth(), scaledResolution.getScaledHeight(), 00.0d, 1000.0d, 3000.0d);
        GlStateManager.matrixMode(GL11.GL_MODELVIEW);
        GlStateManager.loadIdentity();
        GlStateManager.translate(0.0f, 0.0f, -2000.0f);
        GlStateManager.disableLighting();
        GlStateManager.disableFog();
        GlStateManager.disableDepth();
        GlStateManager.enableTexture2D();

        final ResourceLocation splash = new ResourceLocation("client/splashScreen/splashScreen.png");
        textureManager.bindTexture(splash);

        Gui.drawScaledCustomSizeModalRect(0, 0, 0, 0, 1920, 1080, scaledResolution.getScaledWidth(), scaledResolution.getScaledHeight(), 1920, 1080);

        frameBuffer.unbindFramebuffer();
        frameBuffer.framebufferRender(scaledResolution.getScaledWidth() * scaleFactor, scaledResolution.getScaledHeight() * scaleFactor);

        Minecraft.getMinecraft().updateDisplay();
    }

}
