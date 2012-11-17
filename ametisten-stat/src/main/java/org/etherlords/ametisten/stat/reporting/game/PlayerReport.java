package org.etherlords.ametisten.stat.reporting.game;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.etherlords.ametisten.stat.reporting.Report;

public class PlayerReport implements Report<UUID> {

	private final UUID playerId;
	
	private final List<MatchReport> stat;

	private final String playerName;

	public PlayerReport(UUID playerId, List<MatchReport> stat, String playerName) {
		this.playerId = playerId;
		this.stat = stat;
		this.playerName = playerName;
	}
	
	public PlayerReport(UUID playerId, MatchReport stat, String playerName) {
		this(playerId, playerName);
		this.stat.add(stat);
	}
	
	public PlayerReport(UUID playerId, String playerName) {
		this(playerId, new ArrayList<MatchReport>(), playerName);
	}
	
	public List<MatchReport> getGamesStatistic() {
		return stat;
	}

	public String getPlayerName() {
		return playerName;
	}

	@Override
	public UUID getReportId() {
		return playerId;
	}
	
}
