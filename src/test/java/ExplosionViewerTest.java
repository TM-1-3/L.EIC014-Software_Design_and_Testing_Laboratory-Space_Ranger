package View;

import Model.Explosion;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class ExplosionViewerTest {

    @Mock
    private TextGraphics mockGraphics;

    @Mock
    private Explosion mockExplosion1;

    @Mock
    private Explosion mockExplosion2;

    @Mock
    private Explosion mockExplosion3;

    private ExplosionViewer explosionViewer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        explosionViewer = new ExplosionViewer();
    }

    @Test
    void testDrawWithActiveAndExpiredExplosions() {
        // Arrange
        List<Explosion> explosions = new ArrayList<>(Arrays.asList(mockExplosion1, mockExplosion2, mockExplosion3));

        when(mockExplosion1.duration()).thenReturn(false);
        when(mockExplosion2.duration()).thenReturn(true);
        when(mockExplosion3.duration()).thenReturn(false);

        // Act
        explosionViewer.draw(mockGraphics, explosions);

        // Assert
        // Verify that the draw method is called for all explosions
        verify(mockExplosion1).draw(mockGraphics);
        verify(mockExplosion2).draw(mockGraphics);
        verify(mockExplosion3).draw(mockGraphics);

        // Verify that the expired explosion (mockExplosion2) is removed
        verify(mockExplosion2).duration();
        assert explosions.size() == 2;
        assert explosions.contains(mockExplosion1);
        assert explosions.contains(mockExplosion3);
    }

    @Test
    void testDrawWithNoExplosions() {
        // Arrange
        List<Explosion> explosions = new ArrayList<>();

        // Act
        explosionViewer.draw(mockGraphics, explosions);

        // Assert
        // Ensure no interactions occur
        verifyNoInteractions(mockExplosion1, mockExplosion2, mockExplosion3, mockGraphics);
        assert explosions.isEmpty();
    }

    @Test
    void testDrawWithOnlyExpiredExplosions() {
        // Arrange
        List<Explosion> explosions = new ArrayList<>(Arrays.asList(mockExplosion1, mockExplosion2));

        when(mockExplosion1.duration()).thenReturn(true);
        when(mockExplosion2.duration()).thenReturn(true);

        // Act
        explosionViewer.draw(mockGraphics, explosions);

        // Assert
        // Verify the draw method is called for all explosions
        verify(mockExplosion1).draw(mockGraphics);
        verify(mockExplosion2).draw(mockGraphics);

        // Verify that all explosions are removed
        assert explosions.isEmpty();
    }

    @Test
    void testDrawWithNullGraphics() {
        // Arrange
        List<Explosion> explosions = new ArrayList<>(Arrays.asList(mockExplosion1));

        // Act & Assert
        try {
            explosionViewer.draw(null, explosions);
        } catch (NullPointerException e) {
            // Handle or assert exception if necessary
        }
    }
}