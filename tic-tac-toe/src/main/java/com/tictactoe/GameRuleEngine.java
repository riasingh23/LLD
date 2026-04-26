package com.tictactoe;

import com.tictactoe.entity.Board;
import com.tictactoe.enums.Piece;

import java.util.HashMap;
import java.util.Map;

public class GameRuleEngine {
    int n;
    private Map<Piece, Integer> diagCount = new HashMap<>();
    private Map<Piece, Integer> antiDiagCount = new HashMap<>();
    private Map<Piece, int[]> rowCount = new HashMap<>();
    private Map<Piece, int[]> colCount = new HashMap<>();

    public GameRuleEngine(int n) {
        this.n = n;
    }

    public boolean updateMoveAndCheckWinner(Move move, Piece piece) {
        int row = move.getRow(),col = move.getColumn();

        if(row == col) diagCount.put(piece, diagCount.getOrDefault(piece, 0) + 1);
        if(row+col == n-1) antiDiagCount.put(piece, antiDiagCount.getOrDefault(piece, 0) + 1);
        rowCount.put(piece,rowCount.getOrDefault(piece, new int[n]));
        rowCount.get(piece)[row]++;
        colCount.put(piece,colCount.getOrDefault(piece, new int[n]));
        colCount.get(piece)[col]++;

        if(diagCount.getOrDefault(piece,0)==n) return true;
        if(antiDiagCount.getOrDefault(piece,0)==n) return true;
        if(rowCount.get(piece)[row]==n) return true;
        if(colCount.get(piece)[col]==n) return true;
        return false;
    }
}
