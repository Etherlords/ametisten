package org.etherlords.ametisten.stat.application.command.handlers;

import org.etherlords.ametisten.stat.application.command.CommandHandler;
import org.etherlords.ametisten.stat.application.command.commands.CreatePlayerCommand;
import org.etherlords.ametisten.stat.domain.game.Player;
import org.etherlords.ametisten.stat.domain.shared.DomainRepositorySupport;

public class CreatePlayerCommandHandler extends DomainRepositorySupport implements
		CommandHandler<CreatePlayerCommand> {

	@Override
	public void handleCommand(CreatePlayerCommand command) {
		Player player = new Player(command.getPlayerName());
		
		domainRepository.add(Player.class, player);
		
		command.playerCreated(player.getId());
	}

}
