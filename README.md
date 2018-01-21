## Tic Tac Toe


### How to run
* In terminal

```
git clone https://github.com/pragyarai/TicTacToe.git
cd TicTacToe/src
javac engine/Main.java
java engine/Main
```

### Modules

#### Game Controller
This can be implemented to create different controller for this game. It provides the functionality to start a game and get next players turn.

#### Game Board
This is the main board on which game is to be played. This can be extended to create a 'n x n' board. This modules exposes API which coumputer algorithms can make use off.

#### Game Algorithm
Provides functionality to create pluggable algorithms. Algorithm module gets access to the board APIs.

#### Player
Defines functionality that any game player needs to implement.
