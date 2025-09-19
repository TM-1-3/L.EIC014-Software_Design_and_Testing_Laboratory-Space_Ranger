package Model;

import Sound.SoundPlayer;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class Level {
    private final SoundPlayer soundPlayer;
    private final Random random;
    protected int width;
    protected int height;
    protected Ship ship;
    protected List<ShipProjectile> shipProjectiles;
    protected List<Bomb> bombs;
    protected List<Integer> bombsToFire;
    protected List<Explosion> explosions;
    protected List<Bomb> firedBombs;
    protected List<Asteroid> asteroids;
    protected List<EnemyProjectile> enemyProjectiles;

    public Level(int width, int height) {
        this.width=width;
        this.height=height;
        ship=new Ship(new Position(6,height/2));
        shipProjectiles =new ArrayList<>();
        explosions=new ArrayList<>();
        bombs=new ArrayList<>();
        firedBombs=new ArrayList<>();
        bombsToFire=new ArrayList<>(Arrays.asList(1,2,3,4,5));
        asteroids = new ArrayList<>();
        random=new Random();
        enemyProjectiles=new ArrayList<>();
        soundPlayer=new SoundPlayer();
    }

    public abstract int getLevelNumber();

    public Ship getShip(){
        return ship;
    }

    protected abstract String getBackgroundColor();

    public void draw(TextGraphics graphics) throws IOException{
        for (int y = 0; y < 30; y++) {
            for (int x = 0; x < 120; x++) {
                if (x < 4 || x > 116 || y < 5 || y > 24) {
                    graphics.setBackgroundColor(TextColor.Factory.fromString(getBackgroundColor())); // Background color
                    graphics.putString(new TerminalPosition(x, y), " "); // Draw empty space
                }
            }
        }
    }

    public List<Star> getStars(){
        List<Star> stars=new ArrayList<>();
        Random random=new Random();
        while (stars.size()!=15) {
            int randomX = random.nextInt((114 - 9) + 1) + 9;
            int randomY = random.nextInt((24 - 6) + 1) + 6;
            stars.add(new Star(new Position(randomX, randomY)));
        }
        return stars;
    }

    public List<Boundary> getBoundaries(){
        List<Boundary> boundaries=new ArrayList<>();
        for (int c=3; c<width-4; c++){
            boundaries.add(new Boundary((new Position(c,3))));
            boundaries.add(new Boundary((new Position(c,height-4))));
        }
        for (int m=4; m<width-5; m++){
            boundaries.add(new Boundary(new Position(m,4)));
            boundaries.add(new Boundary((new Position(m,height-5))));
        }
        for (int r=3; r<height-3; r++){
            boundaries.add(new Boundary(new Position(3,r)));
            boundaries.add(new Boundary(new Position(width-4,r)));
        }
        for (int n=4; n<height-4; n++){
            boundaries.add(new Boundary(new Position(4,n)));
            boundaries.add(new Boundary(new Position(width-5,n)));
        }
        return boundaries;
    }

    public void createProjectile() {
        soundPlayer.playSound("src\\main\\resources\\shipLaser.wav");
        shipProjectiles.add(new ShipProjectile(ship.getPosition()));
    }

    public List<ShipProjectile> getShipProjectiles(){
        return shipProjectiles;
    }

    public void createBomb(){
        firedBombs.add(new Bomb(ship.getPosition()));
        if (firedBombs.size()<=5) {
            soundPlayer.playSound("src\\main\\resources\\bombFiring.wav");
            bombs.add(new Bomb(ship.getPosition()));
            bombsToFire.removeFirst();
        }
    }

    public List<Bomb> getBombs(){
        return bombs;
    }

    public List<Integer> getBombsToFire(){return bombsToFire;}

    public List<Explosion> getExplosions(){
        return explosions;
    }

    public void createAsteroids(){
        asteroids.add(new Asteroid(new Position(width - 6, random.nextInt(5, height - 5))));
    }

    public List<Asteroid> getAsteroids(){
        return asteroids;
    }

    public abstract int getMaxNumberEnemies();

    public abstract void createEnemy();

    public abstract void updateAsteroids();

    public abstract Enemy createEnemyType(int y);

    public abstract List<Enemy> getEnemies();

    public abstract List<Enemy> getSpawnedEnemies();

    public abstract void createEnemyProjectiles();

    public List<EnemyProjectile> getEnemyProjectiles(){
        return enemyProjectiles;
    }
}