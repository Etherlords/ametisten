package org.etherlords.ametisten.stat.domain.game.pingpong;

import java.util.UUID;

import org.etherlords.ametisten.stat.domain.game.ClientSideMatch;
import org.etherlords.ametisten.stat.domain.game.MatchResult;
import org.etherlords.ametisten.stat.domain.game.Match;
import org.etherlords.ametisten.stat.domain.game.Player;
import org.etherlords.ametisten.stat.domain.shared.AbstractAggregate;

public class PingPongMatch extends AbstractAggregate<UUID> implements Match, ClientSideMatch<MatchResult> {

	/**
	 * The Player who created this game.
	 */
	private UUID ownerId;
	
	private UUID gameId;

	private MatchState state;
	
	private MatchResult result;
	
	public static enum MatchState {
		NEW, PROGRESS, DONE;
	}
	
	public PingPongMatch(UUID gameId, UUID ownerId) {
		raiseEvent(new MatchCreatedEvent(gameId, UUID.randomUUID(), ownerId));
	}
	
	@Override
	public void start() {
		if (this.state == MatchState.NEW) {
			raiseEvent(new MatchStartedEvent(this.id));
		}
	}

	@Override
	public void end() {
		if (this.state == MatchState.PROGRESS && result != null) {
			raiseEvent(new MatchEndedEvent(this.id, result));
		}
	}

	protected void applyEvent(MatchStartedEvent event) {
		this.state = MatchState.PROGRESS;
	}
	
	protected void applyEvent(MatchCreatedEvent event) {
		this.gameId = event.getGameId();
		this.ownerId = event.getOwnerId();
		this.id = event.getMatchId();
		this.state = MatchState.NEW;
	}

	@Override
	public void acceptClientResult(MatchResult clientResult) {
		if (this.state == MatchState.PROGRESS) {
			this.result = clientResult;
			end();
		}
	}
	
}
