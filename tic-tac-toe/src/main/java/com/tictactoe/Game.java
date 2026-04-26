package com.tictactoe;

import com.tictactoe.entity.Board;
import com.tictactoe.entity.Player;
import com.tictactoe.enums.GameStatus;
import com.tictactoe.observer.GameObserverManager;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Board board;
    private volatile GameStatus gameStatus;
    private Player winner;
    private List<Move> moves;
    private GameRuleEngine gameRuleEngine;
    private GameObserverManager gameObserverManager;
    private final Object lock = new Object();
    private TurnManager turnManager;

    public Game(List<Player> players, GameObserverManager gameObserverManager, int n) {
        this.board = new Board(n);
        this.gameRuleEngine = new GameRuleEngine(n);
        this.turnManager = new TurnManager(players);
        this.moves = new ArrayList<>();
        this.gameStatus = GameStatus.IN_PROGRESS;
        this.gameObserverManager = gameObserverManager;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void play() {
        while (GameStatus.IN_PROGRESS.equals(gameStatus)) {
            Player currPlayer = turnManager.getCurrentPlayer();
            Move move = currPlayer.makeMove(board);
            if (!board.validateMove(move)) {
                gameObserverManager.notifyInvalidMove();
                continue;
            }
            board.updateMoveOnBoard(move, currPlayer.getPiece());
            gameObserverManager.notifyMove(move);
            moves.add(move);
            boolean isWinner = gameRuleEngine.updateMoveAndCheckWinner(move, currPlayer.getPiece());
            if (isWinner) {
                winner = currPlayer;
                gameStatus = GameStatus.WIN;
                break;
            } else if (board.isBoardFull()) {
                gameStatus = GameStatus.DRAW;
                break;
            }
            gameObserverManager.notifyDisplayGame(board);
            turnManager.moveToNextPlayer();
        }
        showWinner();
    }

    public void showWinner() {
        if (GameStatus.WIN.equals(gameStatus)) {
            gameObserverManager.notifyWin(winner.getName());
        } else if (GameStatus.DRAW.equals(gameStatus)) {
            gameObserverManager.notifyDraw();
        }
    }
}
