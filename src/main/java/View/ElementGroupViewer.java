package View;

import com.googlecode.lanterna.graphics.TextGraphics;
import java.io.IOException;
import java.util.List;

public interface ElementGroupViewer<T> {
    void draw(TextGraphics graphics, List<T> elements) throws IOException;
}
