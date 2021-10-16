package com.github.m5rian.shilu.client;

import com.github.m5rian.shilu.client.mods.impl.Zoom;
import com.github.m5rian.shilu.client.utilities.Storage;
import com.google.gson.JsonObject;
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
    /**
     * Keybinding to toggle the {@link Zoom} mod.
     */
    public static final KeyBinding ZOOM = new KeyBinding("Zoom", Keyboard.KEY_C, Client.NAME);

    public static final KeyBinding[] customKeys = {
            GUI_MOD_POSITION,
            TOGGLE_SPRINT,
            PERSPECTIVE,
            ZOOM
    };

    public static void load() {
        if (!Storage.getKeybindingsFile().exists()) {
            Storage.writeFile(Storage.getKeybindingsFile(), "{}");
        }

        final JsonObject config = Storage.readJsonObject(Storage.getKeybindingsFile());
        for (KeyBinding key : customKeys) {
            if (config.has(key.getKeyDescription())) {
                final int binding = config.get(key.getKeyDescription()).getAsInt();
                key.setKeyCode(binding);
            } else {
                config.addProperty(key.getKeyDescription(), key.getKeyCode());
            }
        }
        Storage.writeFile(Storage.getKeybindingsFile(), config.toString());
    }

    public static void save() {
        final JsonObject json = new JsonObject();
        for (KeyBinding key : customKeys) {
            json.addProperty(key.getKeyDescription(), key.getKeyCode());
        }

        Storage.writeFile(Storage.getKeybindingsFile(), json.toString());
    }

}
