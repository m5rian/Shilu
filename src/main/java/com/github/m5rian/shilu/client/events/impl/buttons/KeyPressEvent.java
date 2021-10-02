package com.github.m5rian.shilu.client.events.impl.buttons;

import com.github.m5rian.shilu.client.events.Event;

public class KeyPressEvent extends Event {

    private final int key;

    public KeyPressEvent(int key) {
        this.key = key;
    }

    public int getKey() {
        return this.key;
    }

}
