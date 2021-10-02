package com.github.m5rian.shilu.client.cosmetics.impl;


public class TopHat   {
/*
    private final ModelTopHat modelTopHat;
    private static final ResourceLocation TEXTURE = new ResourceLocation("client/cosmetics/hat/hat.png");

    public TopHat(RenderPlayer playerRenderer) {

        new RenderPlayer(Minecraft.getMinecraft().getRenderManager())
        super(playerRenderer);
        modelTopHat = new ModelTopHat(playerRenderer);
    }

    @Override
    public void render(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float headYaw, float headPitch, float scale) {

        if (CosmeticManager.hasTopHat(player)) {
            GL11.glPushMatrix();

            playerRenderer.bindTexture(TEXTURE);
            player.
            // If player is sneaking adjust height of hat
            if (player.isSneaking()) {
                GL11.glTranslated(0, 0.225d, 0);
            }
            final float[] colour = CosmeticManager.getTopHatColour(player);
            GL11.glColor3d(colour[0], colour[1], colour[2]);
            modelTopHat.render(player, limbSwing, limbSwingAmount, ageInTicks, headYaw, headPitch, scale);
            GL11.glColor3f(1, 1, 1);

            GL11.glPopMatrix();
        }
    }

    private class ModelTopHat extends CosmeticModelBase {

        private ModelRenderer rim;
        private ModelRenderer pointy;

        public ModelTopHat(RenderPlayer playerRenderer) {
            super(playerRenderer);

            rim = new ModelRenderer(this.playerModel, 0, 0);
            rim.addBox(-5.5f, -9, -5.5f, 11, 2, 11);

            pointy = new ModelRenderer(this.playerModel, 0, 13);
            pointy.addBox(-3.5f, -17f, -3.5f, 7, 8, 7);
        }

        @Override
        public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch, float scale) {
            rim.rotateAngleX = playerModel.bipedHead.rotateAngleX;
            rim.rotateAngleY = playerModel.bipedHead.rotateAngleY;
            rim.rotationPointX = 0.0f;
            rim.rotationPointY = 0.0f;
            rim.render(scale);


            pointy.rotateAngleX = playerModel.bipedHead.rotateAngleX;
            pointy.rotateAngleY = playerModel.bipedHead.rotateAngleY;
            pointy.rotationPointX = 0.0f;
            pointy.rotationPointY = 0.0f;
            pointy.render(scale);
        }
    }
*/
}
