### QnA

- Let's keep the board size configurable. By default, you can assume a 100-cell board, but your design shouldn't hardcode that value
- Assume the game supports multiple players. The number of players should be configurable at the start of the game, with a minimum of 2.
- Yes, the positions of snakes and ladders should be configurable. You'll receive their start and end positions when initializing the board rather than hardcoding them.
six-sided dice
- Yes. If a player rolls a 6, they get an extra turn immediately.
- No. Three 6s in a row forfeits the whole turn. The player returns to the position they started the turn from, and play moves to the next player.

### Requirement

- Player should be able to make move on ladder
  - invalid moves should through error.
  - If a player rolls a 6, they get an extra turn immediately.
  - Three 6s in a row forfeits the whole turn
- Ladder and snake shpuld be evaluated at every move of the player
  - Ladder and snake should be flexible
- Should be able to declare winner
  - Player how reaches first to last cell on board wins

### Entity model

- Game
- GameStatus -  WINNER, IN_PROGRESS
- Board
- BoardEntity
- Ladder
- Snake
- Player
- Dice

### Relation
- Game → GameStatus, Board, Player<List>, Dice
- BoardEntity -inherited by→ Snake, Ladder
- Board → BoardEntity<List>

### Class diagram
Game
- GameStatus
- Board
- Queue<List> players
- Player winner
+ Game(Builder)
+ play() [main game loop until someone wins]
+ makeMove(player) [move of one player]
+ getGameStatus() -> GameStatus
+ getWinner() -> winner

GameStatus(Enum)
+ IN_PROGRESS
+ WINNER

Board
- Map<int, BoardEntity> [Start point to BoardEntity mapping]
- board[][]
+ Board(size, <List> BoardEntity)
+ makeMove(int position) -> int finalPosition

BoardEntity
- start:int
- end:int
+ BoardEntity(start, end)
+ getStart()
+ getEnd()

Ladder extends BoardEntity
+ Ladder(start, end) → throw exception if start>=end

Snake extends BoardEntity
+ Snake(start, end) → throw exception if start<=end

Player
- position:int
- name:String
+ Player(name)
+ getPosition() -> int
+ getName() -> String

Dice
- size()
+ getSize()





