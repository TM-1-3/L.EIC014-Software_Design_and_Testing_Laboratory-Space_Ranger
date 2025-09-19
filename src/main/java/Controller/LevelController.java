package Controller;

import Model.Level;
import Model.Position;
import States.Game;
import View.LevelViewer;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import java.io.IOException;

public class LevelController {
    private final Level level;
    private final AsteroidController asteroidController;
    private final BombController bombController;
    private final ShipProjectileController shipProjectileController;
    private final ShipController shipController;
    private final EnemyController enemyController;
    private final EnemyProjectileController enemyProjectileController;
    private static final int FPS = 60;

    public boolean runLevel(Level level, Game game, String soundPath) {
        try {
            new LevelViewer(game.getScreen()).drawScreen(soundPath,game);
            long lastUpdateTime = System.nanoTime();
            long targetTime = 1000000000 / FPS;
            while (true) {
                long now = System.nanoTime();
                long elapsedTime = now - lastUpdateTime;
                level.createEnemy();
                level.createEnemyProjectiles();
                if (elapsedTime > targetTime) {
                    updateGame();
                    level.updateAsteroids();
                    if (level.getShip().getHealth() == 0) {
                        new LevelViewer(game.getScreen()).drawBeforeGameOver(game,level);
                        return false;
                    }
                    if (level.getEnemies().isEmpty() && level.getSpawnedEnemies().size() == level.getMaxNumberEnemies()) {
                        return true;
                    }
                    new LevelViewer(game.getScreen()).drawLevel(level);
                    lastUpdateTime = now;
                }
                KeyStroke key = game.getScreen().pollInput();
                if (key != null) {
                    if ((key.getKeyType() == KeyType.Character && (key.getCharacter() == 'q' || key.getCharacter()=='Q'))) {
                        game.getScreen().close();
                        return false;
                    } else if (key.getKeyType() == KeyType.EOF) {
                        return false;
                    } else {
                        processKey(key);
                    }
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                game.getSoundPlayer().stopSound();
            } catch (Exception ignored) {}
        }
        return false;
    }

    public LevelController(Level level) {
        this.level = level;
        asteroidController = new AsteroidController(level);
        bombController=new BombController(level);
        shipProjectileController =new ShipProjectileController(level);
        shipController=new ShipController(level);
        enemyController=new EnemyController(level);
        enemyProjectileController=new EnemyProjectileController(level);
    }

    public void processKey(KeyStroke key){
        Position newPosition=null;
        if (key==null){
            return;
        }
        switch (key.getKeyType()){
            case ArrowUp:
                newPosition=shipController.moveUp();
                break;
            case ArrowDown:
                newPosition=shipController.moveDown();
                break;
            case Character:
                if (key.getCharacter()=='a' || key.getCharacter()=='A'){
                    level.createProjectile();
                }
                else if (key.getCharacter()=='s' || key.getCharacter()=='S'){
                    level.createBomb();
                }
                break;
        }
        if (newPosition!=null && shipController.canShipMove(newPosition)){
            level.getShip().setPosition(newPosition);
        }
    }

    public void updateGame() {
        asteroidController.move();
        asteroidController.treatCollisions();
        bombController.move();
        bombController.treatCollisions();
        shipProjectileController.move();
        shipProjectileController.treatCollisions();
        enemyController.moveEnemies();
        enemyProjectileController.move();
        enemyProjectileController.treatCollisions();
    }
}
