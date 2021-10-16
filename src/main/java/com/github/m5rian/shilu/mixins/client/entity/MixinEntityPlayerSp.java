package com.github.m5rian.shilu.mixins.client.entity;

import com.github.m5rian.shilu.client.events.impl.PlayerMoveEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayerSP.class)
public class MixinEntityPlayerSp {

    // TODO Doesn't get called
    @Inject(method = "onLivingUpdate", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/util/MovementInput;updatePlayerMoveState()V"))
    public void onLivingUpdate(CallbackInfo ci) {
        System.out.println("Uops");
        new PlayerMoveEvent().call();
    }

}
