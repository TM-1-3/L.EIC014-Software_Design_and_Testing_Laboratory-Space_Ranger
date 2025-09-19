package Model;

public abstract class LiveElement extends Element{
    private int health;

    public LiveElement(Position position, int health) {
        super(position);
        this.health = health;
    }

    public void decreaseHealth() {
        health--;
    }

    public int getHealth() {
        return health;
    }
}
