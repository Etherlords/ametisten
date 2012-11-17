package org.etherlords.ametisten.stat.application.command;

import java.io.Serializable;

public abstract class Command<T> implements Serializable {
    
    private static final long serialVersionUID = 6263110414729117747L;

    private final T id;
    
    public Command(final T id) {
        this.id = id;
    }
    
    public T getId() {
        return id;
    }
}
