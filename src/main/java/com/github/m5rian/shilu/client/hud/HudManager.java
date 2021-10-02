package com.github.m5rian.shilu.client.hud;

import com.github.m5rian.shilu.client.events.EventTarget;
import com.github.m5rian.shilu.client.events.impl.RenderEvent;
import com.google.common.collect.Sets;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.inventory.GuiContainer;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

public class HudManager {
    private final Set<IRenderer> registeredRenderers = Sets.newHashSet();

    public void register(IRenderer... renderers) {
        this.registeredRenderers.addAll(Arrays.asList(renderers));
    }

    public void unregister(IRenderer... renderers) {
        Arrays.asList(renderers).forEach(this.registeredRenderers::remove);
    }

    /**
     * @return Returns all registered renderers from {@link HudManager#registeredRenderers}.
     */
    public Collection<IRenderer> getRegisteredRenderers() {
        return Sets.newHashSet(this.registeredRenderers);
    }

    public void openConfigScreen() {
        Minecraft.getMinecraft().displayGuiScreen(new HudConfigScreen(this));
    }

    @EventTarget
    public void onRender(RenderEvent event) {
        if (Minecraft.getMinecraft().currentScreen == null || Minecraft.getMinecraft().currentScreen instanceof GuiContainer || Minecraft.getMinecraft().currentScreen instanceof GuiChat) {
            for (IRenderer renderer : this.registeredRenderers) {
                callRenderer(renderer);
            }
        }
    }

    public void callRenderer(IRenderer renderer) {
        if (renderer.isEnabled()) {
            ScreenPosition position = renderer.getPosition();
            if (position == null) {
                position = ScreenPosition.fromRelativePosition(0.5D, 0.5D); // Center it
            }

            renderer.render(position); // Render the renderer to it's saved position
        }
    }
}
