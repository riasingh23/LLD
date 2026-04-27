package com.tictactoe;

public class MoveResult {
    private boolean winner;
    private boolean valid;

    public MoveResult(boolean winner, boolean valid) {
        this.winner = winner;
        this.valid = valid;
    }

    public boolean isWinner() {
        return winner;
    }

    public boolean isValid() {
        return valid;
    }
}
