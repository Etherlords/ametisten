package org.etherlords.ametisten.stat.domain.shared.saga;

public interface Saga<T> {
    
    void rollback();
    
}
