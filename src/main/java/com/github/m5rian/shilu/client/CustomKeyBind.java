package com.github.m5rian.shilu.client;

import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

/**
 * @author Marian
 * Stores all custom keybindings.
 */
public class CustomKeyBind {
    /**
     * Keybinding to open the mod position gui.
     */
    public static final KeyBinding GUI_MOD_POSITION = new KeyBinding("Mod Positioning", Keyboard.KEY_O, Client.NAME);
    /**
     * Keybinding to toggle the {@link com.github.m5rian.shilu.client.mods.impl.ToggleSprint} mod.
     */
    public static final KeyBinding TOGGLE_SPRINT = new KeyBinding("Toggle Sprint", Keyboard.KEY_RCONTROL, Client.NAME);
    /**
     * Keybinding to toggle the {@link com.github.m5rian.shilu.client.mods.impl.Perspective} mod.
     */
    public static final KeyBinding PERSPECTIVE = new KeyBinding("360 Perspective", Keyboard.KEY_L, Client.NAME);

    public static final KeyBinding[] customKeys = {
            GUI_MOD_POSITION,
            TOGGLE_SPRINT,
            PERSPECTIVE
    };

}
