package org.etherlords.ametisten.stat.domain.shared.saga;

import org.etherlords.ametisten.stat.domain.shared.DomainEvent;

public interface SagaStartedBy<E extends DomainEvent> {
    
    void handle(E domainEvent);
    
}
