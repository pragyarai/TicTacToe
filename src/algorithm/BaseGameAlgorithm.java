package algorithm;

import board.IBoard;

/*
 * Abstract class which pluggable algorithms can extend
 * Pluggable algorithms receive an object of the board so they can make moves based on board's state
 */

public abstract class BaseGameAlgorithm {
	IBoard board;

	BaseGameAlgorithm(IBoard board){
		this.board = board;
	}
	public abstract int getNextMove();
}
