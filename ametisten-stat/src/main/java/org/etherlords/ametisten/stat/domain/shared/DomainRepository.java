package org.etherlords.ametisten.stat.domain.shared;

import java.util.Collection;

public interface DomainRepository {
    
    <K, T extends AggregateRoot<K>> T loadById(Class<T> type, K id);
    
    <K, T extends AggregateRoot<K>> void add(Class<T> type, T aggregateRoot);
    
    // NOTE: этого метода тут не должно быть, это решение до того момента,
    // пока мы полностью не переедем на uow
    void save(AggregateRoot<?> aggregateRoot);
    
    <T extends AggregateRoot<?>> Collection<T> loadBy(Class<T> type, Specification<T> specification);
    
    <T extends AggregateRoot<?>> T loadOneBy(Class<T> type, Specification<T> specification);

}
