package Model;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import java.io.IOException;

public class Boundary extends Element {

    public Boundary(Position position) {
        super(position);
    }

    @Override
    public void draw(TextGraphics graphics) throws IOException{
        graphics.setForegroundColor(TextColor.Factory.fromString("#808080"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(getX(), getY()),"█");
    }
}
