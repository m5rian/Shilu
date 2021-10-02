package com.github.m5rian.shilu.mixins.client.renderer;

import com.github.m5rian.shilu.client.Client;
import com.github.m5rian.shilu.client.cosmetics.Cosmetic;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import org.apache.commons.io.LineIterator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderPlayer.class)
public abstract class MixinRenderPlayer extends RendererLivingEntity<AbstractClientPlayer> {

    @Shadow public abstract ModelPlayer getMainModel();

    public MixinRenderPlayer(RenderManager renderManagerIn, ModelBase modelBaseIn, float shadowSizeIn) {
        super(renderManagerIn, modelBaseIn, shadowSizeIn);
    }

    @Inject(method = "<init>(Lnet/minecraft/client/renderer/entity/RenderManager;Z)V", at = @At("RETURN"))
    public void init(RenderManager renderManager, boolean useSmallArms, CallbackInfo ci) {
        for (Cosmetic cosmetic : Client.getClient().getCosmeticManager().getCosmetics()) {
            this.addLayer(cosmetic.init(this.getMainModel()));
        }
    }

/*
    @Inject(method = "doRender", at = @At("HEAD"), cancellable = true)
    public void doRender(AbstractClientPlayer entity, double x, double y, double z, float entityYaw, float partialTicks, CallbackInfo callbackInfo) {
        if (this.layerRenderers.size() == 6) {
            if (entity == Minecraft.getMinecraft().thePlayer) {
                for (Cosmetic cosmetic : Client.getClient().getCosmeticManager().getCosmetics()) {
                    this.
                    this.addLayer(cosmetic);
                }
            }
        }
        //if (entity == Minecraft.getMinecraft().thePlayer) Client.getClient().getCosmeticManager().render(entity, x, y, z, partialTicks);
    }*/
}
