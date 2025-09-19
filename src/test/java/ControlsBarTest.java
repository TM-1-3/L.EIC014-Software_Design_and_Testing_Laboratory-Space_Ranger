package View;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class ControlsBarTest {

    @Mock
    private TextGraphics mockGraphics;

    private ControlsBar controlsBar;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controlsBar = new ControlsBar();
    }

    @Test
    void testDrawStatusBar() {
        // Arrange
        int placeholder = 0;  // The placeholder value doesn't affect the logic in this case

        // Act
        controlsBar.drawStatusBar(mockGraphics, placeholder);

        // Assert
        String expectedText = "↑ : UP    ↓ : DOWN     A : SHOOT LASER     S : RELEASE BOMB     Q : QUIT";
        // Verify that the foreground color was set
        verify(mockGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        // Verify that the text was put at the correct position
        verify(mockGraphics).putString(new TerminalPosition(25, 28), expectedText);
    }
}