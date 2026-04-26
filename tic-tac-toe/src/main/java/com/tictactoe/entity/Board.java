package com.tictactoe.entity;

import com.tictactoe.GameRuleEngine;
import com.tictactoe.Move;
import com.tictactoe.enums.Piece;

public class Board {
    private final Piece[][] board;
    private final int n;
    private int totalMove;

    public Board(int n) {
        this.board = new Piece[n][n];
        this.n = n;
        this.totalMove = 0;
    }

    public int getSize() {
        return n;
    }

    public boolean validateMove(Move move){
        int row = move.getRow();
        int col = move.getColumn();
        if(row<0 || col<0 || row>=n || col>=n || board[row][col]!=null) {
            return false;
        }
        return true;
    }

    public void updateMoveOnBoard(Move move, Piece piece) {
        int row = move.getRow();
        int col = move.getColumn();

        board[row][col] = piece;
        totalMove++;
    }

    public Piece getPieceAtPosition(int i, int j){
        return board[i][j];
    }

    public boolean isBoardFull() {
        return totalMove == n*n;
    }

    public void printBoard (){

    }


}
