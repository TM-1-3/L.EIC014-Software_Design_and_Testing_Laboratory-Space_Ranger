package View;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class HealthBarTest {

    private HealthBar healthBar;
    private TextGraphics mockGraphics;

    @BeforeEach
    void setUp() {
        healthBar = new HealthBar();
        mockGraphics = mock(TextGraphics.class);
    }

    @Test
    void testDrawStatusBarFullHealth() {
        // Arrange
        int remainingHealth = 3;

        // Act
        healthBar.drawStatusBar(mockGraphics, remainingHealth);

        // Assert
        verify(mockGraphics).setForegroundColor(TextColor.Factory.fromString("#00ff04"));
        ArgumentCaptor<String> textCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockGraphics).putString(eq(2), eq(1), textCaptor.capture());
        assertEquals("Health:  ♥  ♥  ♥ ", textCaptor.getValue());
    }

    @Test
    void testDrawStatusBarPartialHealth() {
        // Arrange
        int remainingHealth = 2;

        // Act
        healthBar.drawStatusBar(mockGraphics, remainingHealth);

        // Assert
        verify(mockGraphics).setForegroundColor(TextColor.Factory.fromString("#00ff04"));
        ArgumentCaptor<String> textCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockGraphics).putString(eq(2), eq(1), textCaptor.capture());
        assertEquals("Health:  ♥  ♥  ", textCaptor.getValue());
    }

    @Test
    void testDrawStatusBarNoHealth() {
        // Arrange
        int remainingHealth = 0;

        // Act
        healthBar.drawStatusBar(mockGraphics, remainingHealth);

        // Assert
        verify(mockGraphics).setForegroundColor(TextColor.Factory.fromString("#00ff04"));
        ArgumentCaptor<String> textCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockGraphics).putString(eq(2), eq(1), textCaptor.capture());
        assertEquals("Health:    ", textCaptor.getValue());
    }
}
