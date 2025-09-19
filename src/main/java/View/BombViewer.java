package View;

import Model.Bomb;
import com.googlecode.lanterna.graphics.TextGraphics;
import java.io.IOException;
import java.util.List;

public class BombViewer implements ElementGroupViewer<Bomb> {

    @Override
    public void draw(TextGraphics graphics, List<Bomb> bombs) throws IOException {
        for (Bomb bomb : bombs){
            bomb.draw(graphics);
        }
    }
}
