import Model.Position;
import Model.Star;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class StarTest {
    private Star star;
    private TextGraphics mockGraphics;

    @BeforeEach
    void setUp() {
        // Use a real Position object
        Position position = new Position(5, 10);
        star = new Star(position);

        // Create a mock TextGraphics object
        mockGraphics = mock(TextGraphics.class);
    }

    @Test
    void testGetPosition() {
        // Verify the Star object uses the Position correctly
        assert star.getX() == 5;
        assert star.getY() == 10;
    }

    @Test
    void testDraw() throws Exception {
        // Call the draw method
        star.draw(mockGraphics);

        // Verify that the correct color is set
        verify(mockGraphics).setForegroundColor(TextColor.Factory.fromString("#F3EB92"));

        // Verify that the star is drawn at the correct position
        verify(mockGraphics).putString(new TerminalPosition(5, 10), ".");
    }

    @Test
    void testSetPosition() {
        // Use a new real Position object
        Position newPosition = new Position(20, 30);

        // Set the new position
        star.setPosition(newPosition);

        // Verify the position is updated
        assert star.getX() == 20;
        assert star.getY() == 30;
    }
}