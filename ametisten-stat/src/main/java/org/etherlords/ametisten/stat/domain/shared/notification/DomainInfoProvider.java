package org.etherlords.ametisten.stat.domain.shared.notification;

public interface DomainInfoProvider<T extends DomainData<T>> extends DomainDataProvider<T>, NotificationProvider {
    
    boolean hasData();
    
}
