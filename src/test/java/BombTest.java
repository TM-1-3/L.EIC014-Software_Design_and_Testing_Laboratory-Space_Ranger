import Model.Bomb;
import Model.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BombTest {

    @Mock
    private TextGraphics mockGraphics;

    private Bomb bomb;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bomb = new Bomb(new Position(5, 5));
    }

    @Test
    void testGetRadius() {
        // Act
        int radius = bomb.getRadius();

        // Assert
        assertEquals(2, radius, "The radius of the bomb should be 2");
    }

    @Test
    void testGetMaxBombsInLevel() {
        // Act
        int maxBombs = Bomb.getMaxBombsInLevel();

        // Assert
        assertEquals(5, maxBombs, "The maximum number of bombs in a level should be 5");
    }

    @Test
    void testDraw() throws IOException {
        // Arrange
        bomb = new Bomb(new Position(10, 15));

        // Act
        bomb.draw(mockGraphics);

        // Assert
        verify(mockGraphics).setForegroundColor(TextColor.Factory.fromString("#964B00"));
        verify(mockGraphics).enableModifiers(any());
        verify(mockGraphics).putString(new TerminalPosition(10, 15), "Φ");
    }
}
