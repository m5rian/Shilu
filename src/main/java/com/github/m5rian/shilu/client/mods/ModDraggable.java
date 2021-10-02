package com.github.m5rian.shilu.client.mods;

import com.github.m5rian.shilu.client.hud.IRenderer;
import com.github.m5rian.shilu.client.hud.ScreenPosition;
import com.github.m5rian.shilu.client.utilities.Logger;
import com.github.m5rian.shilu.client.utilities.Storage;

import java.io.File;

/**
 * @author Marian
 * This represents a mod which gets rendered on the screen.
 */
public abstract class ModDraggable extends Mod implements IRenderer {

    /**
     * The position of the mod on the screen.
     */
    protected ScreenPosition position;

    /**
     * Loads the mods position from the saved file.
     */
    public ModDraggable() {
        loadFromFile();
    }

    /**
     * @return Returns the current {@link ModDraggable#position} of the mod.
     */
    @Override
    public ScreenPosition getPosition() {
        return this.position;
    }

    /**
     * Saves the {@param position} to the mod configs file.
     *
     * @param position The new position of the mod.
     */
    @Override
    public void save(ScreenPosition position) {
        this.position = position; // Update current mod position
        // Update mod configuration file
        final File file = new File(getModFolder(), "position.json"); // Get file
        Storage.writeJsonToFile(file, this.position); // Create config file with current position
    }

    private void loadFromFile() {
        final File file = new File(getModFolder(), "position.json");

        // Mod positioning config file doesn't exist yet
        if (!Storage.fileExists(file)) {
            final ScreenPosition defaultPosition = ScreenPosition.fromRelativePosition(0.5, 0.5); // Set default position in the middle of the screen
            Storage.writeJsonToFile(file, defaultPosition); // Save position to mod position config file
            this.position = defaultPosition; // Set the current position of the mod as the default one
        }

        // Mod positioning config already exists
        else {
            ScreenPosition position = Storage.readFromJson(file, ScreenPosition.class); // Load position from stored file
            this.position = position; // Apply position to mod
        }

        Logger.info(file.getPath() + " - x:" + position.getAbsoluteX() + " y:" + position.getAbsoluteY());
    }

    private File getModFolder() {
        final File folder = new File(Storage.getModsDir(), this.getClass().getSimpleName());
        folder.mkdirs();
        return folder;
    }

    public int getLineOffset(ScreenPosition position, int lineNumber) {
        return position.getAbsoluteY() + getLineOffset(lineNumber);
    }

    private int getLineOffset(int lineNum) {
        return (this.fontRenderer.FONT_HEIGHT + 3) * lineNum;
    }

}
