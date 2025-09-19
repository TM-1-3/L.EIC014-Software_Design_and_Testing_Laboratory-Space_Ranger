import Model.Asteroid;
import Model.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.Mockito.*;

class AsteroidTest {

    @Mock
    private TextGraphics mockGraphics;

    private Asteroid asteroid;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        asteroid = new Asteroid(new Position(7, 10));
    }

    @Test
    void testAsteroidInitialization() {
        // Assert the position is correctly set
        Position position = asteroid.getPosition();
        assert position != null;

    }

    @Test
    void testDraw() throws IOException {
        // Act
        asteroid.draw(mockGraphics);

        // Assert
        verify(mockGraphics).setForegroundColor(TextColor.Factory.fromString("#964B00"));
        verify(mockGraphics).enableModifiers(any());
        verify(mockGraphics).putString(new TerminalPosition(7, 10), "O");
    }
}
