package Model;

import com.googlecode.lanterna.graphics.TextGraphics;
import java.io.IOException;

public abstract class Element {
    protected Position position;

    public Element(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getX() {
        return position.x();
    }

    public int getY() {
        return position.y();
    }

    public abstract void draw(TextGraphics graphics) throws IOException;
}

