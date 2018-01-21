package algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import board.IBoard;

/*
 * Slightly smarter than random number computer player. This player picks it's next move based on opponents previous move.
 */
public class SmartAlgorithmImpl extends BaseGameAlgorithm {

	List<Integer> lastMoves;
	List<Integer> nextMoves;

	public SmartAlgorithmImpl(IBoard board) {
		super(board);
		lastMoves = new ArrayList<>();
		nextMoves = new ArrayList<>();
	}

	@Override
	public int getNextMove() {
		Random r = new Random();
		int move;
		int lastMove;
		if (board.lastLocation() != null){
			lastMove = convertToPosition(board.lastLocation().getRow(), board.lastLocation().getCol(), board.getSize()); // add players last move
			lastMoves.add(lastMove);
			nextMoves = generateNextMoves(lastMove, board.getSize());
		}
		if (nextMoves.isEmpty()){ // If there are no next moves, return a random number
			move = r.nextInt((int) ((Math.pow(board.getSize(), 2) - 1) + 1)) + 1;
		} else {
			move = nextMoves.get(r.nextInt((int) (nextMoves.size())));
		}
		lastMoves.add(move); // add own latest move
		return move;
	}

	private List<Integer> generateNextMoves(int last, int size) {
		List<Integer> nextMoves = new ArrayList<>();
		int row = board.convertToRow(last);
		int col = board.convertToCol(last);
		int nextRowOption;
		int nextColOption;

		for (int i = -1; i <= 1; i++) {
			nextRowOption = (row + i);
			for (int j = -1; j <= 1; j++) {
				nextColOption = (col + j);
				int nextPosition = convertToPosition(nextRowOption, nextColOption, size);
				if (isInRange(nextPosition) && !lastMoves.contains(nextPosition))
					nextMoves.add(nextPosition);
			}
		}
		return nextMoves;
	}

	private boolean isInRange(int number) {
		if (number > 0 && number < Math.pow(board.getSize(), 2))
			return true;
		return false;
	}

	int convertToPosition(int row, int col, int size) {
		if (row < 0 || row >= size || col < 0 || col >= size)
			return Integer.MAX_VALUE; // Send out of range value
		return (row * size) + col + 1;
	}
}
