package com.tictactoe;

import com.tictactoe.entity.Board;
import com.tictactoe.entity.Player;
import com.tictactoe.enums.GameStatus;
import com.tictactoe.strategy.MoveStrategy;

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

    public void play() {
        while (GameStatus.IN_PROGRESS.equals(gameStatus)) {
            Move move = currPlayer.makeMove(board);
            board.updateMoveOnBoard(move.getRow(), move.getColumn(), currPlayer.getPiece());
            if(board.checkWinner(move.getRow(), move.getColumn(), currPlayer.getPiece())) {
                winner = currPlayer;
                gameStatus = GameStatus.WIN;
                break;
            }
            else if(board.isBoardFull()) {
                gameStatus = GameStatus.DRAW;
                break;
            }
            displayGame();
            currPlayer = nextPlayer();
        }
        showWinner();
    }

    public void displayGame () {
        board.printBoard();
    }

    public Player nextPlayer() {
        int index = players.indexOf(currPlayer);
        return players.get((index+1)%(players.size()));
    }

    public void showWinner() {
        if (GameStatus.WIN.equals(gameStatus)) {
            System.out.println(gameStatus + ": " + winner.getName());
        } else if (GameStatus.DRAW.equals(gameStatus)) {
            System.out.println(gameStatus);
        }
    }
}
