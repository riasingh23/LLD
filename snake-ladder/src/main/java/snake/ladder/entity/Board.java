package snake.ladder.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private int size;
    private Map<Integer, BoardEntity> boardEntityMap = new HashMap<>();

    public Board(int size, List<BoardEntity> boardEntityList) {
        this.size = size;
        for(BoardEntity boardEntity: boardEntityList) {
            boardEntityMap.put(boardEntity.getStart(), boardEntity);
        }
    }

    public int getSize() {
        return size;
    }

    public int makeMove(int position){
        if(boardEntityMap.containsKey(position))
            return boardEntityMap.get(position).getEnd();
        else
            return position;
    }

}
