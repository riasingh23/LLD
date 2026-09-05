package com.tictactoe.observer;

import com.tictactoe.Move;
import com.tictactoe.entity.Board;

public class ConsoleGameObserver implements GameObserver{
    @Override
    public void onGameWin(String winner) {
        System.out.println("Winner: " + winner);
    }

    @Override
    public void onGameDraw() {
        System.out.println("Game is Draw");
    }

    @Override
    public void onMoveMade(Move move) {
        System.out.println("Move: "+move.getRow()+", "+move.getColumn()+" registered.");
    }

    @Override
    public void onInvalidMove() {
        System.out.println("Re-enter the move since the older one was invalid");
    }

    @Override
    public void displayGame(Board board) {
        for(int i=0;i<board.getSize();i++){
            for(int j=0;j<board.getSize();j++) {
                if(board.getPieceAtPosition(i,j) == null) {
                    System.out.print(" |");
                } else {
                    System.out.print(board.getPieceAtPosition(i,j).name()+"|");
                }
            }
            System.out.println();
        }
    }
}
