package com.github.m5rian.shilu.client.events;

import com.github.m5rian.shilu.client.Client;
import com.github.m5rian.shilu.client.CustomKeyBind;
import com.github.m5rian.shilu.client.events.impl.buttons.KeyPressEvent;

public class EventScheduler {
    /**
     * A custom button event manager.
     */
    @EventTarget
    public void onButtonPressEvent(KeyPressEvent event) {
        if (event.getKey() == CustomKeyBind.GUI_MOD_POSITION.getKeyCode()) {
            Client.getClient().getHudManager().openConfigScreen();
        }
    }
}
