package View;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class EnemiesBarTest {

    @Mock
    private TextGraphics mockGraphics;

    private EnemiesBar enemiesBar;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        enemiesBar = new EnemiesBar();
    }

    @Test
    void testDrawStatusBar() {
        // Arrange
        int remainingEnemies = 5; // Example of remaining enemies

        // Act
        enemiesBar.drawStatusBar(mockGraphics, remainingEnemies);

        // Assert
        String expectedText = "☠ Remaining: " + remainingEnemies;
        // Verify that the foreground color was set to white
        verify(mockGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        // Verify that the correct string was put at the correct position (100, 1)
        verify(mockGraphics).putString(new TerminalPosition(100, 1), expectedText);
    }

    @Test
    void testDrawStatusBarWithZeroEnemies() {
        // Arrange
        int remainingEnemies = 0; // Edge case: no remaining enemies

        // Act
        enemiesBar.drawStatusBar(mockGraphics, remainingEnemies);

        // Assert
        String expectedText = "☠ Remaining: " + remainingEnemies;
        verify(mockGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        verify(mockGraphics).putString(new TerminalPosition(100, 1), expectedText);
    }
}
