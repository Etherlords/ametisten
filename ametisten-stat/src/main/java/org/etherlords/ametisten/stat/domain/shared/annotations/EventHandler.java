package org.etherlords.ametisten.stat.domain.shared.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.etherlords.ametisten.stat.domain.shared.DomainEvent;

@Retention(RetentionPolicy.RUNTIME)
public @interface EventHandler {
    
    Class<? extends DomainEvent> event();
    
}
