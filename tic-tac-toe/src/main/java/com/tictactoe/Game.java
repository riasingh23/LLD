package com.tictactoe;

import com.tictactoe.entity.Board;
import com.tictactoe.entity.Player;
import com.tictactoe.enums.GameStatus;

import java.util.List;

public class Game {
    private final Board board;
    private final List<Player> players;
    private Player currPlayer;
    private GameStatus gameStatus;
    private Player winner;

    public Game(Board board, List<Player> players) {
        this.board = board;
        this.players = players;
        this.currPlayer = players.get(0);
        this.gameStatus = GameStatus.IN_PROGRESS;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public String getWinner() {
        return winner.getName();
    }

    public void displayGame () {
        board.printBoard();
    }

    public void updateGame(int i, int j) {
        if(board.checkWinner(i, j, currPlayer.getPiece())) {
            winner = currPlayer;
            gameStatus = GameStatus.WIN;
        }
        if(board.isBoardFull()) {
            gameStatus = GameStatus.DRAW;
        }
    }

    public Player nextPlayer() {
        int index = players.indexOf(currPlayer);
        return players.get((index+1)%(players.size()));
    }

    public void makeMove(int i, int j) {
        boolean updated = board.updateMoveOnBoard(i, j, currPlayer.getPiece());
        if(updated) {
            updateGame(i, j);
            if(GameStatus.IN_PROGRESS.equals(gameStatus)){
                currPlayer = nextPlayer();
            }
        }
    }
}
