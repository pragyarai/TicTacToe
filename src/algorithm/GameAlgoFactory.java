package algorithm;

import board.IBoard;

public class GameAlgoFactory {
	
	public static enum LEVEL {
		EASY, DIFFICULT;
	}
	
	public static BaseGameAlgorithm getAlgo(LEVEL lvl, IBoard board){
		if (lvl == LEVEL.DIFFICULT)
				return new SmartAlgorithmImpl(board);
		else
			return new GameAlgorithmImpl(board);
	}
}
