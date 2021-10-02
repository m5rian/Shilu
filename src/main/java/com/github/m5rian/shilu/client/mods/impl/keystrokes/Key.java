package com.github.m5rian.shilu.client.mods.impl.keystrokes;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

import java.util.Arrays;

/**
 * Represents a single key.
 * This class contains all information about a displayed key.
 */
class Key {
    private static final int KEY_SIZE = 18; // The size of a normal key as a 1:1 aspect ratio
    private static final int MARGIN = 1; // The space added after a key
    private static final int ROW_HEIGHT = KEY_SIZE + MARGIN + MARGIN; // The height of a single row
    private static final int MAX_WIDTH = KEY_SIZE * 3 + MARGIN * 4; // The maximum width of a row

    public static final Key W = new Key("W", Minecraft.getMinecraft().gameSettings.keyBindForward, KEY_SIZE + MARGIN + MARGIN, KeyRow.ONE, KEY_SIZE, KEY_SIZE);
    public static final Key A = new Key("A", Minecraft.getMinecraft().gameSettings.keyBindLeft, 0, KeyRow.TWO, KEY_SIZE, KEY_SIZE);
    public static final Key S = new Key("S", Minecraft.getMinecraft().gameSettings.keyBindBack, KEY_SIZE + MARGIN + MARGIN, KeyRow.TWO, KEY_SIZE, KEY_SIZE);
    public static final Key D = new Key("D", Minecraft.getMinecraft().gameSettings.keyBindRight, KEY_SIZE + MARGIN + MARGIN + KEY_SIZE + MARGIN + MARGIN, KeyRow.TWO, KEY_SIZE, KEY_SIZE);

    public static final Key LEFT_MOUSE = new Key("LMB", Minecraft.getMinecraft().gameSettings.keyBindAttack, 0, KeyRow.THREE, MAX_WIDTH / 2 - MARGIN, KEY_SIZE);
    public static final Key RIGHT_MOUSE = new Key("RMB", Minecraft.getMinecraft().gameSettings.keyBindUseItem, MAX_WIDTH / 2 + MARGIN + MARGIN, KeyRow.THREE, MAX_WIDTH / 2 - MARGIN * 2, KEY_SIZE);

    public static final Key SPACE = new Key("Space", Minecraft.getMinecraft().gameSettings.keyBindJump, 0, KeyRow.FOUR, MAX_WIDTH, KEY_SIZE);

    private final String name;
    private final KeyBinding key;
    private final int x;
    private KeyRow row;
    private final int width;
    private final int height;

    /**
     * @param name   The name of the key.
     * @param key    A matching {@link KeyBinding}.
     * @param x      The start coordinates of x-axis.
     * @param row    The row of the key. This will later get calculated in the start coordinates of the y-axis.
     * @param width  The width of the key.
     * @param height The height of the key.
     */
    public Key(String name, KeyBinding key, int x, KeyRow row, int width, int height) {
        this.name = name;
        this.key = key;
        this.x = x;
        this.row = row;
        this.width = width;
        this.height = height;
    }

    /**
     * @return Returns true if the key is currently pressed. If not the method returns false.
     */
    public boolean isPressed() {
        return this.key.isKeyDown();
    }

    /**
     * @return Returns the display name of the key.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return Returns the start x-axis coordinates.
     */
    public int getX() {
        return this.x;
    }

    /**
     * @return Returns the start y-axis coordinates.
     */
    public int getY() {
        return this.row.getY();
    }

    /**
     * Moves the key one row up.
     *
     * @return Returns a new object of {@link Key} with a lower {@link Key#row}.
     */
    public Key moveRowUp() {
        return this.row.subtractRow(this);
    }

    /**
     * Moves the key one row down.
     *
     * @return Returns a new object of {@link Key} with a higher {@link Key#row}.
     */
    public Key moveRowDown() {
        return this.row.addRow(this);
    }

    /**
     * @return Returns the width of the key.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * @return Returns the height of a key.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Represents a row of keys.
     */
    private enum KeyRow {
        ONE(0),
        TWO(1),
        THREE(2),
        FOUR(3),
        FIVE(4);

        private final int row; // Row index
        private final int y; // Row y-axis coordinates start

        /**
         * @param row The row index.
         */
        KeyRow(int row) {
            this.row = row;
            this.y = ROW_HEIGHT * row;
        }

        /**
         * @return Returns the start coordinates of the current row on the y-axis.
         */
        public int getY() {
            return y;
        }

        /**
         * Move the current row up by 1.
         * This method returns a new copy of the current used key to prevent issues with changing the original key.
         * Issues can occur when this key is used in multiple {@link KeyStrokesMode}s and with at least one method
         * call of {@link KeyRow#addRow(Key)} or {@link KeyRow#subtractRow(Key)}.
         *
         * @param key The {@link Key} to change its row.
         * @return Returns a new {@link Key} object with the same data as {@param key}, except the changed row.
         */
        public Key addRow(Key key) {
            final KeyRow newRow = Arrays.stream(values()).filter(row -> row.row == this.row + 1).findFirst().get();
            return new Key(key.name, key.key, key.x, newRow, key.width, key.height);
        }

        /**
         * Move the current row down by 1.
         * This method returns a new copy of the current used key to prevent issues with changing the original key.
         * Issues can occur when this key is used in multiple {@link KeyStrokesMode}s and with at least one method
         * call of {@link KeyRow#addRow(Key)} or {@link KeyRow#subtractRow(Key)}.
         *
         * @param key The {@link Key} to change its row.
         * @return Returns a new {@link Key} object with the same data as {@param key}, except the changed row.
         */
        public Key subtractRow(Key key) {
            final KeyRow newRow = Arrays.stream(values()).filter(row -> row.row == this.row - 1).findFirst().get();
            return new Key(key.name, key.key, key.x, newRow, key.width, key.height);
        }
    }

}