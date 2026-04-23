package com.tictactoe.strategy;

import com.tictactoe.Move;
import com.tictactoe.entity.Board;
import com.tictactoe.enums.Piece;

public interface MoveStrategy {
    public Move makeMove(Board board);
}
