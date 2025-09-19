package Model;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class HarderEnemy extends Enemy{

    public HarderEnemy(Position position){
        super(position,5);
    }

    @Override
    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(getX(), getY()),"☠");
    }

    @Override
    public void decreaseHealth(){
        super.decreaseHealth();
    }

    @Override
    public int getHealth(){
        return super.getHealth();
    }
}
