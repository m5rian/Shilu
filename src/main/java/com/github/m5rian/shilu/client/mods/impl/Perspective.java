package com.github.m5rian.shilu.client.mods.impl;

import com.github.m5rian.shilu.client.CustomKeyBind;
import com.github.m5rian.shilu.client.events.EventTarget;
import com.github.m5rian.shilu.client.events.impl.buttons.KeyPressEvent;
import com.github.m5rian.shilu.client.events.impl.buttons.KeyReleaseEvent;
import com.github.m5rian.shilu.client.mods.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public class Perspective extends Mod {

    /**
     * If true you need to hold the key as long as you want to be in the 360° perspective.
     * If false you need to press it once to get in the 360° perspective and if you press the key again you go out of the 360° perspective.
     */
    private boolean hold = true;
    private boolean toggled = false;

    private float cameraYaw = 0F;
    private float cameraPitch = 0F;
    private int previousPerspective = 0;

    @EventTarget
    public void onKeyPressEvent(KeyPressEvent event) {
        final Minecraft minecraft = Minecraft.getMinecraft();
        final GameSettings settings = minecraft.gameSettings;

        // 360° perspective mod key was pressed
        if (event.getKey() == CustomKeyBind.PERSPECTIVE.getKeyCode()) {
            // The 360° perspective isn't toggled on yet
            if ( !this.toggled) {
                this.toggled = true;
                previousPerspective = settings.thirdPersonView;
                settings.thirdPersonView = 1;
                cameraYaw = minecraft.thePlayer.rotationYaw;
                cameraPitch = minecraft.thePlayer.rotationPitch;
            }
        }

        // F5 was pressed
        if (Keyboard.getEventKey() == settings.keyBindTogglePerspective.getKeyCode() && this.toggled) {
            this.toggled = false; // Disable 360° perspective
        }
    }

    @EventTarget
    public void onKeyReleaseEvent(KeyReleaseEvent event) {
        final GameSettings settings = Minecraft.getMinecraft().gameSettings;

        // 360° perspective mod key was pressed
        if (event.getKey() == CustomKeyBind.PERSPECTIVE.getKeyCode()) {
            // The 360° perspective is toggled on
            if (this.toggled) {
                this.toggled = false;
                settings.thirdPersonView = previousPerspective;
            }
        }
    }

    public float getCameraYaw() {
        return this.toggled ? this.cameraYaw : Minecraft.getMinecraft().thePlayer.rotationYaw;
    }

    public float getCameraPitch() {
        return this.toggled ? this.cameraPitch : Minecraft.getMinecraft().thePlayer.rotationPitch;
    }

    public boolean overrideMouse() {
        //Copied from EntityRenderer
        if (Minecraft.getMinecraft().inGameHasFocus && Display.isActive()) {
            if (!this.toggled) return true;

            Minecraft.getMinecraft().mouseHelper.mouseXYChange();
            float f1 = Minecraft.getMinecraft().gameSettings.mouseSensitivity * 0.6F + 0.2F;
            float f2 = f1 * f1 * f1 * 8.0F;
            float f3 = (float) Minecraft.getMinecraft().mouseHelper.deltaX * f2;
            float f4 = (float) Minecraft.getMinecraft().mouseHelper.deltaY * f2;

            this.cameraYaw += f3 * 0.15F;
            this.cameraPitch += f4 * 0.15F;

            if (this.cameraPitch > 90) this.cameraPitch = 90;
            if (this.cameraPitch < -90) this.cameraPitch = -90;
        }
        return false;
    }

}
