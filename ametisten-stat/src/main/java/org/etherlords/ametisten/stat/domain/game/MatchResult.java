package org.etherlords.ametisten.stat.domain.game;

import org.etherlords.ametisten.stat.domain.shared.ValueObject;

public class MatchResult implements ValueObject<MatchResult> {

	private static final long serialVersionUID = -2368834491039859338L;

	private final String winner;

	private final int ricoshets;

	private final int maxBallSpeed;

	public MatchResult(String winner, int ricoshets, int maxBallSpeed) {
		this.winner = winner;
		this.ricoshets = ricoshets;
		this.maxBallSpeed = maxBallSpeed;
	}
	
	public String getWinner() {
		return winner;
	}

	public int getRicoshets() {
		return ricoshets;
	}

	public int getMaxBallSpeed() {
		return maxBallSpeed;
	}

	@Override
	public boolean sameValueAs(MatchResult valueObject) {
		return true;
	}

}
