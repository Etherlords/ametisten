package org.etherlords.ametisten.stat.domain.game;

import java.util.UUID;

import org.etherlords.ametisten.stat.domain.shared.AbstractAggregate;

public class Player extends AbstractAggregate<UUID> {

	private String playerName;

	public Player(String playerName) {
		raiseEvent(new PlayerCreatedEvent(UUID.randomUUID(), playerName));
	}
	
	protected final void applyEvent(PlayerCreatedEvent event) {
		this.playerName = event.getPlayerName();
		this.id = event.getAggregateId();
	}

	String getName() {
		return playerName;
	}
	
}
