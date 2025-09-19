import Controller.AsteroidController;
import Model.Asteroid;
import Model.Level;
import Model.Position;
import Model.Ship;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AsteroidControllerTest {
    @Test
    void testConstructorInitializesMovementCooldown() {
        Level mockLevel = mock(Level.class);
        AsteroidController asteroidController = new AsteroidController(mockLevel);
        
        // Use reflection to access the private field
        try {
            Field movementCooldownField = AsteroidController.class.getDeclaredField("movementCooldown");
            movementCooldownField.setAccessible(true);
            int movementCooldown = (int) movementCooldownField.get(asteroidController);
            
            assertEquals(1, movementCooldown, "movementCooldown should be initialized to MAX_COOLDOWN (1)");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Failed to access movementCooldown field: " + e.getMessage());
        }
    }
    @Test
    void testMoveAsteroidsLeft() {
        Level mockLevel = mock(Level.class);
        AsteroidController asteroidController = new AsteroidController(mockLevel);
        List<Asteroid> asteroids = new ArrayList<>();
        Asteroid asteroid1 = new Asteroid(new Position(10, 5));
        Asteroid asteroid2 = new Asteroid(new Position(15, 8));
        asteroids.add(asteroid1);
        asteroids.add(asteroid2);

        Ship mockShip = mock(Ship.class);
        when(mockShip.getX()).thenReturn(0);
        when(mockLevel.getAsteroids()).thenReturn(asteroids);
        when(mockLevel.getShip()).thenReturn(mockShip);

        // Set movementCooldown to 0
        for (int i = 0; i < AsteroidController.MAX_COOLDOWN; i++) {
            asteroidController.move();
        }

        // Move asteroids
        asteroidController.move();

        // Verify asteroids moved left
        assertEquals(9, asteroid1.getX());
        assertEquals(14, asteroid2.getX());
    }
}
