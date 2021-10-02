package com.github.m5rian.shilu.client.events;

public enum EventPriority {
    FIRST((byte) 0),
    SECOND((byte) 1),
    THIRD((byte) 2),
    FOURTH((byte) 3),
    FIFTH((byte) 4);

    private final byte priority;

    EventPriority(byte priority) {
        this.priority = priority;
    }

    public byte getPriority() {
        return this.priority;
    }
}
