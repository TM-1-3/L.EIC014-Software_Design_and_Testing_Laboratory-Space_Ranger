import static org.mockito.Mockito.*;

import Model.ShipProjectile;
import View.ShipProjectileViewer;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

class ShipProjectileViewerTest {

    @Test
    void testDraw() throws IOException {
        // Arrange
        TextGraphics mockGraphics = mock(TextGraphics.class);

        // Create mock projectiles
        ShipProjectile projectile1 = mock(ShipProjectile.class);
        ShipProjectile projectile2 = mock(ShipProjectile.class);

        // Create list of projectiles to be drawn
        List<ShipProjectile> projectiles = Arrays.asList(projectile1, projectile2);

        // Create the viewer instance
        ShipProjectileViewer viewer = new ShipProjectileViewer();

        // Act
        viewer.draw(mockGraphics, projectiles);

        // Assert: Verify that draw method is called on each ShipProjectile
        verify(projectile1).draw(mockGraphics);  // Check if draw was called on projectile1
        verify(projectile2).draw(mockGraphics);  // Check if draw was called on projectile2

        // Verify that draw was called the correct number of times
        verifyNoMoreInteractions(projectile1, projectile2);  // Ensure no other interactions happened
    }

    @Test
    void testDrawEmptyList() throws IOException {
        // Arrange
        TextGraphics mockGraphics = mock(TextGraphics.class);

        // Empty list of projectiles
        List<ShipProjectile> projectiles = Arrays.asList();

        // Create the viewer instance
        ShipProjectileViewer viewer = new ShipProjectileViewer();

        // Act
        viewer.draw(mockGraphics, projectiles);

        // Assert: Verify that draw method is not called since the list is empty
        verifyNoInteractions(mockGraphics);  // No interactions should have occurred with TextGraphics
    }
}