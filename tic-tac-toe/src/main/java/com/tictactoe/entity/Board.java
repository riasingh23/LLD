package com.tictactoe.entity;

import com.tictactoe.enums.Piece;
import com.tictactoe.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private final Piece[][] board;
    private final int n;
    private Map<Piece, Integer> diagCount;
    private Map<Piece, Integer> antiDiagCount;
    private Map<Piece, int[]> rowCount;
    private Map<Piece, int[]> colCount;
    private int totalMove;

    public Board(int n) {
        this.board = new Piece[n][n];
        this.n = n;
        this.antiDiagCount = new HashMap<>();
        this.diagCount = new HashMap<>();
        this.rowCount = new HashMap<>();
        this.colCount = new HashMap<>();
        this.totalMove = 0;
    }

    public boolean updateMoveOnBoard(int row, int col, Piece piece) {
        if(row<0 || col<0 || row>=n || col>=n) {
            throw new IndexOutOfBoundsException("Move is out of range " + n);
        }
        if(board[row][col]!=null) {
            throw new IllegalArgumentException("The cell: " + row + ", " + col + "is already filled");
        }
        board[row][col] = piece;
        if(row == col) diagCount.put(piece, diagCount.getOrDefault(piece, 0) + 1);
        if(row+col == n-1) antiDiagCount.put(piece, antiDiagCount.getOrDefault(piece, 0) + 1);
        rowCount.put(piece,rowCount.getOrDefault(piece, new int[n]));
        rowCount.get(piece)[row]++;
        colCount.put(piece,colCount.getOrDefault(piece, new int[n]));
        colCount.get(piece)[col]++;
        totalMove++;
        return true;
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

    public boolean checkWinner(int row, int col, Piece piece) {
        if(diagCount.getOrDefault(piece,0)==n) return true;
        if(antiDiagCount.getOrDefault(piece,0)==n) return true;
        if(rowCount.get(piece)[row]==n) return true;
        if(colCount.get(piece)[col]==n) return true;
        return false;
    }
}
