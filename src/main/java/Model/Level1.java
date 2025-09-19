package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import Controller.AsteroidController;

public class Level1 extends Level {
    private int spawnCooldown;
    private final Random random;
    private static Level1 instance;
    private final AsteroidController asteroidController;
    private final List<Enemy> enemies;
    private final List<Enemy> spawnedEnemies;
    private final List<EnemyProjectile> enemyProjectiles;
    private long lastEnemyProjectileTime;
    private long lastSpawnTime;
    private static final long PROJECTILE_DELAY=1000;
    private static final long SPAWN_DELAY = 1500;

    public Level1(int width, int height){
        super(width, height);
        random = new Random();
        spawnCooldown = random.nextInt(200)+10;
        asteroidController=new AsteroidController(this);
        enemies=new ArrayList<>();
        spawnedEnemies=new ArrayList<>();
        enemyProjectiles=new ArrayList<>();
        lastEnemyProjectileTime=0;
        lastSpawnTime=System.currentTimeMillis();
    }

    public static Level1 getInstance(int width, int height){
        if (instance == null){
            instance = new Level1(width, height);
        }
        return instance;
    }

    @Override
    public int getLevelNumber(){
        return 1;
    }

    @Override
    public int getMaxNumberEnemies(){
        return 20;
    }

    @Override
    public void createEnemy() {
        long currentTime = System.currentTimeMillis();
        if (spawnedEnemies.size() < getMaxNumberEnemies() && currentTime - lastSpawnTime >= SPAWN_DELAY) {
            if (random.nextInt(100) < 5) {
                int spawnY = new Random().nextInt(5, height - 5);
                Enemy newEnemy = createEnemyType(spawnY);
                enemies.add(newEnemy);
                spawnedEnemies.add(newEnemy);
                lastSpawnTime = currentTime;
            }
        }
    }

    @Override
    public Enemy createEnemyType(int spawnY) {
        int roll = new Random().nextInt(10);
        if (roll % 2 == 0) return new EasierEnemy(new Position(width - 6, spawnY));
        else if (roll == 3 || roll == 5 || roll == 7) return new AverageEnemy(new Position(width - 6, spawnY));
        else return new HarderEnemy(new Position(width - 6, spawnY));
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
            spawnCooldown = random.nextInt(200)+10;
        } else {
            spawnCooldown--;
        }
        asteroidController.move();
    }

    @Override
    public String getBackgroundColor() {
        return "#020838";
    }
}






