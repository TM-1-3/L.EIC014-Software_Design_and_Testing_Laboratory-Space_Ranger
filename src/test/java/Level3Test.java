import Model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class Level3Test {
    private Level3 level3;
    private int width = 50;
    private int height = 30;

    @BeforeEach
    void setUp() {
        level3 = spy(new Level3(width, height));
    }

    @Test
    void testSingletonInstance() {
        Level2 instance1 = Level2.getInstance(width, height);
        Level2 instance2 = Level2.getInstance(width, height);
        assertSame(instance1, instance2, "Singleton instance should be the same.");
    }

    @Test
    void testGetLevelNumber() {
        assertEquals(3, level3.getLevelNumber(), "Level number should be 3.");
    }

    @Test
    void testGetMaxNumberEnemies() {
        assertEquals(35, level3.getMaxNumberEnemies(), "Max number of enemies should be 25.");
    }


    @Test
    void testCreateEnemyType() {
        // Act
        Enemy enemy = level3.createEnemyType(25);

        // Assert
        assertNotNull(enemy);
        assertTrue(enemy instanceof EasierEnemy || enemy instanceof AverageEnemy || enemy instanceof HarderEnemy);
    }

    @Test
    void testCreateEnemy() {
        level3.createEnemy();
        List<Enemy> enemies = level3.getEnemies();

        assertNotNull(enemies, "Enemies list should not be null.");
        assertTrue(enemies.size() <= level3.getMaxNumberEnemies(), "Enemies list should not exceed max number of enemies.");
    }

    @Test
    void testGetEnemies() {
        assertNotNull(level3.getEnemies(), "Enemies list should not be null.");
    }

    @Test
    void testGetSpawnedEnemies() {
        assertNotNull(level3.getSpawnedEnemies(), "Spawned enemies list should not be null.");
    }

    @Test
    void testCreateEnemyProjectiles() {
        level3.createEnemyProjectiles();
        List<EnemyProjectile> projectiles = level3.getEnemyProjectiles();

        assertNotNull(projectiles, "Enemy projectiles list should not be null.");
    }

    @Test
    void testUpdateAsteroids() {
        level3 = spy(level3);
        doNothing().when(level3).createAsteroids();


        level3.updateAsteroids();

        level3.updateAsteroids();

        verify(level3, atLeastOnce()).createAsteroids();
    }

    @Test
    void testGetEnemyProjectiles() {
        assertNotNull(level3.getEnemyProjectiles(), "Enemy projectiles list should not be null.");
    }

    @Test
    void testBackgroundColor() {
        assertEquals("#250324", level3.getBackgroundColor(), "Background color should match the level's specification.");
    }
}