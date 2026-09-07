package snake.ladder.entity;

public class Ladder extends BoardEntity{
    public Ladder(int start, int end) {
        if(start>=end) throw new IllegalArgumentException("start>=end");
        super(start, end);
    }
}
