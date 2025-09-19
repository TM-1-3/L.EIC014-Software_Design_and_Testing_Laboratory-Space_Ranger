package View;

import Model.Explosion;
import com.googlecode.lanterna.graphics.TextGraphics;
import java.util.ArrayList;
import java.util.List;

public class ExplosionViewer implements ElementGroupViewer<Explosion> {

    @Override
    public void draw(TextGraphics graphics, List<Explosion> explosions){
        List<Explosion> exploded=new ArrayList<>();
        for (Explosion explosion : explosions){
            explosion.draw(graphics);
            if (explosion.duration()) {
                exploded.add(explosion);
            }
        }
        explosions.removeAll(exploded);
    }
}
