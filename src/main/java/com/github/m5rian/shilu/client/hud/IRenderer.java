package com.github.m5rian.shilu.client.hud;

public interface IRenderer extends IRenderConfig {
    int getWidth();

    int getHeight();

    void render(ScreenPosition position);

    default void renderDummy(ScreenPosition position) {
        this.render(position);
    }

    default boolean isEnabled() {
        return true;
    }
}
