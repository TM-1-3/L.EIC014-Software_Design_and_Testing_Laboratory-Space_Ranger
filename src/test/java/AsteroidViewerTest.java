package View;

import Model.Asteroid;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

class AsteroidViewerTest {

    @Test
    void testDrawWithEmptyList() throws IOException {
        // Arrange
        AsteroidViewer asteroidViewer = new AsteroidViewer();
        TextGraphics graphics = mock(TextGraphics.class);
        List<Asteroid> emptyAsteroids = Collections.emptyList();

        // Act
        asteroidViewer.draw(graphics, emptyAsteroids);

        // Assert
        // Verify that no interactions with graphics occurred
        verifyNoInteractions(graphics);
    }

    @Test
    void testDrawWithMultipleAsteroids() throws IOException {
        // Arrange
        AsteroidViewer asteroidViewer = new AsteroidViewer();
        TextGraphics graphics = mock(TextGraphics.class);

        Asteroid asteroid1 = mock(Asteroid.class);
        Asteroid asteroid2 = mock(Asteroid.class);
        Asteroid asteroid3 = mock(Asteroid.class);

        List<Asteroid> asteroids = Arrays.asList(asteroid1, asteroid2, asteroid3);

        // Act
        asteroidViewer.draw(graphics, asteroids);

        // Assert
        // Verify that each asteroid's draw method was called once with the graphics object
        verify(asteroid1).draw(graphics);
        verify(asteroid2).draw(graphics);
        verify(asteroid3).draw(graphics);

        // Verify no unexpected interactions occurred
        verifyNoMoreInteractions(asteroid1, asteroid2, asteroid3, graphics);
    }
}