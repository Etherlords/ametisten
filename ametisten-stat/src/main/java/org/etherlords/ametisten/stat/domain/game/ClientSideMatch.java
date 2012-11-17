package org.etherlords.ametisten.stat.domain.game;

public interface ClientSideMatch<T> {
	
	void acceptClientResult(T gameStatistic);
	
}
