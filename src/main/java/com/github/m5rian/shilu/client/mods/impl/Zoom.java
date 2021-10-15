package com.github.m5rian.shilu.client.mods.impl;

import com.github.m5rian.shilu.client.CustomKeyBind;
import com.github.m5rian.shilu.client.events.EventTarget;
import com.github.m5rian.shilu.client.events.impl.ClientTickEvent;
import com.github.m5rian.shilu.client.mods.Mod;
import net.minecraft.client.Minecraft;

public class Zoom extends Mod {
    private static boolean prevIsKeyDown = false;
    private static float savedFOV = 0f;

    @EventTarget
    public void onTick(ClientTickEvent event) {
        boolean isKeyDown = CustomKeyBind.ZOOM.isKeyDown();
        if (prevIsKeyDown != isKeyDown) {
            if (isKeyDown) {
                savedFOV = Minecraft.getMinecraft().gameSettings.fovSetting;
                Minecraft.getMinecraft().gameSettings.fovSetting = 30;
                Minecraft.getMinecraft().gameSettings.smoothCamera = true;
            } else {
                Minecraft.getMinecraft().gameSettings.fovSetting = savedFOV;
                Minecraft.getMinecraft().gameSettings.smoothCamera = false;
            }
        }
        prevIsKeyDown = isKeyDown;
    }

}
