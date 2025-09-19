package View;

import Model.Star;
import com.googlecode.lanterna.graphics.TextGraphics;
import java.io.IOException;
import java.util.List;

public class StarViewer implements ElementGroupViewer<Star> {

    @Override
    public void draw(TextGraphics graphics, List<Star> stars) throws IOException {
        for (Star star: stars){
            star.draw(graphics);
        }
    }
}
