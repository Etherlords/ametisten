package org.etherlords.ametisten.stat.domain.shared;

public interface Specification<T extends AggregateRoot<?>> {
    
    boolean isSatisfiedBy(T entity);
    
}
