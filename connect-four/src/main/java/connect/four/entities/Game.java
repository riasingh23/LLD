package connect.four.entities;

import connect.four.enums.GameState;

public class Game {
    private Board board;
    private Player currPlayer;
    private Player player1;
    private Player player2;
    private GameState gameState;
    private Player winner;

    public Game(Player player1, Player player2, Board board) {
        this.player1 = player1;
        this.player2 = player2;
        this.currPlayer = player1;
        this.board = board;
        this.gameState = GameState.IN_PROGRESS;
        this.winner = null;
    }

    public boolean makeMove(Player player, int column) {
        if(currPlayer != player)
            return false;
        if(gameState != GameState.IN_PROGRESS)
            return false;
        int row = board.placeDisc(column, currPlayer.getColor());
        if(row == -1) return false;
        if(board.checkWinner(row, column, player.getColor())) {
            gameState = GameState.WIN;
            winner = player;
        }
        else if(board.isFull())
            gameState = GameState.DRAW;
        else
            currPlayer = (player == player1) ? player2 : player1;
        return true;

    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrPlayer() {
        return currPlayer;
    }

    public GameState getGameState() {
        return gameState;
    }

    public Player getWinner() {
        return winner;
    }
}
