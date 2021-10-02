package com.github.m5rian.shilu.mixins.client;

import com.github.m5rian.shilu.client.Client;
import com.github.m5rian.shilu.client.SplashScreen;
import com.github.m5rian.shilu.client.events.impl.ClientTickEvent;
import com.github.m5rian.shilu.client.events.impl.GuiSwitchEvent;
import com.github.m5rian.shilu.client.events.impl.buttons.KeyPressEvent;
import com.github.m5rian.shilu.client.events.impl.buttons.KeyReleaseEvent;
import com.github.m5rian.shilu.client.events.impl.buttons.MouseLeftEvent;
import com.github.m5rian.shilu.client.events.impl.buttons.MouseRightEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.TextureManager;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraft {

    @Inject(method = "startGame", at = @At("HEAD"))
    public void gameStart(CallbackInfo ci) {
        Client.getClient().init();
    }

    @Inject(method = "startGame", at = @At("RETURN"))
    public void gameLoaded(CallbackInfo ci) {
        Client.getClient().start();
    }

    @Inject(method = "createDisplay", at = @At("RETURN"))
    public void createDisplay(CallbackInfo callbackInfo) {
        Display.setTitle(Client.NAME + " | " + Client.VERSION);
    }

    /**
     * @author Marian
     */
    @Overwrite
    private void drawSplashScreen(TextureManager textureManager) {
        SplashScreen.drawSplashScreen(textureManager);
    }


    @Inject(method = "runTick", at = @At("RETURN"))
    public void runTick(CallbackInfo ci) {
        new ClientTickEvent().call();
    }

    @Inject(method = "dispatchKeypresses", at = @At(value = "INVOKE_ASSIGN", target = "Lorg/lwjgl/input/Keyboard;getEventKeyState()Z", remap = false))
    private void runTickKeyboard(CallbackInfo ci) {
        if (Keyboard.getEventKeyState()) {
            new KeyPressEvent(Keyboard.getEventKey()).call();
        } else {
            new KeyReleaseEvent(Keyboard.getEventKey()).call();
        }
    }

    @Inject(method = "clickMouse", at = @At("RETURN"))
    private void leftClickMouseButton(CallbackInfo ci) {
        new MouseLeftEvent().call();
    }

    @Inject(method = "rightClickMouse", at = @At("RETURN"))
    private void rightClickMouseButton(CallbackInfo ci) {
        new MouseRightEvent().call();
    }

    @Inject(method = "displayGuiScreen", at = @At("HEAD"))
    private void displayGuiScreen(GuiScreen newGui, CallbackInfo ci) {
        new GuiSwitchEvent(newGui).call();
    }

}