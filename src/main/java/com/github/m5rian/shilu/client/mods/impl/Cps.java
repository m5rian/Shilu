package com.github.m5rian.shilu.client.mods.impl;

import com.github.m5rian.shilu.client.events.EventTarget;
import com.github.m5rian.shilu.client.events.impl.buttons.MouseLeftEvent;
import com.github.m5rian.shilu.client.events.impl.buttons.MouseRightEvent;
import com.github.m5rian.shilu.client.hud.ScreenPosition;
import com.github.m5rian.shilu.client.mods.ModDraggable;

import java.util.ArrayList;
import java.util.List;

/**
 * Counts the clicks per second.
 */
public class Cps extends ModDraggable {

    /**
     * Saves the System.currentTimeMillis of each left mouse press.
     */
    private static List<Long> leftClicks = new ArrayList<>();
    /**
     * Saves the System.currentTimeMillis of each right mouse press.
     */
    private static List<Long> rightClicks = new ArrayList<>();

    @Override
    public int getWidth() {
        return fontRenderer.getStringWidth("CPS: 00");
    }

    @Override
    public int getHeight() {
        return this.fontRenderer.FONT_HEIGHT;
    }

    /**
     * Update {@link Cps#leftClicks}.
     */
    @EventTarget
    public void onLeftClickEvent(MouseLeftEvent event) {
        leftClicks.add(System.currentTimeMillis());
    }

    /**
     * Update {@link Cps#rightClicks}.
     */
    @EventTarget
    public void onRightClickEvent(MouseRightEvent event) {
        rightClicks.add(System.currentTimeMillis());
    }

    @Override
    public void render(ScreenPosition position) {
        this.fontRenderer.drawString(String.format("CPS: %2d", getLeftCPS()), this.position.getAbsoluteX(), this.position.getAbsoluteY(), -1);
    }

    /**
     * Removes clicks from {@link Cps#leftClicks}, which expired and
     *
     * @return Returns the current left mouse button clicks per second.
     */
    public int getLeftCPS() {
        final long time = System.currentTimeMillis();
        leftClicks.removeIf(click -> click + 1000 < time);
        return leftClicks.size();
    }

    /**
     * Removes clicks from {@link Cps#rightClicks}, which expired and
     *
     * @return Returns the current right mouse button clicks per second.
     */
    public int getRightCPS() {
        final long time = System.currentTimeMillis();
        rightClicks.removeIf(click -> click + 1000 < time);
        return rightClicks.size();
    }

}
