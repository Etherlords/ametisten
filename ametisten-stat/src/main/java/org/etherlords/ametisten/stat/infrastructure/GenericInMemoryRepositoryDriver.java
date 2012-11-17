package org.etherlords.ametisten.stat.infrastructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.etherlords.ametisten.stat.domain.shared.AggregateRoot;
import org.etherlords.ametisten.stat.domain.shared.DomainRepositoryDriver;
import org.etherlords.ametisten.stat.domain.shared.Specification;
import org.etherlords.ametisten.stat.domain.shared.UnexpectedResultatSizeException;

public class GenericInMemoryRepositoryDriver<T extends AggregateRoot<K>, K>
implements DomainRepositoryDriver<T, K> {
    
    private final Map<K, T> aggregates = new ConcurrentHashMap<K, T>();
    
    private boolean isCloneAggregates = true;
    
    public void setAggregates(final Set<T> aggregates) {
        for (final T aggregate : aggregates) {
            add(aggregate);
        }
    }
    
    public void setIsCloneAggregates(final boolean isCloneAggregates) {
        this.isCloneAggregates = isCloneAggregates;
    }
    
    @Override
    public T loadById(final K aggregateId) {
        
        final T aggregate = aggregates.get(aggregateId);
        
        return aggregate;
        
//        if (aggregate == null) {
//            return null;
//        } else {
//            if (isCloneAggregates) {
//                try {
//                    return (T) aggregate.getClass().getMethod("clone").invoke(aggregate);
//                } catch (final Exception e) {
//                    throw new RuntimeException(e);
//                }
//            } else {
//                return aggregate;
//            }
//        }
        
    }
    
    @Override
    public void add(final T aggregateRoot) {
        if (!this.aggregates.containsKey(aggregateRoot.getId())) {
            this.aggregates.put(aggregateRoot.getId(), aggregateRoot);
        } else {
            throw new RuntimeException("Aggregate with the given id already registred: " + aggregateRoot.getId());
        }
    }
    
    @Override
    public Collection<T> loadBySpecification(final Specification<T> specification) {
        
        final List<T> satisfied = new ArrayList<T>();
        
        for (final T aggregate : aggregates.values()) {
            if (specification.isSatisfiedBy(aggregate)) {
                satisfied.add(aggregate);
            }
        }
        
        return satisfied;
    }
    
    @Override
    public T loadOneBySpecification(final Specification<T> specification) throws UnexpectedResultatSizeException {
        
        T satisfied = null;
        
        for (final T aggregate : aggregates.values()) {
            if (specification.isSatisfiedBy(aggregate)) {
                if (satisfied == null) {
                    satisfied = aggregate;
                } else {
                    throw new UnexpectedResultatSizeException("More then one " +
                            "aggregate satisfied by the given specification.");
                }
            }
        }
        
        return satisfied;
    }
    
}
