package View;

import Model.Boundary;
import com.googlecode.lanterna.graphics.TextGraphics;
import java.io.IOException;
import java.util.List;

public class BoundaryViewer implements ElementGroupViewer<Boundary> {

    @Override
    public void draw(TextGraphics graphics, List<Boundary> boundaries) throws IOException {
        for (Boundary boundary : boundaries){
            boundary.draw(graphics);
        }
    }
}
