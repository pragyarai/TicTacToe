package board;

import engine.Location;
import player.IPlayer;

public class BoardImpl implements IBoard, BoardState  {
	
	String[][] playerBoard;
	int size; // Board is always a square so row==col==size
	private State boardState = State.IDLE;
	private int numberOfMoves = 0;
	private IPlayer lastPlayer;
	private Location lastLocation;
	
	public BoardImpl(int size) {
		this.size = size;
		this.playerBoard = new String[size][size];
		boardIntialize();
		this.lastPlayer = null;
		this.lastLocation = null;
	}

	@Override
	public void boardIntialize() {
		for(int r = 0; r < this.size; r++)  {
			for(int c = 0; c < this.size; c++)
				playerBoard[r][c] = "-";
		}
		updateState();
		print();
	}

	@Override
	public void print() {
		System.out.println();
        for (int i = 0; i < playerBoard.length; i++) {
            for (int j = 0; j < playerBoard[i].length; j++) {
            	System.out.print(playerBoard[i][j]);
                if (j < 2) {
                    System.out.print("|");
                } else {
                    System.out.println();
                } 
             }
         }
         System.out.println();
	}

	@Override
	public String getResult() {
		String pattern = "\n**********\n";
		if (getState() == State.TIED)
			return pattern + "The Game Tied. No Winners." + pattern;
		else if (getState() == State.WON)
			return pattern + lastPlayer.name() + " won the game." + pattern;
		else
			return "The Game is still running";
	}

	@Override
	public int getSize() {
		return size;
	}
	
	@Override
	public Location lastLocation() {
		return this.lastLocation;
	};
	
	@Override
	public boolean isGameOver() {
		return (getState() == State.TIED) || (getState() == State.WON);
	}

	@Override
	public void updateBoard(Location l, IPlayer player) {
		playerBoard[l.getRow()][l.getCol()] = player.marker();
		this.numberOfMoves++;
		this.lastLocation = l;
		this.lastPlayer = player;
		updateState();
	}
	
	@Override	
	public Location getValidLocation(int cell){
		if (cell > 0 && cell <= 9) {
			int row = convertToRow(cell);
			int col = convertToCol(cell);
			if (playerBoard[row][col] == "-") {
				return new Location(row, col);
			}
			System.out.println("Cell "+ cell +" is already marked. Please select another cell.");
			return null;
		}
		System.out.println("Invalid entry.");
		return null;
	}

	@Override
	public void updateState() {
		switch (getState()) {
		case IDLE:
			boardState = State.RUNNING;
			break;
		case RUNNING:
			if(lastLocation != null && lastPlayer != null){
				if (isColumnComplete(lastLocation.getRow(), lastLocation.getCol()) 
						|| isRowComplete(lastLocation.getRow(), lastLocation.getCol()) 
						|| isDiagonalComplete(lastLocation.getRow(), lastLocation.getCol())){
					boardState = State.WON;
				} else if (isTie()) {
					boardState = State.TIED;
				}
			}
		default:
			break;
		}		
	}
	
	@Override
	public State getState() {
		return boardState;
	}
	
	public boolean isColumnComplete(int lastRow, int lastCol) {
		int numMarkersFound = 0;
		String marker = playerBoard[lastRow][lastCol];
		for(int col = 0; col < this.size; col++)  {
			if(playerBoard[lastRow][col].equals(marker))
				numMarkersFound++;
			else
				break;
		}
		
		if (numMarkersFound == this.size) {
			return true;
		}
		
		return false;
	}
	
	public boolean isRowComplete(int lastRow, int lastCol) {
		int numMarkersFound = 0;
		String marker = playerBoard[lastRow][lastCol];
		for(int row = 0; row < this.size; row++)  {
			if(playerBoard[row][lastCol].equals(marker))
				numMarkersFound++;
			else
				break;
		}
		
		if (numMarkersFound == this.size) {
			return true;
		}
		
		return false;
	}
	
	public boolean isDiagonalComplete(int lastRow, int lastCol) {
		String marker = playerBoard[lastRow][lastCol];
		int numMarkersFound = 0;
		
		// Check diagonal
		for (int i = 0; i < this.size; i++) {
			if(playerBoard[i][i].equals(marker))
				numMarkersFound++;
			else
				break;
		}
		if (numMarkersFound == this.size) {
			return true;
		}
		
		// Check anti-diagonal
		numMarkersFound = 0;
		for (int i = 0; i < this.size; i++) {
			if(playerBoard[i][size-1-i].equals(marker))
				numMarkersFound++;
			else
				break;
		}
		if (numMarkersFound == this.size) {
			return true;
		}
		
		return false;
	}
	
	public boolean isTie(){
		return numberOfMoves >= size*size;
	}
	
	@Override
	public int convertToRow(int cell) {
		return (cell - 1) / this.size;
	}
	
	@Override
	public int convertToCol(int cell) {
		return (cell - 1) % this.size;
	}
}
