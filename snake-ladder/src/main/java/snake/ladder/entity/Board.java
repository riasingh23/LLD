package snake.ladder.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private int[][] board;
    private Map<Integer, BoardEntity> boardEntityMap = new HashMap<>();

    public Board(int n, List<BoardEntity> boardEntityList) {
        this.board = new int[n][n];
        for(BoardEntity boardEntity: boardEntityList) {
            boardEntityMap.put(boardEntity.getStart(), boardEntity);
        }
    }

    public int getSize() {
        return board.length * board.length;
    }

    public int makeMove(int position){
        if(boardEntityMap.containsKey(position))
            return boardEntityMap.get(position).getEnd();
        else
            return position;
    }

}
