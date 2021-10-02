package com.github.m5rian.shilu.client.cosmetics.impl;

import com.github.m5rian.shilu.client.cosmetics.Cosmetic;
import com.github.m5rian.shilu.client.cosmetics.CosmeticData;
import com.github.m5rian.shilu.client.cosmetics.CosmeticType;
import com.github.m5rian.shilu.client.utilities.obj.Loader;
import com.github.m5rian.shilu.client.utilities.obj.Model;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@CosmeticData(
        type = CosmeticType.HEAD,
        obj = "client/cosmetics/dogFilter/dogFilter.obj",
        texture = ""
)
public class DogFilter extends Cosmetic {

    /*
    private class ModelDogFilter extends ModelBase {
        private final ModelRenderer dogFilter;

        private final ResourceLocation texture = new ResourceLocation("client/cosmetics/dogFilter/dogFilter.png");

        public ModelDogFilter() {
            final ModelRenderer modelRenderer = new ModelRenderer(this);
            modelRenderer.

            /*
            textureWidth = 32;
            textureHeight = 32;

            dogFilter = new ModelRenderer(this);
            dogFilter.setRotationPoint(0.0F, 24.0F, 0.0F);

            final ModelRenderer earLeft = new ModelRenderer(this);
            earLeft.setRotationPoint(-4.0F, -33.0F, -4.0F);
            earLeft.setTextureOffset(14, 2).addBox(-1.0F, -2.0F, 1.0F, 2, 2, 1);
            earLeft.setTextureOffset(7, 11).addBox(-1.0F, -1.0F, -2.0F, 2, 2, 1);
            earLeft.setTextureOffset(0, 6).addBox(-1.0F, -2.0F, -1.0F, 2, 1, 2);
            earLeft.setTextureOffset(9, 6).addBox(-1.0F, -1.0F, 2.0F, 2, 3, 1);

            final ModelRenderer earRight = new ModelRenderer(this);
            earRight.setRotationPoint(4.0F, -31.0F, -4.0F);
            earRight.setTextureOffset(0, 15).addBox(-2.0F, -4.0F, 0.0F, 2, 2, 1);
            earRight.setTextureOffset(14, 11).addBox(-2.0F, -3.0F, -3.0F, 2, 2, 1);
            earRight.setTextureOffset(7, 15).addBox(-2.0F, -4.0F, -2.0F, 2, 1, 2);
            earRight.setTextureOffset(0, 10).addBox(-2.0F, -3.0F, 1.0F, 2, 3, 1);

            final ModelRenderer mouth = new ModelRenderer(this);
            mouth.setRotationPoint(0.0F, 0.0F, 0.0F);
            mouth.setTextureOffset(0, 3).addBox(-2.0F, -27.0F, -5.0F, 4, 1, 1);
            mouth.setTextureOffset(0, 0).addBox(-3.0F, -26.0F, -5.0F, 6, 1, 1);
            mouth.setTextureOffset(11, 3).addBox(-1.0F, -25.0F, -5.0F, 1, 2, 0);

            dogFilter.addChild(earLeft);
            dogFilter.addChild(earRight);
            dogFilter.addChild(mouth);
             * /
                    textureWidth = 32;
            textureHeight = 32;

            dogFilter = new ModelRenderer(this);
            dogFilter.setRotationPoint(0.0F, 24.0F, 0.0F);


            final ModelRenderer earLeft = new ModelRenderer(this);
            earLeft.setRotationPoint(-4.0F, -33.0F, -4.0F);
            dogFilter.addChild(earLeft);
            earLeft.setTextureOffset(14, 2).addBox(-1.0F, -2.0F, 1.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
            earLeft.setTextureOffset(7, 11).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
            earLeft.setTextureOffset(0, 6).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
            earLeft.setTextureOffset(9, 6).addBox(-1.0F, -1.0F, 2.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);

            final ModelRenderer earRight = new ModelRenderer(this);
            earRight.setRotationPoint(4.0F, -31.0F, -4.0F);
            dogFilter.addChild(earRight);
            setRotationAngle(earRight, -0.0436F, 0.0436F, 0.4363F);
            earRight.rotateAngleX = -0.0436F;
            earRight.rotateAngleY = 0.0436F;
            earRight.rotateAngleZ = 0.4363F;

            earRight.setTextureOffset(0, 15).addBox(-2.0F, -4.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
            earRight.setTextureOffset(14, 11).addBox(-2.0F, -3.0F, -3.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
            earRight.setTextureOffset(7, 15).addBox(-2.0F, -4.0F, -2.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
            earRight.setTextureOffset(0, 10).addBox(-2.0F, -3.0F, 1.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);

            final ModelRenderer Mouth = new ModelRenderer(this);
            Mouth.setRotationPoint(0.0F, 0.0F, 0.0F);
            dogFilter.addChild(Mouth);
            Mouth.setTextureOffset(0, 3).addBox(-2.0F, -27.0F, -5.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);
            Mouth.setTextureOffset(0, 0).addBox(-3.0F, -26.0F, -5.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);
            Mouth.setTextureOffset(11, 3).addBox(-1.0F, -25.0F, -5.0F, 1.0F, 2.0F, 0.0F, 0.0F, false);
        }
    }
*/

}
