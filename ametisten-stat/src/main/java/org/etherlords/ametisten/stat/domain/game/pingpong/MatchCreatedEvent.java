package org.etherlords.ametisten.stat.domain.game.pingpong;

import java.util.UUID;

import org.etherlords.ametisten.stat.domain.shared.DomainEvent;

public class MatchCreatedEvent implements DomainEvent<UUID> {
	
	private static final long serialVersionUID = -1924865251316206419L;

	private final UUID gameId;
	
	private final UUID matchId;
	
	private final UUID ownerId;

	public MatchCreatedEvent(UUID gameId, UUID matchID, UUID ownerId) {
		this.gameId = gameId;
		this.matchId = matchID;
		this.ownerId = ownerId;
	}

	public UUID getGameId() {
		return gameId;
	}

	public UUID getMatchId() {
		return matchId;
	}

	public UUID getOwnerId() {
		return ownerId;
	}

	@Override
	public UUID getAggregateId() {
		return gameId;
	}

}
