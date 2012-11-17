package org.etherlords.ametisten.stat.domain.shared;

import java.util.Collection;

public interface DomainRepositoryDriver<T extends AggregateRoot<K>, K> {
    
    T loadById(K aggregateId);
    
    void add(T aggregateRoot);
    
    Collection<T> loadBySpecification(Specification<T> specification);
    
    T loadOneBySpecification(Specification<T> specification);
    
}
