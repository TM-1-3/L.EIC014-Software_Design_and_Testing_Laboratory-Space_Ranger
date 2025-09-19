package Controller;
import Model.Level;
import Model.Position;

public class ShipController {
    public Level level;

    public ShipController(Level level) {
        this.level = level;
    }

    public Position moveUp() {
        return new Position(level.getShip().getX(), level.getShip().getY()-1);
    }

    public Position moveDown() {
        return new Position(level.getShip().getX(), level.getShip().getY()+1);
    }

    public boolean canShipMove(Position position) {
        return position.y() < 25 && position.y() > 4;
    }
}
