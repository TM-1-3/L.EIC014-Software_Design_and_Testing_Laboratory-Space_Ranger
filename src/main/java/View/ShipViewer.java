package View;

import Model.Ship;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public class ShipViewer {

    public void draw(TextGraphics graphics, Ship ship) throws IOException {
        ship.draw(graphics);
    }
}
