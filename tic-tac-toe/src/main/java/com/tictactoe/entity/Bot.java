package com.tictactoe.entity;

import com.tictactoe.enums.Piece;
import com.tictactoe.strategy.BotMoveStrategy;
import com.tictactoe.strategy.MoveStrategy;

public class Bot extends Player{
    public Bot(String name, Piece piece) {
        super(name, piece, new BotMoveStrategy());
    }
}
