package View;

import Model.Asteroid;
import com.googlecode.lanterna.graphics.TextGraphics;
import java.io.IOException;
import java.util.List;

public class AsteroidViewer implements ElementGroupViewer<Asteroid> {

    @Override
    public void draw(TextGraphics graphics, List<Asteroid> asteroids) throws IOException {
        for (Asteroid asteroid : asteroids){
            asteroid.draw(graphics);
        }
    }
}
