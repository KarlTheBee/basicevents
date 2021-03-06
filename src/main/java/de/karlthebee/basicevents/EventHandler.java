package de.karlthebee.basicevents;

import de.karlthebee.basicevents.basic.BasicEvent;
import de.karlthebee.basicevents.basic.EventListener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class EventHandler{

    private List<EventMethod> listeners = new ArrayList<>();


    public void register(EventListener listener) {
        for(Method m : listener.getClass().getMethods()){
            if (m.getParameterCount()!=1)
                continue;
            if (!m.isAnnotationPresent(Event.class))
                continue;
            if (!BasicEvent.class.isAssignableFrom(m.getParameterTypes()[0]))
                continue;
            listeners.add(new EventMethod(listener,m, (Class<? extends Event>) m.getParameterTypes()[0]));
        }
    }

   
    public synchronized boolean remove(EventListener listener) {
        return listeners.removeIf(em -> em.object==listener);
    }

   
    public synchronized void removeAll() {
        listeners.clear();
    }

   
    public void handle(BasicEvent event) {
        //streaming avoids concurrency errors!
        listeners.stream()
                .filter(listener -> listener.type == event.getClass())
                .forEach(listener -> {
                    try {
                        listener.method.invoke(listener.object,event);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        System.out.println("Exception on event : ");
                        e.printStackTrace();
                    }
                });
    }

    private class EventMethod{
        private EventListener object;
        private Method method;
        private Class<? extends Event> type;

        public EventMethod(EventListener object, Method method, Class<? extends Event> type) {
            this.object = object;
            this.method = method;
            this.type = type;
        }
    }
}
