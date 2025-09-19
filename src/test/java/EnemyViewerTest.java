import Model.Enemy;
import View.EnemyViewer;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class EnemyViewerTest {

    @Mock
    private TextGraphics mockGraphics;

    @Mock
    private Enemy mockEnemy1;

    @Mock
    private Enemy mockEnemy2;

    private EnemyViewer enemyViewer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        enemyViewer = new EnemyViewer();
    }

    @Test
    void testDrawWithMultipleEnemies() throws IOException {
        // Arrange
        List<Enemy> enemies = Arrays.asList(mockEnemy1, mockEnemy2);

        // Act
        enemyViewer.draw(mockGraphics, enemies);

        // Assert
        // Verify that the draw method is called for each enemy
        Mockito.verify(mockEnemy1).draw(mockGraphics);
        Mockito.verify(mockEnemy2).draw(mockGraphics);
    }

    @Test
    void testDrawWithNoEnemies() throws IOException {
        // Arrange
        List<Enemy> enemies = Collections.emptyList();

        // Act
        enemyViewer.draw(mockGraphics, enemies);

        // Assert
        // Ensure no interactions with Enemy instances occurred
        Mockito.verifyNoInteractions(mockEnemy1, mockEnemy2);
    }

    @Test
    void testDrawWithNullGraphics() {
        // Arrange
        List<Enemy> enemies = Arrays.asList(mockEnemy1);

        // Act & Assert
        try {
            enemyViewer.draw(null, enemies);
        } catch (NullPointerException | IOException e) {
            // Verify exception handling if necessary
        }
    }
}