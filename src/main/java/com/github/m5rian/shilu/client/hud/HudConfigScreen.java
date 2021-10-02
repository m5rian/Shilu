package com.github.m5rian.shilu.client.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Predicate;

public class HudConfigScreen extends GuiScreen {
    private final HashMap<IRenderer, ScreenPosition> enabledRenderers = new HashMap();
    private Optional<IRenderer> selectedRenderer = Optional.empty();
    private int previousX;
    private int previousY;

    public HudConfigScreen(HudManager hudManager) {
        final Collection<IRenderer> registeredRenderers = hudManager.getRegisteredRenderers(); // Get registered renderers
        // For each renderer
        for (IRenderer renderer : registeredRenderers) {
            // Renderer is enabled
            if (renderer.isEnabled()) {
                ScreenPosition position = renderer.getPosition(); // Load the position of the renderer
                if (position == null) {
                    position = ScreenPosition.fromRelativePosition(0.5D, 0.5D);
                }

                this.adjustBounds(renderer, position); // Adjust position of renderer
                this.enabledRenderers.put(renderer, position); // Add renderer to renderers HashMap
            }
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawDefaultBackground();
        final float zLevel = this.zLevel; // Get a backup of the current z level
        this.zLevel = 250.0F;

        this.drawHollowRect(0, 0, this.width - 1, this.height - 1, 0xFFFF0000); // Draw screen border
        // Render each renderer
        for (IRenderer renderer : this.enabledRenderers.keySet()) {
            final ScreenPosition position = this.enabledRenderers.get(renderer); // Get renderers position
            renderer.renderDummy(position); // Render a dummy of the renderer
            // Add a hitbox to renderer
            this.drawHollowRect(position.getAbsoluteX() -1 , position.getAbsoluteY() -1, renderer.getWidth(), renderer.getHeight(), 0xFF00FFFF);
        }

        this.zLevel = zLevel; // Restore z level backup variable
    }

    public void drawHollowRect(int x, int y, int w, int h, int colour) {
        this.drawHorizontalLine(x, x + w, y, colour);
        this.drawHorizontalLine(x, x + w, y + h, colour);

        this.drawVerticalLine(x, y + h, y, colour);
        this.drawVerticalLine(x + w, y + h, y, colour);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode == Keyboard.KEY_ESCAPE) {
            this.enabledRenderers.forEach(IRenderConfig::save);
            Minecraft.getMinecraft().displayGuiScreen(null);
        }
    }

    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int button, long timeSinceLastClick) {
        if (this.selectedRenderer.isPresent()) {
            this.moveSelectedRenderBy(mouseX - this.previousX, mouseY - this.previousY);
        }

        this.previousX = mouseX;
        this.previousY = mouseY;
    }

    private void moveSelectedRenderBy(int offsetX, int offsetY) {
        final IRenderer renderer = this.selectedRenderer.get(); // Get selected renderer
        final ScreenPosition position = this.enabledRenderers.get(renderer);

        position.setAbsolute(position.getAbsoluteX() + offsetX, position.getAbsoluteY() + offsetY);
        adjustBounds(renderer, position);
    }

    @Override
    public void onGuiClosed() {
        this.enabledRenderers.keySet().forEach(renderer -> renderer.save(enabledRenderers.get(renderer)));
    }

    @Override
    public boolean doesGuiPauseGame() {
        return true;
    }

    private void adjustBounds(IRenderer renderer, ScreenPosition position) {
        final ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        final int screenWidth = scaledResolution.getScaledWidth();
        final int screenHeight = scaledResolution.getScaledHeight();

        final int absoluteX = Math.max(0, Math.min(position.getAbsoluteX(), Math.max(screenWidth - renderer.getWidth(), 0)));
        final int absoluteY = Math.max(0, Math.min(position.getAbsoluteY(), Math.max(screenHeight - renderer.getHeight(), 0)));

        position.setAbsolute(absoluteX, absoluteY);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int button) throws IOException {
        this.previousX = mouseX;
        this.previousY = mouseY;
        this.loadMouseOver(mouseX, mouseY);
    }

    private void loadMouseOver(int x, int y) {
        this.selectedRenderer = this.enabledRenderers.keySet().stream().filter(new MouseOverFinder(x, y)).findFirst();
    }

    private class MouseOverFinder implements Predicate<IRenderer> {
        public final int mouseX;
        public final int mouseY;

        public MouseOverFinder(int x, int y) {
            this.mouseX = x;
            this.mouseY = y;
        }

        @Override
        public boolean test(IRenderer renderer) {
            final ScreenPosition position = enabledRenderers.get(renderer);

            final int absoluteX = position.getAbsoluteX();
            final int absoluteY = position.getAbsoluteY();

            return this.mouseX >= absoluteX && mouseX <= absoluteX + renderer.getWidth()
                    && this.mouseY >= absoluteY && mouseY <= absoluteY + renderer.getHeight();
        }

    }

}
