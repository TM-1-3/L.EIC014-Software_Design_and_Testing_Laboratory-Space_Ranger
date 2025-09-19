package View;

import Model.*;
import States.Game;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;

public class LevelViewer {
    private final Screen screen;
    private final ShipViewer shipViewer;
    private final StarViewer starViewer;
    private final ShipProjectileViewer shipProjectileViewer;
    private final BoundaryViewer boundaryViewer;
    private final AsteroidViewer asteroidViewer;
    private final BombViewer bombViewer;
    private final ExplosionViewer explosionViewer;
    private final EnemyViewer enemyViewer;
    private final EnemyProjectileViewer enemyProjectileViewer;
    private final BombBar bombBar;
    private final HealthBar healthBar;
    private final EnemiesBar enemiesBar;
    private final ControlsBar controlsBar;
    private final LevelBar levelBar;

    public LevelViewer(Screen screen) {
        this.screen=screen;
        shipViewer=new ShipViewer();
        starViewer=new StarViewer();
        boundaryViewer = new BoundaryViewer();
        asteroidViewer = new AsteroidViewer();
        shipProjectileViewer = new ShipProjectileViewer();
        bombViewer = new BombViewer();
        explosionViewer = new ExplosionViewer();
        enemyViewer=new EnemyViewer();
        enemyProjectileViewer=new EnemyProjectileViewer();
        bombBar=new BombBar();
        healthBar=new HealthBar();
        enemiesBar=new EnemiesBar();
        controlsBar=new ControlsBar();
        levelBar=new LevelBar();
    }

    public void drawBeforeGameOver(Game game, Level level) throws IOException, InterruptedException {
        game.getSoundPlayer().playSound("src\\main\\resources\\bombExplosion.wav");
        drawLevel(level);
        Thread.sleep(500);
        screen.close();

    }

    public void drawScreen(String soundPath, Game game){
        game.getScreen().clear();
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.ANSI.BLACK);
        graphics.fillRectangle(new TerminalPosition(0, 0), screen.getTerminalSize(), ' ');
        game.getSoundPlayer().playSound(soundPath);
    }

    public void drawLevel(Level level) throws IOException {
        screen.clear();
        level.draw(screen.newTextGraphics());
        bombBar.drawStatusBar(screen.newTextGraphics(), level.getBombsToFire().size());
        healthBar.drawStatusBar(screen.newTextGraphics(), level.getShip().getHealth());
        enemiesBar.drawStatusBar(screen.newTextGraphics(), (level.getMaxNumberEnemies())-(level.getSpawnedEnemies().size()-level.getEnemies().size()));
        controlsBar.drawStatusBar(screen.newTextGraphics(),0);
        levelBar.drawStatusBar(screen.newTextGraphics(), level.getLevelNumber());
        shipViewer.draw(screen.newTextGraphics(), level.getShip());
        starViewer.draw(screen.newTextGraphics(), level.getStars());
        boundaryViewer.draw(screen.newTextGraphics(), level.getBoundaries());
        asteroidViewer.draw(screen.newTextGraphics(), level.getAsteroids());
        shipProjectileViewer.draw(screen.newTextGraphics(), level.getShipProjectiles());
        bombViewer.draw(screen.newTextGraphics(), level.getBombs());
        explosionViewer.draw(screen.newTextGraphics(), level.getExplosions());
        enemyViewer.draw(screen.newTextGraphics(), level.getEnemies());
        enemyProjectileViewer.draw(screen.newTextGraphics(), level.getEnemyProjectiles());
        screen.refresh();
    }
}
