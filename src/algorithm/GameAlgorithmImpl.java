package algorithm;
import java.util.Random;

import board.IBoard;

/*
 * Computer player who randomly generates a valid move.
 */
public class GameAlgorithmImpl extends BaseGameAlgorithm{
	
	IBoard board;
	
	public GameAlgorithmImpl(IBoard board){
		super(board);
		this.board = board;
	}

	@Override
	public int getNextMove() {
		Random r = new Random();
		return r.nextInt((int) ((Math.pow(this.board.getSize(), 2)    - 1) + 1)) + 1;
	}
}
