package com.tictactoe;

import com.tictactoe.enums.GameStatus;

public class MoveResult {
    private GameStatus gameStatus;
    private boolean valid;
    public MoveResult(boolean valid, GameStatus gameStatus) {
        this.valid = valid;
        this.gameStatus = gameStatus;
    }

    public boolean isValidMove() {
        return valid;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }
}
