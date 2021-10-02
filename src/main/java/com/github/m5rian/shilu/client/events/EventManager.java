package com.github.m5rian.shilu.client.events;

import java.lang.reflect.Method;
import java.util.*;

public class EventManager {

    private static final Map<Class<? extends Event>, ArrayList<EventData>> REGISTRY_MAP = new HashMap<>();

    private static void sortListValue(final Class<? extends Event> clazz) {
        final ArrayList<EventData> events = new ArrayList<>(); // Sorted list of events
        // For every priority
        for (EventPriority priority : EventPriority.values()) {
            for (EventData eventData : EventManager.REGISTRY_MAP.get(clazz)) {
                // The priority of the event matches the current looping priority
                if (eventData.priority == priority) {
                    events.add(eventData);
                }
            }
        }
        REGISTRY_MAP.put(clazz, events);
    }

    private static boolean isEventMethod(Method method) {
        return method.getParameterTypes().length == 1 && method.isAnnotationPresent(EventTarget.class);
    }

    private static boolean isEventMethod(Method method, Class<? extends Event> clazz) {
        return isEventMethod(method) || !method.getParameterTypes()[0].equals(clazz);
    }

    public static ArrayList<EventData> get(Class<? extends Event> clazz) {
        return REGISTRY_MAP.get(clazz);
    }

    public static void cleanMap(boolean removeOnlyEmptyValues) {
        final Iterator<Map.Entry<Class<? extends Event>, ArrayList<EventData>>> iterator = REGISTRY_MAP.entrySet().iterator();
        while (iterator.hasNext()) {
            // Method should clear all values or current value is empty
            if (!removeOnlyEmptyValues || iterator.next().getValue().isEmpty()) {
                iterator.remove(); // Remove entry
            }
        }
    }

    public static void unregister(Object object, Class<? extends Event> clazz) {
        if (REGISTRY_MAP.containsKey(clazz)) {
            REGISTRY_MAP.get(clazz).removeIf(eventData -> eventData.source.equals(object));
        }

        cleanMap(true);
    }

    public static void unregister(Object object) {
        for (ArrayList<EventData> events : REGISTRY_MAP.values()) {
            for (int i = events.size(); i >= 0; i--) {
                if (events.get(i).source.equals(object)) {
                    events.remove(i);
                }
            }
        }

        cleanMap(true);
    }

    public static void register(Method method, Object object) {
        final Class<?> clazz = method.getParameterTypes()[0]; // Get first argument class type
        final EventData eventData = new EventData(object, method, method.getAnnotation(EventTarget.class).value()); // Create Event data out of method

        if (eventData.target.isAccessible()) {
            eventData.target.setAccessible(true);
        }

        // Class is already registered
        if (REGISTRY_MAP.containsKey(clazz)) {
            // Method of registered class isn't registered yet
            if (!REGISTRY_MAP.get(clazz).contains(eventData)) {
                REGISTRY_MAP.get(clazz).add(eventData);
                sortListValue((Class<? extends Event>) clazz);
            }
        }
        // Class isn't registered yet
        else {
            REGISTRY_MAP.put((Class<? extends Event>) clazz, new ArrayList<EventData>() {{
                add(eventData);
            }});
        }
    }

    public static void register(Object object, Class<? extends Event> clazz) {
        for (Method method : object.getClass().getMethods()) {
            // Method is an event method
            if (isEventMethod(method, clazz)) {
                register(method, object); // Register method
            }
        }
    }

    public static void register(Object object) {
        for (Method method : object.getClass().getMethods()) {
            // Method is an event method
            if (isEventMethod(method)) {
                register(method, object); // Register method
            }
        }
    }

    public static void register(Object... objects) {
        Arrays.asList(objects).forEach(EventManager::register);
    }

}