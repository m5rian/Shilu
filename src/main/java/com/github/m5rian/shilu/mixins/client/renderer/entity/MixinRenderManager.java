package com.github.m5rian.shilu.mixins.client.renderer.entity;

import com.github.m5rian.shilu.client.Client;
import com.github.m5rian.shilu.client.cosmetics.impl.TopHat;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.texture.TextureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.sql.SQLOutput;

@Mixin(RenderManager.class)
public class MixinRenderManager {

    @Shadow
    private RenderPlayer playerRenderer;

    @Inject(method = "<init>", at = @At("RETURN"))
    public void init(TextureManager renderEngineIn, RenderItem itemRendererIn, CallbackInfo ci) {
        Client.playerRenderer = playerRenderer;
        System.out.println("DONEEEEEEEEEEEE");
    }

}
