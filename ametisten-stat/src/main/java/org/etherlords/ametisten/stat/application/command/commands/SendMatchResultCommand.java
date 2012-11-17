package org.etherlords.ametisten.stat.application.command.commands;

import java.util.UUID;

import org.etherlords.ametisten.stat.application.command.AbstractNotificableCommand;
import org.etherlords.ametisten.stat.domain.game.MatchResult;

public class SendMatchResultCommand extends AbstractNotificableCommand<UUID> {

	private static final long serialVersionUID = -7353160398373386989L;
	
	private final MatchResult gameStat;

	public SendMatchResultCommand(UUID matchId, MatchResult gameStat) {
		super(matchId);
		
		this.gameStat = gameStat;
	}

	public MatchResult getGameResult() {
		return gameStat;
	}

	public void matchNotFound() {
		// TODO Auto-generated method stub
		
	}

}
