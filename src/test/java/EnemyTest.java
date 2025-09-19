import Model.*;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class EnemyTest {
    private EasierEnemy easierEnemy;
    private AverageEnemy averageEnemy;
    private HarderEnemy harderEnemy;
    private TextGraphics mockGraphics;

    @BeforeEach
    void setUp() {
        // Creating enemies with specific Positions
        easierEnemy = new EasierEnemy(new Position(5, 10));
        averageEnemy = new AverageEnemy(new Position(15, 20));
        harderEnemy = new HarderEnemy(new Position(25, 30));

        // TextGraphics mockup for drawing tests
        mockGraphics = mock(TextGraphics.class);
    }

    @Test
    void testPosition() {
        // Checks enemy starting positions
        assert easierEnemy.getX() == 5 && easierEnemy.getY() == 10;
        assert averageEnemy.getX() == 15 && averageEnemy.getY() == 20;
        assert harderEnemy.getX() == 25 && harderEnemy.getY() == 30;
    }

    @Test
    void testDrawEasierEnemy() {
        // Test EasierEnemy's draw method
        easierEnemy.draw(mockGraphics);
        verify(mockGraphics).setForegroundColor(TextColor.Factory.fromString("#1F26C2"));
        verify(mockGraphics).putString(new TerminalPosition(5, 10), "☠");
    }

    @Test
    void testDrawAverageEnemy() {
        // Test AverageEnemy's draw method
        averageEnemy.draw(mockGraphics);
        verify(mockGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFB00"));
        verify(mockGraphics).putString(new TerminalPosition(15, 20), "☠");
    }

    @Test
    void testDrawHarderEnemy() {
        // Test HarderEnemy's draw method
        harderEnemy.draw(mockGraphics);
        verify(mockGraphics).setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        verify(mockGraphics).putString(new TerminalPosition(25, 30), "☠");
    }

    @Test
    void testDecreaseHealth() {
        // Checks the decrease in enemy health
        easierEnemy.decreaseHealth();
        averageEnemy.decreaseHealth();
        harderEnemy.decreaseHealth();

        assert easierEnemy.getHealth() == 0 : "Vida do EasierEnemy deveria ser 0";
        assert averageEnemy.getHealth() == 2 : "Vida do AverageEnemy deveria ser 2";
        assert harderEnemy.getHealth() == 4 : "Vida do HarderEnemy deveria ser 4";
    }
}
