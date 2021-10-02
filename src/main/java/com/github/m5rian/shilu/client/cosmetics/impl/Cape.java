package com.github.m5rian.shilu.client.cosmetics.impl;

import com.github.m5rian.shilu.client.Client;
import com.github.m5rian.shilu.client.cosmetics.Cosmetic;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class Cape extends Cosmetic {
    final ResourceLocation texture = new ResourceLocation("client/cosmetics/capes/m5rian/texture/00.png");

    @Override
    public void renderCosmetic(AbstractClientPlayer player, float p_177141_2_, float p_177141_3_, float partialTicks, float p_177141_5_, float p_177141_6_, float p_177141_7_, float scale) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        Client.playerRenderer.bindTexture(texture);
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.0F, 0.0F, 0.125F);
        double d0 = player.prevChasingPosX + (player.chasingPosX - player.prevChasingPosX) * (double) partialTicks - (player.prevPosX + (player.posX - player.prevPosX) * (double) partialTicks);
        double d1 = player.prevChasingPosY + (player.chasingPosY - player.prevChasingPosY) * (double) partialTicks - (player.prevPosY + (player.posY - player.prevPosY) * (double) partialTicks);
        double d2 = player.prevChasingPosZ + (player.chasingPosZ - player.prevChasingPosZ) * (double) partialTicks - (player.prevPosZ + (player.posZ - player.prevPosZ) * (double) partialTicks);
        float f = player.prevRenderYawOffset + (player.renderYawOffset - player.prevRenderYawOffset) * partialTicks;
        double d3 = (double) MathHelper.sin(f * (float) Math.PI / 180.0F);
        double d4 = (double) (-MathHelper.cos(f * (float) Math.PI / 180.0F));
        float f1 = (float) d1 * 10.0F;
        f1 = MathHelper.clamp_float(f1, -6.0F, 32.0F);
        float f2 = (float) (d0 * d3 + d2 * d4) * 100.0F;
        float f3 = (float) (d0 * d4 - d2 * d3) * 100.0F;

        if (f2 < 0.0F) {
            f2 = 0.0F;
        }

        float f4 = player.prevCameraYaw + (player.cameraYaw - player.prevCameraYaw) * partialTicks;
        f1 = f1 + MathHelper.sin((player.prevDistanceWalkedModified + (player.distanceWalkedModified - player.prevDistanceWalkedModified) * partialTicks) * 6.0F) * 32.0F * f4;

        if (player.isSneaking()) {
            f1 += 25.0F;
        }

        GlStateManager.rotate(6.0F + f2 / 2.0F + f1, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(f3 / 2.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(-f3 / 2.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
        Client.playerRenderer.getMainModel().renderCape(0.0625F);
        GlStateManager.popMatrix();
    }
}
