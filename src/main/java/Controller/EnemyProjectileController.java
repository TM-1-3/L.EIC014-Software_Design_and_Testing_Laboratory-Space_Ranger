package Controller;

import Model.*;
import java.util.ArrayList;
import java.util.List;

public class EnemyProjectileController extends AgainstShipProjectileController{

    public EnemyProjectileController(Level level) {
        super(level);
    }

    @Override
    public void move(){
        List<EnemyProjectile> outOffBoundsEnemyProjectile =new ArrayList<>();
        for (EnemyProjectile enemyProjectile : level.getEnemyProjectiles()) {
            moveLeft(enemyProjectile);
            if (enemyProjectile.getX() < level.getShip().getX()) {
                outOffBoundsEnemyProjectile.add(enemyProjectile);
            }
        }
        level.getEnemyProjectiles().removeAll(outOffBoundsEnemyProjectile);
    }

    @Override
    public void treatCollisions() {
        List<EnemyProjectile> hitEnemyProjectile=new ArrayList<>();
        for (EnemyProjectile enemyProjectile : level.getEnemyProjectiles()) {
            if (checkCollisionWithShip(enemyProjectile)){
                level.getExplosions().add(new Explosion(enemyProjectile.getPosition()));
                soundPlayer.playSound("src\\main\\resources\\explosion.wav");
                level.getShip().decreaseHealth();
            }
            for (ShipProjectile projectile : level.getShipProjectiles()) {
                if (checkCollisionWithShipProjectile(projectile,enemyProjectile)) {
                    level.getExplosions().add(new Explosion(enemyProjectile.getPosition()));
                    soundPlayer.playSound("src\\main\\resources\\explosion.wav");
                    hitEnemyProjectile.add(enemyProjectile);
                }
            }
        }
        level.getEnemyProjectiles().removeAll(hitEnemyProjectile);
    }

    public boolean checkCollisionWithShipProjectile(ShipProjectile shipProjectile, EnemyProjectile enemyProjectile) {
        return shipProjectile.getPosition().equals(enemyProjectile.getPosition());
    }

    public boolean checkCollisionWithShip(EnemyProjectile enemyProjectile){
        return level.getShip().getPosition().equals(enemyProjectile.getPosition());
    }
}
