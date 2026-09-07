# Tic-Tac-Toe Design
## Requirements
- Player should be able to make move
- Game should draw game if all cells are filled and there is no winner
- A player wins by completing a row, column, or diagonal.
- System should be able to store the moves by all player



## Entities
### Players
- `String name`
- `Piece piece`
- `Boolean winner`

- `isWinner()`
----------
### Game
- `List<Player> playerList;`
- `Game State;` -> `DRAW`, `WIN`, `IN_PROGRESS`
- `Board board;`
- `Player currentPlayer;`
- `Player winner;`

- `getGameState()`
- `makeMove()`
- `findWinner()`
-----------
### Board - N x N
- `List<Piece> board[n][n];`
- `updateBoard()`
- `availableSpot()`
- ~~`findWinner()`~~//this is game funtionality

### Piece
- `X`, `O`, ...