package Model;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Star extends Element{

    public Star(Position position){
        super(position);
    }

    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#F3EB92"));
        graphics.putString(new TerminalPosition(getX(), getY()),".");
    }
}
