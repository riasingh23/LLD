package com.tictactoe.entity;

import com.tictactoe.enums.Piece;
import com.tictactoe.Pair;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private final Piece[][] board;
    private final int n;

    public Board(int n) {
        this.board = new Piece[n][n];
        this.n = n;
    }

    public boolean updateBoard(int i, int j, Player player) {
        if(i<0 || j<0 || i>=n || j>=n || board[i][j]!=null) {
            System.out.println("Invalid i: "+ i +", j: " + j);
            return false;
        }
        board[i][j] = player.getPiece();
        return true;
    }

    public Piece getPieceAtPosition(int i, int j){
        return board[i][j];
    }

    public int getSize() {
        return n;
    }

    public List<Pair<Integer, Integer>> availableSpot() {
        List<Pair<Integer, Integer>> availableSpot = new ArrayList<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++) {
                if(board[i][j] == null) {
                    Pair<Integer, Integer> pair = new Pair<>(i,j);
                    availableSpot.add(pair);
                }
            }
        }
        return availableSpot;
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
