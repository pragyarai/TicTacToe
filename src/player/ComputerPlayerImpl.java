package player;

import algorithm.BaseGameAlgorithm;

public class ComputerPlayerImpl implements IPlayer{
	
	String marker;
	String name;
	BaseGameAlgorithm algo;
	
	public ComputerPlayerImpl(String name, BaseGameAlgorithm algo) {
		this.marker = "0";
		this.name = "Computer";
		this.algo = algo;
	}

	@Override
	public int playTurn() {
		return algo.getNextMove();
	}

	@Override
	public String marker() {
		return this.marker;
	}

	@Override
	public String name() {
		return name;
	}
}
