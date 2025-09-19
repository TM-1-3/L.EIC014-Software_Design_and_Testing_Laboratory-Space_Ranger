package View;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class BombBarTest {

    @Mock
    private TextGraphics mockGraphics;

    private BombBar bombBar;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bombBar = new BombBar();
    }

    @Test
    void testDrawStatusBar() {
        // Arrange
        int bombsAvailable = 5;

        // Act
        bombBar.drawStatusBar(mockGraphics, bombsAvailable);

        // Assert
        verify(mockGraphics, times(1)).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        verify(mockGraphics, times(1)).putString(new TerminalPosition(2, 0), "Bombs: " + bombsAvailable);
    }

    @Test
    void testDrawStatusBarZeroBombs() {
        // Arrange
        int bombsAvailable = 0;

        // Act
        bombBar.drawStatusBar(mockGraphics, bombsAvailable);

        // Assert
        verify(mockGraphics, times(1)).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        verify(mockGraphics, times(1)).putString(new TerminalPosition(2, 0), "Bombs: " + bombsAvailable);
    }

    @Test
    void testDrawStatusBarNegativeBombs() {
        // Arrange
        int bombsAvailable = -3;

        // Act
        bombBar.drawStatusBar(mockGraphics, bombsAvailable);

        // Assert
        verify(mockGraphics, times(1)).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        verify(mockGraphics, times(1)).putString(new TerminalPosition(2, 0), "Bombs: " + bombsAvailable);
    }
}