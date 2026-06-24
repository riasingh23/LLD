package snake.ladder;

import snake.ladder.entity.*;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Random;

public class SnakeLadder
{
    public static void main(String[] args) {
        Board board = new Board(100, List.of(
                new Ladder(4, 14), new Ladder(9, 31), new Ladder(28, 84),
                new Snake(17, 7), new Snake(54, 34), new Snake(99, 78)
        ));
        Game game = new Game.GameBuilder()
                .setBoard(board)
                .setDice(new Dice(6))   // seeded for reproducibility
                .setPlayers(new ArrayDeque<>(List.of(new Player("A"), new Player("B"), new Player("C"))))
                .build();
        game.play();
        System.out.println("Winner: " + game.getWinner().getName());
    }
}
