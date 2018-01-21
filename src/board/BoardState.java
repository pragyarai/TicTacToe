package board;

public interface BoardState {

	enum State {
		IDLE, RUNNING, WON, TIED;
	}
	State getState();
	void updateState();
}
