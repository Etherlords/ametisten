package org.etherlords.ametisten.stat.domain.shared;

import org.etherlords.ametisten.stat.event.MessageRouter;

public abstract class DomainEventsRoutingSupport {
    
    private MessageRouter router;
    
    public void setMessageRouter(final MessageRouter router) {
        this.router = router;
    }
    
    protected void flushDomainEvents(final EventProvider eventProvider) {
        for (final DomainEvent domainEvent : eventProvider.getChanges()) {
            router.routeMessage(domainEvent);
        }
        eventProvider.clear();
    }
    
}
