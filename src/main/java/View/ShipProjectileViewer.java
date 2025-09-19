package View;

import Model.ShipProjectile;
import com.googlecode.lanterna.graphics.TextGraphics;
import java.io.IOException;
import java.util.List;

public class ShipProjectileViewer implements ElementGroupViewer<ShipProjectile> {

    @Override
    public void draw(TextGraphics graphics, List<ShipProjectile> projectiles) throws IOException {
        for (ShipProjectile projectile : projectiles){
            projectile.draw(graphics);
        }
    }
}
