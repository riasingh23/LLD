package com.tictactoe;

import com.tictactoe.entity.Player;

import java.util.List;

public class TurnManager {
    private final List<Player> players;
    private int index;

    public TurnManager(List<Player> players) {
        this.players = players;
        this.index = 0;
    }

    public Player getCurrentPlayer() {
        return players.get(index);
    }

    public void moveToNextPlayer() {
        index = (index + 1) % players.size();
    }
}
