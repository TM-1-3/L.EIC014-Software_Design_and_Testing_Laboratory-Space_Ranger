package Model;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import java.io.IOException;

public class Ship extends LiveElement {

    public Ship(Position position){
        super(position,3);
    }

    @Override
    public void draw(TextGraphics graphics) throws IOException {
        graphics.setForegroundColor(TextColor.Factory.fromString("#00FF00"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(getX(), getY()), "▶");
    }

    @Override
    public void decreaseHealth(){
        super.decreaseHealth();
    }

    @Override
    public int getHealth() {
        return super.getHealth();
    }

}