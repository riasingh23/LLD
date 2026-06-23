package snake.ladder;

import snake.ladder.entity.Board;
import snake.ladder.entity.Dice;
import snake.ladder.entity.Player;
import snake.ladder.enums.GameStatus;

import java.util.List;
import java.util.Queue;

public class Game {
    private Board board;
    private Dice dice;
    private Queue<Player> players;
    private GameStatus gameStatus;
    private Player winner;

    public void play(){
        if(players.size()<2) throw new IllegalArgumentException("Less players");
        while(GameStatus.IN_PROGRESS.equals(gameStatus)) {
            Player currPlayer = players.poll();
            makeMove(currPlayer);
            if(GameStatus.IN_PROGRESS.equals(gameStatus))
                players.add(currPlayer);
        }
    }

    public void makeMove(Player player){
        int maxConsecutive = 3;
        int totalSix = 0;
        int startPosition = player.getPosition();
        while(maxConsecutive>0) {
            int val = dice.roll();
            if(player.getPosition()+val > board.getSize() || val != 6) break;
            totalSix++;
            player.setPosition(board.makeMove(player.getPosition()+val));
            checkWinner(player);
            maxConsecutive--;
        }
        if(totalSix == 3) {
            player.setPosition(startPosition);
        }
    }

    public void checkWinner (Player player){
        if(player.getPosition()==board.getSize()) {
            winner = player;
            gameStatus = GameStatus.WIN;
        }
    }

    public GameStatus getGameStatus(){
        return gameStatus;
    }

    public Player getWinner() {
        return winner;
    }

    public Game(GameBuilder gameBuilder) {
        this.dice = gameBuilder.dice;
        this.players = gameBuilder.players;
        this.board = gameBuilder.board;
        this.gameStatus = GameStatus.IN_PROGRESS;
        this.winner = null;

    }

    public static class GameBuilder{
        private Board board;
        private Queue<Player> players;
        private Dice dice;
        public GameBuilder setBoard(Board board){
            this.board = board;
            return this;
        }

        public GameBuilder setPlayers(Queue<Player> players){
            this.players = players;
            return this;
        }

        public GameBuilder setDice(Dice dice){
            this.dice = dice;
            return this;
        }

        public Game build() {
            if(board == null || players == null || dice == null) throw new IllegalArgumentException("Board, Players, and Dice must be set.");
            return new Game(this);
        }

    }


}
