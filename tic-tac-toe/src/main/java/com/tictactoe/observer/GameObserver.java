package com.tictactoe.observer;

import com.tictactoe.Move;
import com.tictactoe.entity.Board;

public interface GameObserver {
    public void onGameWin(String winner);
    public void onGameDraw();
    void onMoveMade(Move move);
    public void onInvalidMove();
    public void displayGame(Board board);
}
