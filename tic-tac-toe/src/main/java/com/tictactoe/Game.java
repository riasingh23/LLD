package com.tictactoe;

import com.tictactoe.entity.Board;
import com.tictactoe.entity.Player;
import com.tictactoe.enums.GameStatus;
import com.tictactoe.enums.Piece;

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
        if(isWinner(i, j, currPlayer.getPiece())) {
            winner = currPlayer;
            gameStatus = GameStatus.WIN;
        }
        if(board.availableSpot().isEmpty()) {
            gameStatus = GameStatus.DRAW;
        }
    }

    public Player nextPlayer() {
        int index = players.indexOf(currPlayer);
        return players.get((index+1)%(players.size()));
    }

    public void makeMove(int i, int j) {
        boolean updated = board.updateBoard(i, j, currPlayer);
        if(updated) {
            updateGame(i, j);
            if(GameStatus.IN_PROGRESS.equals(gameStatus)){
                currPlayer = nextPlayer();
            }
        }
    }

    public boolean isWinner(int i, int j, Piece piece) {
        int n=board.getSize();
        if(i == j) {
            boolean right_diag = true;
            for(int r=0; r<n; r++) {
                if(board.getPieceAtPosition(r,r) != piece){
                    right_diag = false;
                    break;
                }

            }
            if(right_diag) return right_diag;
        }
        if(i+j==n) {
            boolean left_diag = true;
            for(int r=0; r<n; r++) {
                if(board.getPieceAtPosition(r,n-r-1) != piece){
                    left_diag = false;
                    break;
                }

            }
            if(left_diag) return left_diag;
        }
        boolean vertical = true, horizontal = true;
        for(int r=0; r<n; r++) {
            if(board.getPieceAtPosition(i,r) != piece){
                vertical = false;
                break;
            }
        }
        for(int r=0; r<n; r++) {
            if(board.getPieceAtPosition(i,r) != piece){
                horizontal = false;
                break;
            }
        }

        return vertical || horizontal;
    }
}
