package com.github.m5rian.shilu.client.events;

public class EventCancelable extends Event {

    private boolean cancelled = false;

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

}
