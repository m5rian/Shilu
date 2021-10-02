package com.github.m5rian.shilu.client.cosmetics;

import com.github.m5rian.shilu.client.utilities.obj.Loader;
import com.github.m5rian.shilu.client.utilities.obj.Model;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public abstract class Cosmetic implements LayerRenderer<AbstractClientPlayer> {
    private ModelPlayer playerModel = null;
    private boolean toggled = true;
    private Model model = null;

    public Cosmetic init(ModelPlayer playerModel) {
        this.playerModel = playerModel;
        System.out.println("init called");
        return this;
    }

    public boolean isToggled() {
        return this.toggled;
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;
    }

    @Override
    public void doRenderLayer(AbstractClientPlayer player, float p_177141_2_, float p_177141_3_, float partialTicks, float p_177141_5_, float p_177141_6_, float p_177141_7_, float scale) {
        GL11.glPushMatrix();

        renderCosmetic(player, p_177141_2_, p_177141_3_, partialTicks, p_177141_5_, p_177141_6_, p_177141_7_, scale);

        GL11.glPopMatrix();
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }

    public void renderCosmetic(AbstractClientPlayer player, float p_177141_2_, float p_177141_3_, float partialTicks, float p_177141_5_, float p_177141_6_, float p_177141_7_, float scale) {
        if (this.playerModel == null) throw new NullPointerException("The ModePlayer wasn't initialized yet");
        final CosmeticData data = this.getClass().getDeclaredAnnotation(CosmeticData.class);

        if (model == null) {
            model = Loader.loadModel(new ResourceLocation(data.obj()));
        }
        final float nothing = 0.0f;

        //GlStateManager.translate(nothing, 1f, nothing); // Move to head

        // Rotate with head
        System.out.println("x " + playerModel.bipedHead.rotateAngleX);
        System.out.println("y " + playerModel.bipedHead.rotateAngleY);
        System.out.println("z " + playerModel.bipedHead.rotateAngleZ);
        System.out.println("-----------------------------------------");
        GL11.glRotatef(playerModel.bipedHead.rotateAngleX, 1.0f, nothing, nothing);
        GL11.glRotatef(playerModel.bipedHeadwear.rotateAngleY, nothing, 1.0f, nothing);
        GL11.glRotatef(playerModel.bipedHeadwear.rotateAngleZ, nothing, nothing, 1.0f);

        //GlStateManager.rotate(90f, );
        //GlStateManager.rotate(180f, 1.0f, nothing, nothing);

        model.render();
    }


}
