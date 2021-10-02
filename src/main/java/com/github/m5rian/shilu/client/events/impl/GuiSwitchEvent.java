package com.github.m5rian.shilu.client.events.impl;

import com.github.m5rian.shilu.client.events.Event;
import net.minecraft.client.gui.GuiScreen;

public class GuiSwitchEvent extends Event {
    private final GuiScreen gui;

    public GuiSwitchEvent(GuiScreen gui) {
        this.gui = gui;
    }

    public GuiScreen getGui() {
        return this.gui;
    }
}
