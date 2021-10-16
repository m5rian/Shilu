package com.github.m5rian.shilu.mixins.client.gui;

import com.github.m5rian.shilu.client.CustomKeyBind;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiControls;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiControls.class)
public class MixinGuiControls {

    @Inject(method = "actionPerformed", at = @At("HEAD"))
    private void onIdk(GuiButton button, CallbackInfo ci) {
        CustomKeyBind.save();
    }

}
