package com.tictactoe.entity;

import com.tictactoe.Move;
import com.tictactoe.enums.Piece;
import com.tictactoe.strategy.MoveStrategy;

public abstract class Player {
//    Bot, Human
    private String name;
    private Piece piece;
    private MoveStrategy moveStrategy;

    public Player(String name, Piece piece, MoveStrategy moveStrategy) {
        this.name = name;
        this.piece = piece;
        this.moveStrategy = moveStrategy;
    }

    public String getName() {
        return name;
    }

    public Piece getPiece() {
        return piece;
    }

    public Move makeMove(Board board) {
        return moveStrategy.makeMove(board);
    }
}
