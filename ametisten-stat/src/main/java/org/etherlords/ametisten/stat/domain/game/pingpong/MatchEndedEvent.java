package org.etherlords.ametisten.stat.domain.game.pingpong;

import java.util.UUID;

import org.etherlords.ametisten.stat.domain.game.MatchResult;
import org.etherlords.ametisten.stat.domain.shared.DomainEvent;

public class MatchEndedEvent implements DomainEvent<UUID> {

	private static final long serialVersionUID = -1122377623796998392L;
	
	private final MatchResult matchResult;
	
	private final UUID matchId;

	MatchEndedEvent(UUID matchId, MatchResult gameResult) {
		this.matchId = matchId;
		this.matchResult = gameResult;
	}
	
	public MatchResult getMatchResult() {
		return matchResult;
	}

	@Override
	public UUID getAggregateId() {
		return matchId;
	}

}
