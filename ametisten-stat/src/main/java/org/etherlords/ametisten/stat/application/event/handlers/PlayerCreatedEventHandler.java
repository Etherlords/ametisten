package org.etherlords.ametisten.stat.application.event.handlers;

import java.util.UUID;

import org.etherlords.ametisten.stat.domain.game.PlayerCreatedEvent;
import org.etherlords.ametisten.stat.event.EventHandler;
import org.etherlords.ametisten.stat.reporting.ReportRepository;
import org.etherlords.ametisten.stat.reporting.game.PlayerReport;

public class PlayerCreatedEventHandler implements EventHandler<PlayerCreatedEvent> {

	private ReportRepository<PlayerReport, UUID> accountReportRepository;

	public void setAccountReportRepository(ReportRepository<PlayerReport, UUID> accountReportRepository) {
		this.accountReportRepository = accountReportRepository;
	}
	
	@Override
	public void handleEvent(PlayerCreatedEvent domainEvent) {
		accountReportRepository.add(
				new PlayerReport(domainEvent.getAggregateId(), domainEvent.getPlayerName()));
	}

}
