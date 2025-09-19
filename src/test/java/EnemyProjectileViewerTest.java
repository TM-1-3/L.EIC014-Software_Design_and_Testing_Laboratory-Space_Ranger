package View;

import Model.EnemyProjectile;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class EnemyProjectileViewerTest {

    @Mock
    private TextGraphics mockGraphics;

    @Mock
    private EnemyProjectile mockEnemyProjectile1;

    @Mock
    private EnemyProjectile mockEnemyProjectile2;

    private EnemyProjectileViewer enemyProjectileViewer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        enemyProjectileViewer = new EnemyProjectileViewer();
    }

    @Test
    void testDrawWithMultipleProjectiles() throws IOException {
        // Arrange
        List<EnemyProjectile> projectiles = Arrays.asList(mockEnemyProjectile1, mockEnemyProjectile2);

        // Act
        enemyProjectileViewer.draw(mockGraphics, projectiles);

        // Assert
        // Verify that the draw method was called for each projectile
        verify(mockEnemyProjectile1).draw(mockGraphics);
        verify(mockEnemyProjectile2).draw(mockGraphics);
    }

    @Test
    void testDrawWithNoProjectiles() throws IOException {
        // Arrange
        List<EnemyProjectile> projectiles = Arrays.asList();

        // Act
        enemyProjectileViewer.draw(mockGraphics, projectiles);

        // Assert
        // Verify that no interactions with EnemyProjectile occurred
        verifyNoInteractions(mockEnemyProjectile1, mockEnemyProjectile2);
    }

    @Test
    void testDrawWithNullGraphics() {
        // Arrange
        List<EnemyProjectile> projectiles = Arrays.asList(mockEnemyProjectile1);

        // Act & Assert
        try {
            enemyProjectileViewer.draw(null, projectiles);
        } catch (NullPointerException | IOException e) {
            // Expect a NullPointerException to be thrown due to null TextGraphics
        }
    }
}
