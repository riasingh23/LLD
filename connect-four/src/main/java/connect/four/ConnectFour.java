package connect.four;

import connect.four.entities.Board;
import connect.four.entities.Game;
import connect.four.entities.Player;
import connect.four.enums.Color;

public class ConnectFour {
    public static void main(String[] args) {
        Board board = new Board(6,7);
        Player player1 = new Player("Ria", Color.RED);
        Player player2 = new Player("Sushil", Color.YELLOW);
        Game game = new Game(player1, player2, board);
    }
}