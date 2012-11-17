package org.etherlords.ametisten.stat.domain.shared;

import java.io.Serializable;

public interface ValueObject<T extends ValueObject<T>> extends Serializable {
    
    boolean sameValueAs(T valueObject);
    
}
