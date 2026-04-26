package com.tictactoe;

import com.tictactoe.entity.Board;
import com.tictactoe.entity.Player;
import com.tictactoe.enums.GameStatus;
import com.tictactoe.observer.ConsoleGameObserver;
import com.tictactoe.observer.GameObserver;
import com.tictactoe.observer.GameObserverManager;

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
    private GameObserverManager gameObserverManager;

    public Game(List<Player> players, GameObserverManager gameObserverManager, int n) {
        this.board = new Board(n);
        this.gameRuleEngine = new GameRuleEngine(n);
        this.players = players;
        this.moves = new ArrayList<>();
        this.currPlayer = players.get(0);
        this.gameStatus = GameStatus.IN_PROGRESS;
        this.gameObserverManager = gameObserverManager;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void play() {
        while (GameStatus.IN_PROGRESS.equals(gameStatus)) {
            Move move = currPlayer.makeMove(board);
            if(!board.validateMove(move)){
                gameObserverManager.notifyInvalidMove();
                move = currPlayer.makeMove(board);
            }
            board.updateMoveOnBoard(move, currPlayer.getPiece());
            gameObserverManager.notifyMove(move);
            moves.add(move);
            boolean isWinner = gameRuleEngine.updateMoveAndCheckWinner(move, currPlayer.getPiece());
            if(isWinner) {
                winner = currPlayer;
                gameStatus = GameStatus.WIN;
                break;
            }
            else if(board.isBoardFull()) {
                gameStatus = GameStatus.DRAW;
                break;
            }
            gameObserverManager.notifyDisplayGame(board);
            currPlayer = nextPlayer();
        }
        showWinner();
    }

    public Player nextPlayer() {
        int index = players.indexOf(currPlayer);
        return players.get((index+1)%(players.size()));
    }

    public void showWinner() {
        if (GameStatus.WIN.equals(gameStatus)) {
            gameObserverManager.notifyWin(currPlayer.getName());
        } else if (GameStatus.DRAW.equals(gameStatus)) {
            gameObserverManager.notifyDraw();
        }
    }
}
