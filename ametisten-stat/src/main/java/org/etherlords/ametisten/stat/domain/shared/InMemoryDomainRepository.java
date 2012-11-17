package org.etherlords.ametisten.stat.domain.shared;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.etherlords.ametisten.stat.event.MessageRouter;

public class InMemoryDomainRepository extends DomainEventsRoutingSupport implements DomainRepository {
	
	private final Map<Object, Object> aggregates = new ConcurrentHashMap<Object, Object>();
	
	private MessageRouter messageRouter;
	
	@Override
	public <K, T extends AggregateRoot<K>> T loadById(Class<T> type, K id) {
        return (T) aggregates.get(id);
	}

	@Override
	public <K, T extends AggregateRoot<K>> void add(Class<T> type,
			T aggregateRoot) {
        if (!this.aggregates.containsKey(aggregateRoot.getId())) {
            this.aggregates.put(aggregateRoot.getId(), aggregateRoot);
        } else {
            throw new RuntimeException("Aggregate with the given id already registred: " + aggregateRoot.getId());
        }
        
        flushDomainEvents(aggregateRoot);
	}

	@Override
	public void save(AggregateRoot<?> aggregateRoot) {
		flushDomainEvents(aggregateRoot);
	}

	@Override
	public <T extends AggregateRoot<?>> Collection<T> loadBy(Class<T> type,
			Specification<T> specification) {
        final List<T> satisfied = new ArrayList<T>();
        
        for (final Object aggregate : aggregates.values()) {
        	if (aggregate.getClass().isAssignableFrom(type)) {
	            if (specification.isSatisfiedBy((T) aggregate)) {
	                satisfied.add((T) aggregate);
	            }
        	}
        }
        
        return satisfied;
	}

	@Override
	public <T extends AggregateRoot<?>> T loadOneBy(Class<T> type,
			Specification<T> specification) {
        T satisfied = null;
        
        for (final Object aggregate : aggregates.values()) {
        	
        	if (aggregate.getClass().isAssignableFrom(type)) {
	            if (specification.isSatisfiedBy((T) aggregate)) {
	                if (satisfied == null) {
	                    satisfied = (T) aggregate;
	                } else {
	                    throw new UnexpectedResultatSizeException("More then one " +
	                            "aggregate satisfied by the given specification.");
	                }
	            }
        	}
        }
        
        return satisfied;
	}

}
