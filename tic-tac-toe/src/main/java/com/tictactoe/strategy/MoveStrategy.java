package com.tictactoe.strategy;

import com.tictactoe.Move;
import com.tictactoe.entity.Board;

public interface MoveStrategy {
    public Move makeMove(Board board);
}
