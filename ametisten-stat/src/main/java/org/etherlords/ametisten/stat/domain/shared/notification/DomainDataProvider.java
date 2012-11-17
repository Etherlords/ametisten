package org.etherlords.ametisten.stat.domain.shared.notification;

public interface DomainDataProvider<T extends DomainData<T>> {
    
    T getDomainData();
    
}
