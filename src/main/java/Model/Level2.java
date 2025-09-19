package Model;

import Controller.AsteroidController;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Level2 extends Level {
    private int spawnCooldown;
    private final Random random;
    private static Level2 instance;
    private final AsteroidController asteroidController;
    private final List<Enemy> enemies;
    private final List<Enemy> spawnedEnemies;
    private final List<EnemyProjectile> enemyProjectiles;
    private long lastSpawnTime;
    private long lastEnemyProjectileTime;
    private static final long PROJECTILE_DELAY=1000;
    private static final long SPAWN_DELAY = 2500;


    public Level2(int width, int height){
        super(width, height);
        random=new Random();
        spawnCooldown = random.nextInt(50)+10;
        asteroidController=new AsteroidController(this);
        enemies=new ArrayList<>();
        spawnedEnemies=new ArrayList<>();
        enemyProjectiles=new ArrayList<>();
        lastSpawnTime = System.currentTimeMillis();
    }

    public static Level2 getInstance(int width, int height){
        if (instance == null){
            instance = new Level2(width, height);
        }
        return instance;
    }

    @Override
    public int getLevelNumber(){
        return 2;
    }

    @Override
    public int getMaxNumberEnemies(){
        return 25;
    }

    @Override
    public Enemy createEnemyType(int spawnY) {
        int roll = random.nextInt(10);
        if (roll % 3 == 0 && roll!=6) return new EasierEnemy(new Position(width - 6, spawnY));
        else if (roll%2==0) return new AverageEnemy(new Position(width - 6, spawnY));
        else return new HarderEnemy(new Position(width - 6, spawnY));
    }

    @Override
    public void createEnemy() {
        long currentTime = System.currentTimeMillis();
        if (spawnedEnemies.size() < getMaxNumberEnemies() && currentTime - lastSpawnTime >= SPAWN_DELAY) {
            if (new Random().nextInt(100) < 5) {
                int spawnY = new Random().nextInt(5, height - 5);
                Enemy newEnemy = createEnemyType(spawnY);
                enemies.add(newEnemy);
                spawnedEnemies.add(newEnemy);
                lastSpawnTime = currentTime;
            }
        }
    }

    @Override
    public List<Enemy> getEnemies(){
        return enemies;
    }

    @Override
    public List<Enemy> getSpawnedEnemies() {
        return spawnedEnemies;
    }

    @Override
    public void createEnemyProjectiles(){
        long currentTime = System.currentTimeMillis();
        if (currentTime-lastEnemyProjectileTime>=PROJECTILE_DELAY){
            for (Enemy enemy: enemies) {
                if (new Random().nextInt(25)<5) {
                    enemyProjectiles.add(new EnemyProjectile(enemy.getPosition()));
                }
            }
            lastEnemyProjectileTime=currentTime;
        }
    }

    @Override
    public List<EnemyProjectile> getEnemyProjectiles(){
        return enemyProjectiles;
    }

    @Override
    public void updateAsteroids(){
        if (spawnCooldown<=0){
            createAsteroids();
            spawnCooldown = random.nextInt(100)+10;
        } else {
            spawnCooldown--;
        }
        asteroidController.move();
    }

    @Override
    public String getBackgroundColor() {
        return "#230007";
    }
}
