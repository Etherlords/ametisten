package org.etherlords.ametisten.stat.domain.shared;

import java.util.Collection;

public interface EventProvider {
    
    void clear();
    
    Collection<DomainEvent> getChanges();
    
    void loadFromHistory(final Collection<? extends DomainEvent> history);
    
}
