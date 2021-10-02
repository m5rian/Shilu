package com.github.m5rian.shilu.client.events;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Event {
    public Event call() {
        final ArrayList<EventData> dataList = EventManager.get(this.getClass());
        if (dataList != null) {
            for (EventData data : dataList) {
                try {
                    data.target.invoke(data.source, this);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return this;
    }
}
