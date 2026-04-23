package com.tictactoe.strategy;

import com.tictactoe.Pair;
import com.tictactoe.entity.Board;
import com.tictactoe.enums.Piece;

public interface MoveStrategy {
    public Pair<Integer,Integer> makeMove(Board board);
}
