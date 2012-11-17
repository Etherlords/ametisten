package org.etherlords.ametisten.stat.application.command.shared;

import org.etherlords.ametisten.stat.application.command.AbstractNotificableCommand;
import org.etherlords.ametisten.stat.domain.shared.notification.DomainData;
import org.etherlords.ametisten.stat.domain.shared.notification.DomainInfoProvider;

public abstract class AbstractDomainInfoProviderCommand<T extends DomainData<T>, I> extends
		AbstractNotificableCommand<I> implements DomainInfoProvider<T> {
	
	private static final long serialVersionUID = -1426987459828194953L;
	
	private T domainData;
	
	public AbstractDomainInfoProviderCommand(I id) {
		super(id);
	}

	@Override
	public T getDomainData() {
		return domainData;
	}

	@Override
	public boolean hasData() {
		return domainData != null;
	}

	protected void assignDomainData(T domainData) {
		
		if (this.domainData != null) {
			throw new IllegalStateException("Domain data already assigned.");
		}
		
		this.domainData = domainData;
	}
	
}
