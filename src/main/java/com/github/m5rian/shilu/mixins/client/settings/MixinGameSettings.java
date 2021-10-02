package com.github.m5rian.shilu.mixins.client.settings;

import com.github.m5rian.shilu.client.CustomKeyBind;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import org.apache.commons.lang3.ArrayUtils;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;

@Debug(print = true, export = true)
@Mixin(GameSettings.class)
public class MixinGameSettings {

    @Shadow
    public KeyBinding[] keyBindings;

    @Inject(at = @At("RETURN"), method = "<init>()V")
    public void onConstructorWithArgs(CallbackInfo ci) {
        keyBindings = ArrayUtils.addAll(keyBindings, CustomKeyBind.customKeys);
    }

    @Inject(at = @At("RETURN"), method = "<init>(Lnet/minecraft/client/Minecraft;Ljava/io/File;)V")
    public void onConstructorWithoutArgs(Minecraft mc, File p_i46326_2_, CallbackInfo ci) {
        keyBindings = ArrayUtils.addAll(keyBindings, CustomKeyBind.customKeys);
    }

}
