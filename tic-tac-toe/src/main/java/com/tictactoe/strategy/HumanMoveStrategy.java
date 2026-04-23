package com.tictactoe.strategy;

import com.tictactoe.Move;
import com.tictactoe.entity.Board;

import java.util.Scanner;

public class HumanMoveStrategy implements MoveStrategy{
    @Override
    public Move makeMove(Board board) {
        System.out.println("Enter i and j: ");
        Scanner scanner = new Scanner(System.in);
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        return new Move(row,col);
    }
}
