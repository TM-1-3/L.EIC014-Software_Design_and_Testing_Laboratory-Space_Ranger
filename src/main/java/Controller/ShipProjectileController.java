package Controller;
import Model.*;

import java.util.*;

public class ShipProjectileController extends AgainstEnemiesProjectileController {

    public ShipProjectileController(Level level) {
        super(level);
    }

    @Override
    public void move() {
        List<ShipProjectile> outOffBoundsProjectile =new ArrayList<>();
        for (ShipProjectile projectile : level.getShipProjectiles()) {
            moveRight(projectile);
            if (projectile.getX() >= 115) {
                outOffBoundsProjectile.add(projectile);
            }
        }
        level.getShipProjectiles().removeAll(outOffBoundsProjectile);
    }

    @Override
    public void treatCollisions(){
        List<ShipProjectile> destroyedProjectiles=new ArrayList<>();
        List<Enemy> killedEnemies=new ArrayList<>();
        for (ShipProjectile projectile : level.getShipProjectiles()) {
            for (EnemyProjectile enemyProjectile : level.getEnemyProjectiles()){
                if (checkCollisionWithEnemyProjectile(enemyProjectile, projectile)){
                    destroyedProjectiles.add(projectile);
                }
            }
            for (Asteroid asteroid : level.getAsteroids()){
                if (checkCollisionWithAsteroid(projectile, asteroid)){
                    destroyedProjectiles.add(projectile);
                }
            }
            for (Enemy enemy : level.getEnemies()){
                if (checkCollisionWithEnemy(projectile,enemy)){
                    enemy.decreaseHealth();
                    if (enemy.getHealth()==0) {
                        level.getExplosions().add(new Explosion(projectile.getPosition()));
                        soundPlayer.playSound("src\\main\\resources\\bombExplosion.wav");
                        killedEnemies.add(enemy);
                    }
                    destroyedProjectiles.add(projectile);
                }
            }
        }
        level.getEnemies().removeAll(killedEnemies);
        level.getShipProjectiles().removeAll(destroyedProjectiles);
    }

    public boolean checkCollisionWithEnemy(ShipProjectile shipProjectile, Enemy enemy){
        return shipProjectile.getPosition().equals(enemy.getPosition());
    }

    public boolean checkCollisionWithAsteroid(ShipProjectile shipProjectile, Asteroid asteroid) {
        return shipProjectile.getPosition().equals(asteroid.getPosition());
    }

    public boolean checkCollisionWithEnemyProjectile(EnemyProjectile enemyProjectile, ShipProjectile shipProjectile) {
        return enemyProjectile.getPosition().equals(shipProjectile.getPosition());
    }
}
