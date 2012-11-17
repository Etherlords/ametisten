package org.etherlords.ametisten.stat.application.command.handlers;

import java.util.UUID;

import org.etherlords.ametisten.stat.application.command.CommandHandler;
import org.etherlords.ametisten.stat.application.command.commands.CreateGameMatchCommand;
import org.etherlords.ametisten.stat.application.command.commands.CreatePlayerCommand;
import org.etherlords.ametisten.stat.domain.game.Player;
import org.etherlords.ametisten.stat.domain.game.PlayerNameSpecification;
import org.etherlords.ametisten.stat.domain.game.pingpong.PingPongMatch;
import org.etherlords.ametisten.stat.domain.shared.DomainRepositorySupport;
import org.etherlords.ametisten.stat.event.MessageRouter;

public class CreateGameMatchCommandHandler extends DomainRepositorySupport
		implements CommandHandler<CreateGameMatchCommand> {

	private MessageRouter messageRouter;

	public void setMessageRouter(MessageRouter messageRouter) {
		this.messageRouter = messageRouter;
	}
	
	@Override
	public void handleCommand(CreateGameMatchCommand command) {
		
		if (command == null) {
			throw new IllegalArgumentException("Command can't be null.");
		}
		
		Player player = domainRepository.loadOneBy(Player.class, new PlayerNameSpecification(command.getPlayerName()));
		
		UUID playerId;
		
		if (player == null) {
			
			CreatePlayerCommand createPlayerCommand = new CreatePlayerCommand(UUID.randomUUID(), command.getPlayerName());
			
			messageRouter.routeMessage(createPlayerCommand);
			
			if (createPlayerCommand.hasNotifications()) {
				
			}
			
			if (createPlayerCommand.hasData()) {
				playerId = createPlayerCommand.getDomainData().getPlayerId();
			} else {
				throw new IllegalStateException();
			}
			
		} else {
			playerId = player.getId();
		}
		
		PingPongMatch pingPongMatch = new PingPongMatch(command.getId(), playerId);
		
		domainRepository.add(PingPongMatch.class, pingPongMatch);
		
		command.matchCreated(pingPongMatch.getId());
		
	}

}
