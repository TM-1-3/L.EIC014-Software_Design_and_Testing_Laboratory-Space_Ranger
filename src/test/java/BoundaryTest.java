import Model.Boundary;
import Model.Position;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BoundaryTest {
    @Test
    public void testBoundaryCreation() {
        Position position = new Position(10, 20);
        Boundary boundary = new Boundary(position);

        assertEquals(10, boundary.getX());
        assertEquals(20, boundary.getY());
        assertEquals(position, boundary.getPosition());
    }

    @Test
    public void testDrawBoundary() throws IOException {
        Position position = new Position(5, 10);
        Boundary boundary = new Boundary(position);
        TextGraphics mockGraphics = mock(TextGraphics.class);

        boundary.draw(mockGraphics);

        verify(mockGraphics).setForegroundColor(TextColor.Factory.fromString("#808080"));
        verify(mockGraphics).enableModifiers(SGR.BOLD);
        verify(mockGraphics).putString(new TerminalPosition(5, 10), "█");
    }

    @Test
    public void testDrawBoundaryColor() throws IOException {
        Position position = new Position(5, 10);
        Boundary boundary = new Boundary(position);
        TextGraphics mockGraphics = mock(TextGraphics.class);

        boundary.draw(mockGraphics);

        verify(mockGraphics).setForegroundColor(TextColor.Factory.fromString("#808080"));
    }
}
