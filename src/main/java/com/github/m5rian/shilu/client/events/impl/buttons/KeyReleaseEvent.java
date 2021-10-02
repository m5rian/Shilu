package com.github.m5rian.shilu.client.events.impl.buttons;

import com.github.m5rian.shilu.client.events.Event;

public class KeyReleaseEvent extends Event {

    private final int key;

    public KeyReleaseEvent(int key) {
        this.key = key;
    }

    public int getKey() {
        return this.key;
    }

}
