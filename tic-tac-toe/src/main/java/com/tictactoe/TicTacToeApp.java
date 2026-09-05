package com.tictactoe;

import com.tictactoe.entity.Board;
import com.tictactoe.entity.Human;
import com.tictactoe.entity.Player;
import com.tictactoe.enums.Piece;
import com.tictactoe.observer.ConsoleGameObserver;
import com.tictactoe.observer.GameObserver;
import com.tictactoe.observer.GameObserverManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TicTacToeApp
{
    public static void main(String[] args) {

        System.out.println("Hello World!");
        int n=3;
        Player player1 = new Human("Ria", Piece.X);
        Player player2 = new Human("Rekha", Piece.O);
        List<Player> players = Arrays.asList(player1, player2);
        GameObserver gameObserver = new ConsoleGameObserver();
        List<GameObserver> gameObserverList = new ArrayList<>();
        gameObserverList.add(gameObserver);
        GameObserverManager gameObserverManager = new GameObserverManager(gameObserverList);
        Game game = new Game(players, gameObserverManager, n);
        game.play();
    }
}
