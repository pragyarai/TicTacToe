package player;
import java.util.Scanner;

public class HumanPlayerImpl implements IPlayer{

	Scanner input;
	String marker;
	String name;
	
	public HumanPlayerImpl(String name) {
		input = new Scanner(System.in);
		this.marker = "X";
		this.name = name;
	}

	@Override
	public int playTurn() {
		System.out.println("Please enter a number between 1-9");
		return input.nextInt();
	}

	@Override
	public String marker() {
		return this.marker;
	}

	@Override
	public String name() {
		return this.name;
	}
	
	
}
