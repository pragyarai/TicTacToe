package engine;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import algorithm.BaseGameAlgorithm;
import algorithm.GameAlgoFactory;
import board.BoardImpl;
import board.IBoard;
import player.ComputerPlayerImpl;
import player.HumanPlayerImpl;
import player.IPlayer;

/*
 * Main class to start application.
 */

public class Main {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int size = 3;
		while(true){
			System.out.println("\nWelcome!\n Please select computer difficulty level (0 or 1) :");
			int lvl  = in.nextInt();
			if(lvl>1){
				continue;
			}
			IBoard board = new BoardImpl(size);
			BaseGameAlgorithm algo = GameAlgoFactory.getAlgo(GameAlgoFactory.LEVEL.values()[lvl], board);
			
			List<IPlayer> players = new ArrayList<IPlayer>();
			players.add(new HumanPlayerImpl("Luke"));
			players.add(new ComputerPlayerImpl("Computer", algo));
			
			GameControllerImpl controller = new GameControllerImpl(board, players);
			System.out.println(controller.run());
			System.out.println("To continue, press 0 else press any other number to exit :");
			int exit  = in.nextInt();
			if(exit>0){
				System.out.println("Goodbye!");
				break;
			}
		}
		in.close();
	}
}