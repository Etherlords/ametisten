package org.etherlords.ametisten.stat.domain.shared;

import java.io.Serializable;

public interface DomainEvent<T> extends Serializable {
    
    T getAggregateId();
    
}
