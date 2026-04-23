package com.tictactoe.strategy;

import com.tictactoe.Move;
import com.tictactoe.entity.Board;

public class BotMoveStrategy implements MoveStrategy{
    @Override
    public Move makeMove(Board board) {
    return new Move(1,1);
    }
}
