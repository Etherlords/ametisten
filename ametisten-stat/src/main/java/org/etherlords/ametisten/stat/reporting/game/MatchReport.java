package org.etherlords.ametisten.stat.reporting.game;

import java.util.UUID;

import org.etherlords.ametisten.stat.domain.game.MatchResult;
import org.etherlords.ametisten.stat.reporting.Report;

public class MatchReport implements Report<UUID> {
	
	private final MatchResult score;
	
	private final UUID gameId;

	public MatchReport(UUID gameId, MatchResult statistic) {
		this.gameId = gameId;
		this.score = statistic;
	}
	
	public MatchResult getResult() {
		return score;
	}

	@Override
	public UUID getReportId() {
		return gameId;
	}

}
