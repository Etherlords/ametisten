package org.etherlords.ametisten.stat.domain.shared;

import java.util.Collection;
import java.util.Map;

public class DriverBasedDomainRepository extends DomainEventsRoutingSupport implements DomainRepository {
    
    @SuppressWarnings("rawtypes")
	private Map drivers;
    
    public void setDrivers(final Map<Class<? extends AggregateRoot<?>>,
            DomainRepositoryDriver<AggregateRoot<?>, ?>> drivers) {
        this.drivers = drivers;
    }
    
    @Override
    public <K, T extends AggregateRoot<K>> T loadById(final Class<T> type, final K id) {
        
        @SuppressWarnings("unchecked")
        final DomainRepositoryDriver<T, K> driver = (DomainRepositoryDriver<T, K>) drivers.get(type);
        
        assert driver != null;
        return driver.loadById(id);
    }
    
    @Override
    public <K, T extends AggregateRoot<K>> void add(final Class<T> type, final T aggregateRoot) {
        @SuppressWarnings("unchecked")
        final DomainRepositoryDriver<T, K> driver = (DomainRepositoryDriver<T, K>) drivers.get(aggregateRoot.getClass());
        driver.add(aggregateRoot);
        save(aggregateRoot);
    }
    
    @Override
    public void save(final AggregateRoot<?> aggregateRoot) {
        flushDomainEvents(aggregateRoot);
    }
    
    @Override
    public <T extends AggregateRoot<?>> Collection<T> loadBy(final Class<T> aggregateRoot,
            final Specification<T> specification) {
        
        @SuppressWarnings("unchecked")
        final DomainRepositoryDriver<T, ?> driver = (DomainRepositoryDriver<T, ?>) drivers.get(aggregateRoot);
        
        return driver.loadBySpecification(specification);
    }
    
    @Override
    public <T extends AggregateRoot<?>> T loadOneBy(final Class<T> aggregateRoot, final Specification<T> specification) {
        
        @SuppressWarnings("unchecked")
        final DomainRepositoryDriver<T, ?> driver = (DomainRepositoryDriver<T, ?>) drivers.get(aggregateRoot);
        
        if (driver == null) {
            throw new RuntimeException("Can't find any driver for the given aggregate: " + aggregateRoot);
        }
        
        return driver.loadOneBySpecification(specification);
    }
    
}
