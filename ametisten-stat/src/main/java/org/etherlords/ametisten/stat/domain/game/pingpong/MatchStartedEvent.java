package org.etherlords.ametisten.stat.domain.game.pingpong;

import java.util.UUID;

import org.etherlords.ametisten.stat.domain.shared.DomainEvent;

public class MatchStartedEvent implements DomainEvent<UUID> {

	private static final long serialVersionUID = -1023079017134617191L;
	private final UUID matchId;

	MatchStartedEvent(UUID matchId) {
		this.matchId = matchId;
	}
	
	@Override
	public UUID getAggregateId() {
		return matchId;
	}

}
