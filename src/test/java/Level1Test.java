import Model.*;
import Controller.AsteroidController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Level1Test {

    private Level1 level1;

    @Mock
    private AsteroidController mockAsteroidController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        level1 = Level1.getInstance(100, 50);
    }

    @Test
    void testSingletonInstance() {
        // Arrange & Act
        Level1 anotherInstance = Level1.getInstance(100, 50);

        // Assert
        assertSame(level1, anotherInstance);
    }

    @Test
    void testGetLevelNumber() {
        assertEquals(1, level1.getLevelNumber());
    }

    @Test
    void testGetMaxNumberEnemies() {
        assertEquals(20, level1.getMaxNumberEnemies());
    }

    @Test
    void testCreateEnemy() {
        // Act
        level1.createEnemy();

        // Assert
        assertTrue(level1.getEnemies().size() <= level1.getMaxNumberEnemies());
    }

    @Test
    void testCreateEnemyType() {
        // Act
        Enemy enemy = level1.createEnemyType(25);

        // Assert
        assertNotNull(enemy);
        assertTrue(enemy instanceof EasierEnemy || enemy instanceof AverageEnemy || enemy instanceof HarderEnemy);
    }

    @Test
    void testGetEnemies() {
        // Act
        List<Enemy> enemies = level1.getEnemies();

        // Assert
        assertNotNull(enemies);
    }

    @Test
    void testGetSpawnedEnemies() {
        // Act
        List<Enemy> spawnedEnemies = level1.getSpawnedEnemies();

        // Assert
        assertNotNull(spawnedEnemies);
    }

    @Test
    void testCreateEnemyProjectiles() {
        // Act
        level1.createEnemyProjectiles();

        // Assert
        assertNotNull(level1.getEnemyProjectiles());
    }

    @Test
    void testUpdateAsteroids() {
        // Mock dependencies
        level1 = spy(level1);
        doNothing().when(level1).createAsteroids();

        // Act
        level1.updateAsteroids();

        // Assert
        verify(level1).createAsteroids();
    }

    @Test
    void testGetBackgroundColor() {
        assertEquals("#020838", level1.getBackgroundColor());
    }
}