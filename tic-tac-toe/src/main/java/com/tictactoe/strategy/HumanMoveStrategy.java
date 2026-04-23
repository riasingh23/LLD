package com.tictactoe.strategy;

import com.tictactoe.Pair;
import com.tictactoe.entity.Board;
import com.tictactoe.enums.Piece;

import java.util.Scanner;

public class HumanMoveStrategy implements MoveStrategy{
    @Override
    public Pair<Integer,Integer> makeMove(Board board) {
        System.out.println("Enter i and j: ");
        Scanner scanner = new Scanner(System.in);
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        return new Pair<Integer, Integer>(row,col);
    }
}
