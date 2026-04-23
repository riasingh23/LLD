package com.tictactoe.entity;

import com.tictactoe.enums.Piece;
import com.tictactoe.strategy.HumanMoveStrategy;
import com.tictactoe.strategy.MoveStrategy;

public class Human extends Player{
    public Human(String name, Piece piece) {
        super(name, piece, new HumanMoveStrategy());
    }
}
