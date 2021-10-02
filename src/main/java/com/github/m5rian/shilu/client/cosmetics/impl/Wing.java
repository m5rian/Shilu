package com.github.m5rian.shilu.client.cosmetics.impl;

import com.github.m5rian.shilu.client.cosmetics.Cosmetic;
import com.github.m5rian.shilu.client.utilities.Maths;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class Wing extends Cosmetic {

    private final ModelWing model = new ModelWing();

    @Override
    public void renderCosmetic(AbstractClientPlayer player, float p_177141_2_, float p_177141_3_, float partialTicks, float p_177141_5_, float p_177141_6_, float p_177141_7_, float s) {
        final float scale = 0.7f;
        final float rotate = 180f + Maths.interpolate(player.prevRenderYawOffset, player.renderYawOffset, partialTicks);
        final float nothing = 0.0f;
        final int speed = 4;

        GlStateManager.pushMatrix();
        GlStateManager.resetColor();
        GlStateManager.scale(-scale, -scale, scale);
        GlStateManager.rotate(rotate, nothing, 1.0f, nothing);
        GlStateManager.translate(nothing, -(1.25 / scale), nothing);
        GlStateManager.translate(nothing, nothing, 0.15f / scale);
        Minecraft.getMinecraft().getTextureManager().bindTexture(model.texture);

        if (player.isSneaking()) GlStateManager.translate(0.0, 0.125 / scale, 0.0);

        for (int i = 0; i < 2; i++) {
            GL11.glEnable(GL11.GL_CULL_FACE);
            final float f11 = System.currentTimeMillis() % 1000L / 1000.0f * speed * (speed - 1);
            model.wing.rotateAngleX = (float) (Math.toRadians(-80.0) - Math.cos(f11) * 0.2f);
            model.wing.rotateAngleY = (float) (Math.toRadians(20.0) + Math.sin(f11) * 0.4f);
            model.wing.rotateAngleZ = (float) Math.toRadians(20.0);
            model.wingTip.rotateAngleZ = (float) ((-(Math.sin(f11 + 2.0f) + 0.5)) * 0.75f);
            model.wing.render(0.0625f);
            GlStateManager.scale(-1.0f, 1.0f, 1.0f);
            GL11.glCullFace(GL11.GL_FRONT);
        }
        GL11.glCullFace(GL11.GL_BACK);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glPopMatrix();
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }

    private class ModelWing extends ModelBase {
        private final ModelRenderer wing = new ModelRenderer(this, "wing");
        private final ModelRenderer wingTip = new ModelRenderer(this, "wingtip");
        private final ResourceLocation texture = new ResourceLocation("client/cosmetics/wing/wing.png");

        public ModelWing() {
            setTextureOffset("wing.bone", 0, 0);
            setTextureOffset("wing.skin", -10, 8);
            setTextureOffset("wingtip.bone", 0, 5);
            setTextureOffset("wingtip.skin", -10, 18);
            wing.setTextureSize(30, 30);
            wing.setRotationPoint(-2.0f, 0.0f, 0.0f);
            wing.addBox("bone", -10.0f, -1.0f, -1.0f, 10, 2, 2);
            wing.addBox("skin", -10.0f, 0.0f, 0.5f, 10, 0, 10);
            wingTip.setTextureSize(30, 30);
            wingTip.setRotationPoint(-10.0f, 0.0f, 0.0f);
            wingTip.addBox("bone", -10.0f, -0.5f, -0.5f, 10, 1, 1);
            wingTip.addBox("skin", -10.0f, 0.0f, 0.5f, 10, 0, 10);
            wing.addChild(wingTip);
        }

    }

}
