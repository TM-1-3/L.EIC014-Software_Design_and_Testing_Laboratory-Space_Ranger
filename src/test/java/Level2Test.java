import Model.*;
import Controller.AsteroidController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class Level2Test {
    private Level2 level2;
    private int width = 50;
    private int height = 30;

    @BeforeEach
    void setUp() {
        level2 = spy(new Level2(width, height));
    }

    @Test
    void testSingletonInstance() {
        Level2 instance1 = Level2.getInstance(width, height);
        Level2 instance2 = Level2.getInstance(width, height);
        assertSame(instance1, instance2, "Singleton instance should be the same.");
    }

    @Test
    void testGetLevelNumber() {
        assertEquals(2, level2.getLevelNumber(), "Level number should be 2.");
    }

    @Test
    void testGetMaxNumberEnemies() {
        assertEquals(25, level2.getMaxNumberEnemies(), "Max number of enemies should be 25.");
    }


    @Test
    void testCreateEnemyType() {
        // Act
        Enemy enemy = level2.createEnemyType(25);

        // Assert
        assertNotNull(enemy);
        assertTrue(enemy instanceof EasierEnemy || enemy instanceof AverageEnemy || enemy instanceof HarderEnemy);
    }

    @Test
    void testCreateEnemy() {
        level2.createEnemy();
        List<Enemy> enemies = level2.getEnemies();

        assertNotNull(enemies, "Enemies list should not be null.");
        assertTrue(enemies.size() <= level2.getMaxNumberEnemies(), "Enemies list should not exceed max number of enemies.");
    }

    @Test
    void testGetEnemies() {
        assertNotNull(level2.getEnemies(), "Enemies list should not be null.");
    }

    @Test
    void testGetSpawnedEnemies() {
        assertNotNull(level2.getSpawnedEnemies(), "Spawned enemies list should not be null.");
    }

    @Test
    void testCreateEnemyProjectiles() {
        level2.createEnemyProjectiles();
        List<EnemyProjectile> projectiles = level2.getEnemyProjectiles();

        assertNotNull(projectiles, "Enemy projectiles list should not be null.");
    }

    @Test
    void testUpdateAsteroids() {
        level2 = spy(level2);
        doNothing().when(level2).createAsteroids();


        level2.updateAsteroids();

        level2.updateAsteroids();

        verify(level2, atLeastOnce()).createAsteroids();
    }

    @Test
    void testGetEnemyProjectiles() {
        assertNotNull(level2.getEnemyProjectiles(), "Enemy projectiles list should not be null.");
    }

    @Test
    void testBackgroundColor() {
        assertEquals("#230007", level2.getBackgroundColor(), "Background color should match the level's specification.");
    }
}
