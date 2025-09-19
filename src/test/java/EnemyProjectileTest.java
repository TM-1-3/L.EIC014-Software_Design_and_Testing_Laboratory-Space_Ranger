import Model.EnemyProjectile;
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

class EnemyProjectileTest {

    @Mock
    private TextGraphics mockGraphics;

    private EnemyProjectile enemyProjectile;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        enemyProjectile = new EnemyProjectile(new Position(3, 7));
    }

    @Test
    void testEnemyProjectileInitialization() {
        // Assert position is correctly initialized
        Position position = enemyProjectile.getPosition();
        assert position != null;

    }

    @Test
    void testDraw() throws IOException {
        // Act
        enemyProjectile.draw(mockGraphics);

        // Assert
        verify(mockGraphics).setForegroundColor(TextColor.Factory.fromString("#BE32FF"));
        verify(mockGraphics).enableModifiers(any());
        verify(mockGraphics).putString(new TerminalPosition(3, 7), "-");
    }
}
