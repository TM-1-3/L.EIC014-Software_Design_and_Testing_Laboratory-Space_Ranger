package Controller;

import Model.*;
import java.util.ArrayList;
import java.util.List;

public class AsteroidController extends AgainstShipProjectileController {
    private int movementCooldown;
    public static final int MAX_COOLDOWN = 1;

    public AsteroidController(Level level) {
        super(level);
        movementCooldown = MAX_COOLDOWN;
    }

    @Override
    public void move(){
        List<Asteroid> outOffBoundsAsteroid = new ArrayList<>();
        if (movementCooldown > 0) {
            movementCooldown--;
            return;
        }
        for (Asteroid asteroid : level.getAsteroids()) {
            moveLeft(asteroid);
            if (asteroid.getX()<level.getShip().getX()){
                outOffBoundsAsteroid.add(asteroid);
            }
        }
        movementCooldown = MAX_COOLDOWN;
        level.getAsteroids().removeAll(outOffBoundsAsteroid);
    }

    @Override
    public void treatCollisions(){
        for (Asteroid asteroid : level.getAsteroids()){
            if (checkCollisionWithShip(asteroid)){
                level.getExplosions().add(new Explosion(asteroid.getPosition()));
                soundPlayer.playSound("src\\main\\resources\\bombExplosion.wav");
                while (level.getShip().getHealth()!=0){
                    level.getShip().decreaseHealth();
                }
            }
        }
    }

    public boolean checkCollisionWithShip(Asteroid asteroid){
        return level.getShip().getPosition().equals(asteroid.getPosition());
    }
}
