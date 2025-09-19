import Model.*;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ProjectileTest {
    @Test
    void testShipProjectileCreation() {
        Position position = new Position(10, 20);
        ShipProjectile projectile = new ShipProjectile(position);

        assertEquals(10, projectile.getX());
        assertEquals(20, projectile.getY());
    }
    @Test
    void testDrawProjectile() throws IOException {
        Position position = new Position(5, 10);
        ShipProjectile projectile = new ShipProjectile(position);
        TextGraphics mockGraphics = mock(TextGraphics.class);

        projectile.draw(mockGraphics);

        verify(mockGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        verify(mockGraphics).enableModifiers(SGR.BOLD);
        verify(mockGraphics).putString(new TerminalPosition(5, 10), "-");
    }
    @Test
    void testDrawProjectileWithBoldModifier() throws IOException {
        Position position = new Position(5, 10);
        ShipProjectile projectile = new ShipProjectile(position);
        TextGraphics mockGraphics = mock(TextGraphics.class);

        projectile.draw(mockGraphics);

        verify(mockGraphics).enableModifiers(SGR.BOLD);
        verify(mockGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        verify(mockGraphics).putString(new TerminalPosition(5, 10), "-");
    }
}
