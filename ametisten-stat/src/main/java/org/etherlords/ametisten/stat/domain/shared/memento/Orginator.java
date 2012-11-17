package org.etherlords.ametisten.stat.domain.shared.memento;


public interface Orginator<T extends Memento> {
    
    T createMemento();
    
    void installMemento(T memento);
    
}
