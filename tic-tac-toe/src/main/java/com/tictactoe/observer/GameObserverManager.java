package com.tictactoe.observer;

import com.tictactoe.Move;
import com.tictactoe.entity.Board;

import java.util.List;

public class GameObserverManager {
    private List<GameObserver> gameObserverList;

    public GameObserverManager(List<GameObserver> gameObserverList) {
        this.gameObserverList = gameObserverList;
    }

    public void addGameObserver(GameObserver gameObserver) {
        gameObserverList.add(gameObserver);
    }

    public void removeGameObserver(GameObserver gameObserver) {
        gameObserverList.remove(gameObserver);
    }

    public void notifyMove (Move move){
        for(GameObserver gameObserver: gameObserverList) {
            gameObserver.onMoveMade(move);
        }
    }

    public void notifyWin (String winner){
        for(GameObserver gameObserver: gameObserverList) {
            gameObserver.onGameWin(winner);
        }
    }

    public void notifyDraw(){
        for(GameObserver gameObserver: gameObserverList) {
            gameObserver.onGameDraw();
        }
    }
    public void notifyDisplayGame(Board board){
        for(GameObserver gameObserver: gameObserverList) {
            gameObserver.displayGame(board);
        }
    }

    public void notifyInvalidMove(){
        for(GameObserver gameObserver: gameObserverList) {
            gameObserver.onInvalidMove();
        }
    }
}
