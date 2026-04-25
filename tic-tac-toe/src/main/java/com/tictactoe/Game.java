package com.tictactoe;

import com.tictactoe.entity.Board;
import com.tictactoe.entity.Player;
import com.tictactoe.enums.GameStatus;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Board board;
    private final List<Player> players;
    private Player currPlayer;
    private GameStatus gameStatus;
    private Player winner;
    private List<Move> moves;
    private GameRuleEngine gameRuleEngine;

    public Game(List<Player> players, int n) {
        this.board = new Board(n);
        this.gameRuleEngine = new GameRuleEngine();
        this.players = players;
        this.moves = new ArrayList<>();
        this.currPlayer = players.get(0);
        this.gameStatus = GameStatus.IN_PROGRESS;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void play() {
        while (GameStatus.IN_PROGRESS.equals(gameStatus)) {
            Move move = currPlayer.makeMove(board);
            moves.add(move);
            if(gameRuleEngine.updateMoveAndCheckWinner(move.getRow(), move.getColumn(), currPlayer.getPiece(), board.getN())) {
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
