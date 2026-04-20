package com.tictactoe;

import com.tictactoe.entity.Board;
import com.tictactoe.entity.Player;
import com.tictactoe.enums.GameStatus;
import com.tictactoe.enums.Piece;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TicTacToeApp
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello World!");
        Board board = new Board(3);
        Player player1 = new Player("Ria", Piece.X);
        Player player2 = new Player("Rekha", Piece.O);
        List<Player> players = Arrays.asList(player1, player2);
        Game game = new Game(board, players);
        while (GameStatus.IN_PROGRESS.equals(game.getGameStatus())) {
            System.out.println("Enter i and j: ");
            int i = scanner.nextInt();
            int j = scanner.nextInt();
            game.makeMove(i, j);
            game.displayGame();
        }
        if (GameStatus.WIN.equals(game.getGameStatus())) {
            System.out.println("WINNER: " + game.getWinner());
        } else if (GameStatus.DRAW.equals(game.getGameStatus())) {
            System.out.println("DRAW");
        }

    }
}
