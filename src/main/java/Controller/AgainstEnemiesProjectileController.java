package Controller;

import Model.Level;
import Model.Element;
import Model.Position;

public abstract class AgainstEnemiesProjectileController extends ProjectileController{

    public AgainstEnemiesProjectileController(Level level){
        super(level);
    }

    public void moveRight(Element element){
        element.setPosition(new Position(element.getX()+1, element.getY()));
    }
}
