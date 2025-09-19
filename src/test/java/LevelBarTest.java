package View;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.TerminalPosition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class LevelBarTest {

    private LevelBar levelBar;
    private TextGraphics mockGraphics;

    @BeforeEach
    void setUp() {
        levelBar = new LevelBar();
        mockGraphics = mock(TextGraphics.class);
    }

    @Test
    void testDrawStatusBar() {
        // Arrange
        int numberLevel = 5;

        // Act
        levelBar.drawStatusBar(mockGraphics, numberLevel);

        // Assert
        verify(mockGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        ArgumentCaptor<String> textCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockGraphics).putString(eq(new TerminalPosition(55, 1)), textCaptor.capture());
        assertEquals("LEVEL 5", textCaptor.getValue());
    }
}
