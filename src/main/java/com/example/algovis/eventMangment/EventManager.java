package com.example.algovis.eventMangment;

import com.google.common.eventbus.EventBus;

public class EventManager {

    private static EventManager eventManager;

    private EventBus eventBus = new EventBus();

    private EventManager() {
    }

    public void post(Object event) {
        eventBus.post(event);
    }

    public void register(Object subscriber) {
        eventBus.register(subscriber);
    }

    public void unregister(Object subscriber) {
        eventBus.unregister(subscriber);
    }

    public static EventManager getInstance(){
        if (eventManager == null){
            eventManager = new EventManager();
        }
        return eventManager;
    }
}
