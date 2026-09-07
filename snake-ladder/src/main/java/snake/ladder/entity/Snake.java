package snake.ladder.entity;

public class Snake extends BoardEntity{
    public Snake(int start, int end) {
        if(start<=end) throw new IllegalArgumentException("start<=end");
        super(start, end);
    }
}