package org.etherlords.ametisten.stat.application.command.commands;

import java.util.Collection;
import java.util.UUID;

import org.etherlords.ametisten.stat.application.command.Command;
import org.etherlords.ametisten.stat.domain.shared.notification.DomainData;
import org.etherlords.ametisten.stat.domain.shared.notification.DomainInfoProvider;
import org.etherlords.ametisten.stat.domain.shared.notification.Notification;
import org.etherlords.ametisten.stat.application.command.commands.CreateGameMatchCommand.MatchData;


public class CreateGameMatchCommand extends Command<UUID> implements
		DomainInfoProvider<MatchData> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8086381722824927372L;
	
	private final String playerName;
	
	private MatchData data;
	
	public static class MatchData implements DomainData<MatchData> {
		
		private static final long serialVersionUID = 2126876092424600507L;
		
		private final UUID matchId;

		public MatchData(UUID matchId) {
			this.matchId = matchId;
		}

		public UUID getMatchId() {
			return matchId;
		}
		
	}
	
	public CreateGameMatchCommand(UUID gameId, String playerName) {
		super(gameId);
		
		if (playerName == null || playerName.isEmpty()) {
			throw new IllegalArgumentException("Player name can't be null nor empty.");
		}
		
		this.playerName = playerName;
	}

	public String getPlayerName() {
		return playerName;
	}

	@Override
	public MatchData getDomainData() {
		
		if (data == null) {
			throw new IllegalStateException("Domain data is not available, can't get it.");
		}
		
		return data;
	}

	public void matchCreated(UUID matchId) {
		
		if (matchId == null) {
			throw new IllegalArgumentException("Match ID can't be null.");
		}
		
		this.data = new MatchData(matchId);
	}
	
	@Override
	public boolean containsNotification(Notification notification) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasNotifications() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<Notification> getNotifications() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasData() {
		return data != null;
	}

}
