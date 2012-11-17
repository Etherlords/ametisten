package org.etherlords.ametisten.stat.application.webservice.controller;

import java.util.UUID;

import org.etherlords.ametisten.stat.application.command.commands.CreateGameMatchCommand;
import org.etherlords.ametisten.stat.application.command.commands.SendMatchResultCommand;
import org.etherlords.ametisten.stat.application.command.commands.CreateGameMatchCommand.MatchData;
import org.etherlords.ametisten.stat.domain.game.MatchResult;
import org.etherlords.ametisten.stat.event.MessageRouter;
import org.etherlords.ametisten.stat.reporting.ReportRepository;
import org.etherlords.ametisten.stat.reporting.game.PlayerReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/game/{gameId}")
public class GameController {
	
	@Autowired
	private MessageRouter messageRouter;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value="/match", method=RequestMethod.PUT)
	@ResponseBody
	public MatchData startGame(@PathVariable("gameId") String gameId, @RequestParam("accountName") String accountName) {
		
		Assert.notNull(messageRouter, "Message router can't be null, is required.");
		Assert.hasText(accountName, "Account name must be not null nor empty.");
		Assert.hasText(gameId, "Game Id name must be not null nor empty.");
		
		if (logger.isDebugEnabled()) {
			logger.debug("Request for game creation accepted.");
			logger.debug("Game id: " + gameId);
			logger.debug("Account name: " + accountName);
		}
		
		CreateGameMatchCommand createGameMatchCommand = new CreateGameMatchCommand(UUID.randomUUID(), accountName);
		
		messageRouter.routeMessage(createGameMatchCommand);
		
		if (createGameMatchCommand.hasNotifications()) {
			
		}
		
		if (createGameMatchCommand.hasData()) {
			return createGameMatchCommand.getDomainData();
		} else {
			throw new IllegalStateException("Somthing wrong with our message routing, command has no notification nor data.");
		}
		
	}
	
	@RequestMapping(value="/match/{matchId}/result", method=RequestMethod.POST)
	public void endGame(
			@PathVariable("matchId") String matchId,
			@RequestParam("accountName") String accountName, 
			@RequestParam("ricoshetsCount") int ricoshets, @RequestParam("winnerName") String winner, 
			@RequestParam("maxBallSpeed") int maxBallSpeed) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("Send command to the router.");
		}
		
		messageRouter.routeMessage(new SendMatchResultCommand(UUID.fromString(matchId), new MatchResult(winner, ricoshets, maxBallSpeed)));
		
		if (logger.isDebugEnabled()) {
			logger.debug("Command sent.");
		}
		
	}
		
}
