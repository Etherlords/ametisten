package org.etherlords.ametisten.stat.event;

import org.etherlords.ametisten.stat.domain.shared.DomainEvent;

public interface EventHandler<T extends DomainEvent> {
    
    void handleEvent(T domainEvent);

}
