package board;

import engine.Location;
import player.IPlayer;

/*
 * Interface of the game board. This can be implemented to create different game boards.
 * It exposes all the APIs useful for pluggable algorithms to make next moves
 */

public interface IBoard {
	void print();
	void boardIntialize();
	void updateBoard(Location l, IPlayer player);
	boolean isGameOver();
	String getResult();
	Location getValidLocation(int cell);
	Location lastLocation();
	int convertToRow(int cell);
	int convertToCol(int cell);
	int getSize();
}