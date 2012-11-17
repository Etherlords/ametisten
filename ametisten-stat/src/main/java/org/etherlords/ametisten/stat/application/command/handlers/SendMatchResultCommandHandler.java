package org.etherlords.ametisten.stat.application.command.handlers;

import org.etherlords.ametisten.stat.application.command.CommandHandler;
import org.etherlords.ametisten.stat.application.command.commands.SendMatchResultCommand;
import org.etherlords.ametisten.stat.domain.game.Player;
import org.etherlords.ametisten.stat.domain.game.pingpong.PingPongMatch;
import org.etherlords.ametisten.stat.domain.shared.DomainRepositorySupport;
import org.etherlords.ametisten.stat.reporting.game.PlayerReport;

public class SendMatchResultCommandHandler extends DomainRepositorySupport implements CommandHandler<SendMatchResultCommand> {
	
	@Override
	public void handleCommand(SendMatchResultCommand command) {
		
		PingPongMatch match = domainRepository.loadById(PingPongMatch.class, command.getId());
		
		if (match == null) {
			command.matchNotFound();
		}
		
		match.acceptClientResult(command.getGameResult());
		
		domainRepository.save(match);
		
	}

}
