package com.github.m5rian.shilu.client.mods.impl;

import com.github.m5rian.shilu.client.CustomKeyBind;
import com.github.m5rian.shilu.client.events.EventTarget;
import com.github.m5rian.shilu.client.events.impl.PlayerMoveEvent;
import com.github.m5rian.shilu.client.events.impl.PlayerStopEvent;
import com.github.m5rian.shilu.client.events.impl.buttons.KeyPressEvent;
import com.github.m5rian.shilu.client.mods.Mod;
import net.minecraft.client.Minecraft;

public class ToggleSprint extends Mod {
    private boolean toggled = false;

    @EventTarget
    public void onKeyPress(KeyPressEvent event) {
        if (event.getKey() == CustomKeyBind.TOGGLE_SPRINT.getKeyCode()) {
            System.out.println("TOGGLE SPRINT!!!");
            this.toggled = !this.toggled;
        }
    }

    @EventTarget
    public void onPlayerMove(PlayerMoveEvent event) {
        if (isEnabled() && this.toggled) {
            Minecraft.getMinecraft().thePlayer.setSprinting(true);
        }
    }

}
