package org.etherlords.ametisten.stat.application.command.commands;

import java.util.UUID;

import org.etherlords.ametisten.stat.application.command.shared.AbstractDomainInfoProviderCommand;
import org.etherlords.ametisten.stat.domain.shared.notification.DomainData;
import org.etherlords.ametisten.stat.application.command.commands.CreatePlayerCommand.PlayerData;

public class CreatePlayerCommand extends AbstractDomainInfoProviderCommand<PlayerData, UUID> {
	
	private static final long serialVersionUID = -3351453803028831843L;
	private final String playerName;

	public static class PlayerData implements DomainData<PlayerData> {

		private final UUID playerId;

		private static final long serialVersionUID = -8237839618610426496L;
		
		public PlayerData(UUID playerId) {
			this.playerId = playerId;
		}

		public UUID getPlayerId() {
			return playerId;
		}
		
	}
	
	public CreatePlayerCommand(UUID id, String playerName) {
		super(id);
		this.playerName = playerName;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void playerCreated(UUID playerId) {
		assignDomainData(new PlayerData(playerId));
	}

}
