import Model.Position;
import Model.Level1;
import Controller.ShipController;
import Model.Ship;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ShipTest {
    private ShipController shipController;
    private Level1 level1;

    @BeforeEach
    public void setUp() {
        this.level1=new Level1(120,30);
        shipController=new ShipController(level1);
    }

    @Test
    public void testMoveUp(){
        Position afterPosition=shipController.moveUp();
        Position expected = new Position(6,14);
        assertEquals(expected, afterPosition);
    }

    @Test
    public void testMoveDown(){
        Position afterPosition=shipController.moveDown();
        Position expected = new Position(6,16);
        assertEquals(expected, afterPosition);
    }

    @Test
    public void testInitialPosition(){
        Position initialposition=level1.getShip().getPosition();
        Position expected = new Position(6,15);
        assertEquals(expected, initialposition);
    }
    
    @Test
    void testShipInitialHealth() {
        Ship ship = new Ship(new Position(10, 10));
        assertEquals(3, ship.getHealth(), "Ship should be created with initial health of 3");
    }
    @Test
    void testDecreaseHealth() {
        Ship ship = new Ship(new Position(10, 10));
        int initialHealth = ship.getHealth();
        ship.decreaseHealth();
        assertEquals(initialHealth - 1, ship.getHealth(), "Ship's health should decrease by 1 when decreaseHealth() is called");
    }
    
    @Test
    void testDrawShip() {
        Ship ship = new Ship(new Position(10, 15));
        TextGraphics mockGraphics = mock(TextGraphics.class);

        try {
            ship.draw(mockGraphics);
            verify(mockGraphics).setForegroundColor(TextColor.Factory.fromString("#00FF00"));
            verify(mockGraphics).enableModifiers(SGR.BOLD);
            verify(mockGraphics).putString(new TerminalPosition(10, 15), "▶");
        } catch (IOException e) {
            fail("Exception should not be thrown during draw operation");
        }
    }
    
}
