package Controller;
import Model.*;
import java.util.*;


public class EnemyController {
    public Level level;
    private final Map<Enemy, Integer> cooldowns;
    private static final int MAX_COOLDOWN = 55;

    public EnemyController(Level level){
        this.level=level;
        cooldowns=new HashMap<>();
    }

    public void moveEnemies() {
        for (Enemy enemy : level.getEnemies()) {
            int cooldown = cooldowns.getOrDefault(enemy,0);
            if (cooldown>0){
                cooldowns.put(enemy, cooldown-1);
                continue;
            }
            Position newEnemyPosition=null;
            int direction = new Random().nextInt(3);
            switch (direction) {
                case 0:
                    newEnemyPosition = new Position(enemy.getX()-1, enemy.getY() - 1);
                    break;
                case 1:
                    newEnemyPosition = new Position(enemy.getX()-1, enemy.getY() + 1);
                    break;
                case 2:
                    newEnemyPosition = new Position(enemy.getX() - 1, enemy.getY());
                    break;
                default:
                    break;
            }
            if (canEnemyMove(newEnemyPosition)){
                enemy.setPosition(newEnemyPosition);
            }
            cooldowns.put(enemy, MAX_COOLDOWN);
        }
    }

    public boolean canEnemyMove(Position newEnemyPosition){
        return newEnemyPosition.y()<25 && newEnemyPosition.y()>5 && newEnemyPosition.x()>60;
    }
}
