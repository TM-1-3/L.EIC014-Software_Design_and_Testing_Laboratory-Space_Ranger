package View;

import Model.EnemyProjectile;
import com.googlecode.lanterna.graphics.TextGraphics;
import java.io.IOException;
import java.util.List;

public class EnemyProjectileViewer implements ElementGroupViewer<EnemyProjectile> {

    @Override
    public void draw(TextGraphics graphics, List<EnemyProjectile> enemyProjectiles) throws IOException {
        for (EnemyProjectile enemyProjectile : enemyProjectiles){
            enemyProjectile.draw(graphics);
        }
    }
}
