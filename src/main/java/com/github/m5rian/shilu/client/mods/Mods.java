package com.github.m5rian.shilu.client.mods;

import com.github.m5rian.shilu.client.hud.HudManager;
import com.github.m5rian.shilu.client.mods.impl.*;
import com.github.m5rian.shilu.client.mods.impl.keystrokes.Keystrokes;

/**
 * @author Marian
 * This class stores and registers all mods.
 */
public class Mods {
    // Draggable mods
    private static ArmourStatus ARMOUR_STATUS;
    private static FpsCount FPS_COUNT;
    private static Keystrokes KEYSTROKES;
    public static Cps CPS;
    // Other mods
    public static ToggleSprint TOGGLE_SPRINT;
    public static Perspective PERSPECTIVE;

    public static void load(HudManager hudManager) {
        ARMOUR_STATUS = new ArmourStatus();
        FPS_COUNT = new FpsCount();
        KEYSTROKES = new Keystrokes();
        CPS = new Cps();

        TOGGLE_SPRINT = new ToggleSprint();
        PERSPECTIVE = new Perspective();

        hudManager.register(
                ARMOUR_STATUS,
                FPS_COUNT,
                KEYSTROKES,
                CPS
        );
    }

}
