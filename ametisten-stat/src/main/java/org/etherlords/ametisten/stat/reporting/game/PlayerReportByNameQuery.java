package org.etherlords.ametisten.stat.reporting.game;

import java.util.Collection;

import org.etherlords.ametisten.stat.reporting.ReportQuery;
import org.etherlords.ametisten.stat.reporting.ReportQueryResultSet;
import org.etherlords.ametisten.stat.reporting.SingleResultSet;

public class PlayerReportByNameQuery implements ReportQuery<PlayerReport> {

	private final String playerName;

	private final SingleResultSet<PlayerReport> resultSet;
	
	public PlayerReportByNameQuery(String playerName) {
		this.playerName = playerName;
		this.resultSet = new SingleResultSet<PlayerReport>();
	}
	
	@Override
	public void executeOn(Collection<PlayerReport> collection) {
		for (PlayerReport playerReport : collection) {
			if (playerReport.getPlayerName().equals(playerName)) {
				resultSet.addResult(playerReport);
			}
		}
	}

	@Override
	public ReportQueryResultSet<PlayerReport> getResultSet() {
		return resultSet;
	}
	
}
