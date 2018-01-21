package engine;

import java.util.List;

import board.IBoard;
import player.IPlayer;

public class GameControllerImpl implements IGameController {
	
	IBoard board;
	List<IPlayer> players;
	
	public GameControllerImpl(IBoard board, List<IPlayer> players){
		this.board = board;
		this.players = players;
	}
	
	@Override
	public String run(){
		IPlayer lastPlayer = null;
		int move = 0;
		while(!this.board.isGameOver()) {
			lastPlayer = players.get(move % players.size());
			System.out.println(lastPlayer.name() + "'s turn");
			playerTurn(lastPlayer, board);
			board.print();
			move++;	
		}
		return board.getResult();
	}
	
	@Override
	public void playerTurn(IPlayer player, IBoard board) {
		int cell = Integer.MAX_VALUE;
		Location l = null;
		while(l == null){
			cell = player.playTurn();
			l = board.getValidLocation(cell);
		}
		board.updateBoard(l, player);
	}
	
}
