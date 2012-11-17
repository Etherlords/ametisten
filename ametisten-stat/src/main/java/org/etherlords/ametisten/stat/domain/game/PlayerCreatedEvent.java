package org.etherlords.ametisten.stat.domain.game;

import java.util.UUID;

import org.etherlords.ametisten.stat.domain.shared.DomainEvent;

public class PlayerCreatedEvent implements DomainEvent<UUID> {

	private static final long serialVersionUID = 2598536640822151551L;
	
	private final String accountName;

	private final UUID accountId;

	PlayerCreatedEvent(UUID accountId, String accountName) {
		this.accountId = accountId;
		this.accountName = accountName;
	}
	
	@Override
	public UUID getAggregateId() {
		return accountId;
	}

	public String getPlayerName() {
		return accountName;
	}
	
}
