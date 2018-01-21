package engine;

import board.IBoard;
import player.IPlayer;

/*
 * Interface for Game controller. Controller is used to start the game and controls player's turns.
 */

public interface IGameController {
	public String run();
	public void playerTurn(IPlayer player, IBoard board);
}
