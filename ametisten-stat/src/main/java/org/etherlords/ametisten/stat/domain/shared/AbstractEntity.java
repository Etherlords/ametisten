package org.etherlords.ametisten.stat.domain.shared;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.etherlords.ametisten.stat.domain.shared.annotations.EntityEventHandler;
import org.etherlords.ametisten.stat.domain.shared.annotations.EventHandler;

public abstract class AbstractEntity<K> implements Entity<K>, EventProvider {
    
    protected K id;
    
    private final LinkedList<DomainEvent> changes;
    
    private final Map<Class<? extends DomainEvent>, Method> eventHandlers;
    
    protected AbstractEntity() {
        this(new LinkedList<DomainEvent>());
    }
    
    public AbstractEntity(final Collection<? extends DomainEvent> history) {
        changes = new LinkedList<DomainEvent>(history);
        eventHandlers = new HashMap<Class<? extends DomainEvent>, Method>();
        registerEventHandlers();
        doInitialization();
        loadFromHistory(history);
    }
    
    @Override
    public K getId() {
        return id;
    }
    
    protected void doInitialization() {
        
    }
    
    @Override
    public Collection<DomainEvent> getChanges() {
        return new LinkedList<DomainEvent>(changes);
    }
    
    @Override
    public void clear() {
        this.changes.clear();
    }
    
    @SuppressWarnings("unchecked")
    private final void registerEventHandlers() {
        for (final Method method : getClass().getDeclaredMethods()) {
            if (isUnannotatedMethod(method)) {
                addMethodAsEventHandler((Class<? extends DomainEvent>) method.getParameterTypes()[0], method);
            } else if (isEventHandler(method)) {
                final EventHandler annotation = method.getAnnotation(EventHandler.class);
                addMethodAsEventHandler(annotation.event(), method);
            } else if (isEntityEventHandler(method)) {
                final EntityEventHandler annotation = method.getAnnotation(EntityEventHandler.class);
                for (final Class<? extends DomainEvent> eventType : annotation.events()) {
                    addMethodAsEventHandler(eventType, method);
                }
                continue;
            }
        }
    }
    
    private boolean isEntityEventHandler(final Method method) {
        return method.isAnnotationPresent(EntityEventHandler.class);
    }
    
    private boolean isEventHandler(final Method method) {
        return method.isAnnotationPresent(EventHandler.class);
    }
    
    private boolean isUnannotatedMethod(final Method method) {
        return method.getName().equals("applyEvent") && !isEventHandler(method)
                && !isEntityEventHandler(method);
    }
    
    private void addMethodAsEventHandler(final Class<? extends DomainEvent> eventType, final Method eventHandler) {
        eventHandler.setAccessible(true);
        addEventHandler(eventType, eventHandler);
    }
    
    private void addEventHandler(final Class<? extends DomainEvent> eventType,
            final Method eventHandler) {
        eventHandlers.put(eventType, eventHandler);
    }
    
    protected void handleEvent(final DomainEvent domainEvent) {
        if (eventHandlers.containsKey(domainEvent.getClass())) {
            try {
                eventHandlers.get(domainEvent.getClass()).invoke(this, domainEvent);
            } catch (final Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("Can't find any handler for the given event: "
                    + domainEvent.getClass() + " in: " + this);
        }
    }
    
    @Override
    public void loadFromHistory(final Collection<? extends DomainEvent> history) {
        for (final DomainEvent domainEvent : history) {
            handleEvent(domainEvent);
        }
    }
    
    protected void raiseEvent(final DomainEvent domainEvent) {
        this.changes.add(domainEvent);
        handleEvent(domainEvent);
    }
    
}
