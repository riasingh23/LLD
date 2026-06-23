package snake.ladder.entity;

public class Dice {
    private int size;

    public Dice(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public int roll() {
        return (int)(Math.random()*(size));
    }
}
