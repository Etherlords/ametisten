package org.etherlords.ametisten.stat.domain.shared;

import java.util.Collection;
import java.util.LinkedList;

public abstract class AbstractAggregate<K> extends AbstractEntity<K> implements AggregateRoot<K>, EventProvider {
    
    private LinkedList<EventProvider> childEventProviders;
    
    protected AbstractAggregate() {
        super();
    }
    
    public AbstractAggregate(final Collection<? extends DomainEvent> history) {
        super(history);
    }
    
    @Override
    protected void doInitialization() {
        childEventProviders = new LinkedList<EventProvider>();
    }
    
    @Override
    public Collection<DomainEvent> getChanges() {
        
        final LinkedList<DomainEvent> changes = new LinkedList<DomainEvent>();
        
        changes.addAll(super.getChanges());
        
        for (final EventProvider eventProvider : childEventProviders) {
            changes.addAll(eventProvider.getChanges());
        }
        
        return changes;
    }
    
    @Override
    public void clear() {
        for (final EventProvider eventProvider : childEventProviders) {
            eventProvider.clear();
        }
        super.clear();
    }
    
    protected void registerChildProvider(final EventProvider eventProvider) {
        childEventProviders.add(eventProvider);
    }
    
}
