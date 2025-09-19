import Model.Explosion;
import Model.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ExplosionTest {

    @Mock
    private TextGraphics mockGraphics;

    private Explosion explosion;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        explosion = new Explosion(new Position(5, 5));
    }

    @Test
    void testExplosionInitialization() {
        // Assert position is correctly initialized
        Position position = explosion.getPosition();
        assert position != null;

    }

    @Test
    void testDraw() {
        // Act
        explosion.draw(mockGraphics);

        // Assert
        verify(mockGraphics).setForegroundColor(TextColor.Factory.fromString("#FFA500"));
        verify(mockGraphics).enableModifiers(any());
        verify(mockGraphics).putString(new TerminalPosition(5, 5), "O");
        verify(mockGraphics).putString(new TerminalPosition(6, 5), "O");
        verify(mockGraphics).putString(new TerminalPosition(4, 5), "O");
        verify(mockGraphics).putString(new TerminalPosition(5, 6), "O");
        verify(mockGraphics).putString(new TerminalPosition(5, 4), "O");
    }

    @Test
    void testDuration() {
        // Act & Assert
        for (int i = 0; i < 9; i++) {
            assertFalse(explosion.duration(), "Explosion should still be active.");
        }
        assertTrue(explosion.duration(), "Explosion should end after 10 calls to duration().");
    }
}
