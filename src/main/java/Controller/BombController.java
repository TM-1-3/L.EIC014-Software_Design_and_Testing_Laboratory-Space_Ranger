package Controller;

import Model.*;
import java.util.ArrayList;
import java.util.List;

public class BombController extends AgainstEnemiesProjectileController {

    public BombController(Level level) {
        super(level);
    }

    @Override
    public void move() {
        List<Bomb> outOfBoundsBomb = new ArrayList<>();
        for (Bomb bomb : level.getBombs()) {
            moveRight(bomb);
            if (bomb.getX() >= 115) {
                outOfBoundsBomb.add(bomb);
            }
        }
        level.getBombs().removeAll(outOfBoundsBomb);
    }

    @Override
    public void treatCollisions(){
        List<Enemy> hitEnemy = new ArrayList<>();
        for (Bomb bomb : level.getBombs()) {
            for (Enemy enemy : level.getEnemies()) {
                if (checkCollisionWithEnemy(bomb,enemy)){
                    level.getExplosions().add(new Explosion(enemy.getPosition()));
                    soundPlayer.playSound("src\\main\\resources\\bombExplosion.wav");
                    hitEnemy.add(enemy);
                }
            }
        }
        level.getEnemies().removeAll(hitEnemy);
    }

    public boolean checkCollisionWithEnemy(Bomb bomb,Enemy enemy) {
        return enemy.getX()-2<=bomb.getX() && enemy.getX()+2>=bomb.getX() && enemy.getY()-2<=bomb.getY() && enemy.getY()+2>=bomb.getY();
    }
}
