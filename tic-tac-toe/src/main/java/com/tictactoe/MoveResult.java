package com.tictactoe;

public class MoveResult {
    private boolean winner;
    private boolean valid;
    private boolean draw;

    public MoveResult(boolean winner, boolean valid, boolean draw) {
        this.winner = winner;
        this.valid = valid;
        this.draw = draw;
    }

    public boolean isWinner() {
        return winner;
    }

    public boolean isValid() {
        return valid;
    }

    public boolean isDraw() {
        return draw;
    }
}
