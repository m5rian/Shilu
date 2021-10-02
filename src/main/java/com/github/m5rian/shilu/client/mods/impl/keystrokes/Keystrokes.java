package com.github.m5rian.shilu.client.mods.impl.keystrokes;

import com.github.m5rian.shilu.client.hud.ScreenPosition;
import com.github.m5rian.shilu.client.mods.ModDraggable;
import com.github.m5rian.shilu.client.mods.Mods;
import net.minecraft.client.gui.Gui;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class Keystrokes extends ModDraggable {
    private KeyStrokesMode mode = KeyStrokesMode.WASD_MOUSE_JUMP;
    private boolean showCps = true;

    public void setMode(KeyStrokesMode mode) {
        this.mode = mode;
    }

    @Override
    public int getWidth() {
        return this.mode.getWidth();
    }

    @Override
    public int getHeight() {
        return mode.getHeight();
    }

    @Override
    public void render(ScreenPosition position) {
        GL11.glPushMatrix();

        for (Key key : this.mode.getKeys()) {
            final int textWidth = this.fontRenderer.getStringWidth(key.getName());
            // Draw key background
            Gui.drawRect(
                    position.getAbsoluteX() + key.getX(),
                    position.getAbsoluteY() + key.getY(),
                    position.getAbsoluteX() + key.getX() + key.getWidth(),
                    position.getAbsoluteY() + key.getY() + key.getHeight(),
                    key.isPressed() ? new Color(255, 255, 255, 102).getRGB() : new Color(0, 0, 0, 102).getRGB()
            );

            if ((key == Key.LEFT_MOUSE || key == Key.RIGHT_MOUSE) && showCps) {
                final int cps = key == Key.LEFT_MOUSE ? Mods.CPS.getLeftCPS() : Mods.CPS.getRightCPS();
                final int cpsTextWidth = this.fontRenderer.getStringWidth(String.valueOf(cps));

                this.fontRenderer.drawString(key.getName(),
                        position.getAbsoluteX() + key.getX() + (key.getWidth() / 2) - (textWidth / 2),
                        position.getAbsoluteY() + key.getY() + (key.getHeight() / 2) - 4,
                        key.isPressed() ? Color.BLACK.getRGB() : Color.WHITE.getRGB()
                );

                this.fontRenderer.drawString("CPS: " + cps,
                        position.getAbsoluteX() + key.getX() + (key.getWidth() / 2) - (cpsTextWidth / 2),
                        position.getAbsoluteY() + key.getY() + (key.getHeight() / 2) - 4,
                        key.isPressed() ? Color.BLACK.getRGB() : Color.WHITE.getRGB()
                );
            } else {
                this.fontRenderer.drawString(key.getName(),
                        position.getAbsoluteX() + key.getX() + (key.getWidth() / 2) - (textWidth / 2),
                        position.getAbsoluteY() + key.getY() + (key.getHeight() / 2) - 4,
                        key.isPressed() ? Color.BLACK.getRGB() : Color.WHITE.getRGB()
                );
            }

/*
            if (key == Key.LEFT_MOUSE || key == Key.RIGHT_MOUSE) {
                final int cps = key == Key.LEFT_MOUSE ? Mods.CPS.getLeftCPS() : Mods.CPS.getRightCPS();
                final int cpsTextWidth = this.fontRenderer.getStringWidth(cps);

                this.fontRenderer.drawString("CPS: " + cps,
                        position.getAbsoluteX() + key.getX() + (key.getWidth() / 2) - (cpsTextWidth / 2),
                        position.getAbsoluteY() + key.getY() + (key.getHeight() / 2) - 4,
                        key.isPressed() ? Color.BLACK.getRGB() : Color.WHITE.getRGB()
                );
            }

 */
        }

        GL11.glPopMatrix();
    }
}
