package com.tictactoe.entity;

import com.tictactoe.GameRuleEngine;
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

    public int getN() {
        return n;
    }

    public void updateMoveOnBoard(int row, int col, Piece piece) {
        if(row<0 || col<0 || row>=n || col>=n) {
            throw new IndexOutOfBoundsException("Move is out of range " + n);
        }
        if(board[row][col]!=null) {
            throw new IllegalArgumentException("The cell: " + row + ", " + col + "is already filled");
        }
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
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++) {
                if(board[i][j] == null) {
                    System.out.print(" |");
                } else {
                    System.out.print(board[i][j].name()+"|");
                }
            }
            System.out.println();
        }
    }


}
