package Controller;

import Model.Element;
import Model.Level;
import Model.Position;

public abstract class AgainstShipProjectileController extends ProjectileController{

    public AgainstShipProjectileController(Level level) {
        super(level);
    }

    public void moveLeft(Element element){
        element.setPosition(new Position(element.getX()-1,element.getY()));
    }
}
