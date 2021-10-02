package com.github.m5rian.shilu.client.mods.impl.keystrokes;

enum KeyStrokesMode {
    WASD(Key.W, Key.A, Key.S, Key.D),
    WASD_MOUSE(Key.W, Key.A, Key.S, Key.D, Key.LEFT_MOUSE, Key.RIGHT_MOUSE),
    WASD_JUMP(Key.W, Key.A, Key.S, Key.D, Key.SPACE.moveRowUp()),
    WASD_MOUSE_JUMP(Key.W, Key.A, Key.S, Key.D, Key.LEFT_MOUSE, Key.RIGHT_MOUSE, Key.SPACE);

    private final Key[] keys;
    private int width = 0;
    private int height = 0;

    KeyStrokesMode(Key... keys) {
        this.keys = keys;

        for (Key key : keys) {
            this.width = Math.max(this.width, key.getX() + key.getWidth());
            this.height = Math.max(this.height, key.getY() + key.getHeight());
        }
        this.width++;
        this.height++;
    }

    public Key[] getKeys() {
        return keys;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
