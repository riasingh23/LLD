package com.tictactoe.strategy;

import com.tictactoe.Pair;
import com.tictactoe.entity.Board;
import com.tictactoe.enums.Piece;

public class BotMoveStrategy implements MoveStrategy{
    @Override
    public Pair<Integer, Integer> makeMove(Board board) {
    return new Pair<>(1,1);
    }
}
