package com.tictactoe.entity;

import com.tictactoe.GameRuleEngine;
import com.tictactoe.Move;
import com.tictactoe.MoveResult;
import com.tictactoe.enums.GameStatus;
import com.tictactoe.enums.Piece;

public class Board {
    private final Piece[][] board;
    private final int n;
    private int totalMove;
    private GameRuleEngine gameRuleEngine;

    public Board(int n) {
        this.board = new Piece[n][n];
        this.n = n;
        this.totalMove = 0;
        this.gameRuleEngine = new GameRuleEngine(n);
    }

    public int getSize() {
        return n;
    }

    private boolean validateMove(Move move){
        int row = move.getRow();
        int col = move.getColumn();
        if(row<0 || col<0 || row>=n || col>=n || board[row][col]!=null) {
            return false;
        }
        return true;
    }

    public MoveResult makeMove(Move move, Piece piece) {
        if(!validateMove(move)) {
            return new MoveResult(false, GameStatus.IN_PROGRESS);
        }
        int row = move.getRow();
        int col = move.getColumn();

        board[row][col] = piece;
        totalMove++;
        if(gameRuleEngine.updateMoveAndCheckWinner(move, piece))
            return new MoveResult(true, GameStatus.WIN);
        if(isBoardFull())
            return new MoveResult(false, GameStatus.DRAW);
        return new MoveResult(false, GameStatus.IN_PROGRESS);
    }

    public Piece getPieceAtPosition(int i, int j){
        return board[i][j];
    }

    private boolean isBoardFull() {
        return totalMove == n*n;
    }


}
