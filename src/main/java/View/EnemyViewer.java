package View;

import Model.Enemy;
import com.googlecode.lanterna.graphics.TextGraphics;
import java.io.IOException;
import java.util.List;

public class EnemyViewer implements ElementGroupViewer<Enemy> {

    @Override
    public void draw(TextGraphics graphics, List<Enemy> enemies) throws IOException {
        for (Enemy enemy : enemies){
            enemy.draw(graphics);
        }
    }
}
