package org.etherlords.ametisten.stat.application.event.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.etherlords.ametisten.stat.domain.game.pingpong.MatchEndedEvent;
import org.etherlords.ametisten.stat.event.EventHandler;
import org.etherlords.ametisten.stat.reporting.ReportRepository;
import org.etherlords.ametisten.stat.reporting.game.PlayerReport;
import org.etherlords.ametisten.stat.reporting.game.MatchReport;

public class MatchEndedEventHandler implements EventHandler<MatchEndedEvent> {

	private ReportRepository<PlayerReport, UUID> accountReportRepository;

	public void setAccountReportRepository(ReportRepository<PlayerReport, UUID> accountReportRepository) {
		this.accountReportRepository = accountReportRepository;
	}
	
	@Override
	public void handleEvent(MatchEndedEvent domainEvent) {
				
		PlayerReport playerReport = 
				accountReportRepository.findById(domainEvent.getAggregateId());
		
		List<MatchReport> gamesStatistic = new ArrayList<MatchReport>(); 
		
		gamesStatistic.addAll(playerReport.getGamesStatistic());
		gamesStatistic.add(new MatchReport(domainEvent.getAggregateId(), domainEvent.getMatchResult()));
		
		accountReportRepository.add(new PlayerReport(domainEvent.getAggregateId(), gamesStatistic, playerReport.getPlayerName()));
		
	}

}
