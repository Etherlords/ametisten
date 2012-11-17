package org.etherlords.ametisten.stat.event;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.etherlords.ametisten.stat.application.command.Command;
import org.etherlords.ametisten.stat.application.command.CommandHandler;
import org.etherlords.ametisten.stat.domain.shared.DomainEvent;

public class CommonMessageRouter implements MessageRouter {
    
    private Set<EventHandler<?>> eventHandlers;
    
    private Map<Class<? extends DomainEvent>, List<EventHandler<? extends DomainEvent>>> registredEventHandlers;
    
    private Map<Class<? extends Command>, List<CommandHandler<? extends Command>>> registredCommandHandlers;
    
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    private Set<CommandHandler<?>> commandHandlers;
    
    public void setEventHandlers(final Set<EventHandler<?>> eventHandlers) {
        this.eventHandlers = eventHandlers;
    }
    
    public void setCommandHandlers(final Set<CommandHandler<?>> commandHandlers) {
        this.commandHandlers = commandHandlers;
    }
    
    public void registerHandlers() {
        
        this.registredEventHandlers = new HashMap<Class<? extends DomainEvent>, List<EventHandler<?>>>();
        
        for (final EventHandler<?> eventHandler : eventHandlers) {
            
            final ParameterizedType pt = (ParameterizedType) eventHandler.getClass().getGenericInterfaces()[0];
            
            if (logger.isDebugEnabled()) {
                logger.debug("Try to register handler for event" + pt.getActualTypeArguments()[0]);
            }
            
            List<EventHandler<?>> eventTypeHandlers = registredEventHandlers.get(pt.getActualTypeArguments()[0]);
            
            if (eventTypeHandlers == null) {
                eventTypeHandlers = new ArrayList<EventHandler<?>>();
                registredEventHandlers.put((Class<? extends DomainEvent>) pt.getActualTypeArguments()[0], eventTypeHandlers);
            }
            
            eventTypeHandlers.add(eventHandler);
            
            if (logger.isDebugEnabled()) {
                logger.debug("Event handler registred " + eventHandler.getClass());
            }
            
        }
        
        this.registredCommandHandlers = new HashMap<Class<? extends Command>, List<CommandHandler<? extends Command>>>();
        
        for (final CommandHandler<?> eventHandler : commandHandlers) {
            
            final ParameterizedType pt = (ParameterizedType) eventHandler.getClass().getGenericInterfaces()[0];
            
            if (logger.isDebugEnabled()) {
                logger.debug("Try to register handler for command" + pt.getActualTypeArguments()[0]);
            }
            
            List<CommandHandler<?>> commandTypeHandlers = registredCommandHandlers.get(pt.getActualTypeArguments()[0]);
            
            if (commandTypeHandlers == null) {
                commandTypeHandlers = new ArrayList<CommandHandler<?>>();
                registredCommandHandlers.put((Class<? extends Command>) pt.getActualTypeArguments()[0], commandTypeHandlers);
            }
            
            commandTypeHandlers.add(eventHandler);
            
            if (logger.isDebugEnabled()) {
                logger.debug("Command handler registred " + eventHandler.getClass());
            }
            
        }
        
    }
    
    
    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void routeMessage(final Object message) {
        
        if (logger.isDebugEnabled()) {
            logger.debug("Message received: " + message.getClass());
        }
        
        if (message instanceof DomainEvent) {
            
            final DomainEvent domainEvent = (DomainEvent) message;
            
            final List<EventHandler<? extends DomainEvent>> list = registredEventHandlers.get(message.getClass());
            
            if (list != null) {
                for (final EventHandler eventHandler : list) {
                    
                    if (logger.isDebugEnabled()) {
                        logger.debug("Send message to the handler " + eventHandler.getClass());
                    }
                    
                    eventHandler.handleEvent(domainEvent);
                }
            } else {
                
            }
        } else if (message instanceof Command) {
            final Command command = (Command) message;
            
            final List<CommandHandler<? extends Command>> list = registredCommandHandlers.get(message.getClass());
            
            if (list != null) {
                for (final CommandHandler commandHandler : list) {
                    
                    if (logger.isDebugEnabled()) {
                        logger.debug("Send command to the handler " + commandHandler.getClass());
                    }
                    
                    commandHandler.handleCommand(command);
                }
            } else {
                
            }
            
        }
        
    }
    
}
