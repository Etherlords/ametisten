package org.etherlords.ametisten.stat.domain.game;

import org.etherlords.ametisten.stat.domain.shared.Specification;

public class PlayerNameSpecification implements Specification<Player> {
	
	private final String playerName;

	public PlayerNameSpecification(String playerName) {
		this.playerName = playerName;
	}
	
	@Override
	public boolean isSatisfiedBy(Player entity) {
		return entity.getName().equals(playerName);
	}

}
