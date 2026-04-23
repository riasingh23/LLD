package com.tictactoe;

import com.tictactoe.entity.Board;
import com.tictactoe.entity.Human;
import com.tictactoe.entity.Player;
import com.tictactoe.enums.Piece;
import java.util.Arrays;
import java.util.List;

public class TicTacToeApp
{
    public static void main(String[] args) {

        System.out.println("Hello World!");
        Board board = new Board(3);
        Player player1 = new Human("Ria", Piece.X);
        Player player2 = new Human("Rekha", Piece.O);
        List<Player> players = Arrays.asList(player1, player2);
        Game game = new Game(board, players);
        game.play();
    }
}
