package Model;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class EasierEnemy extends Enemy {

    public EasierEnemy(Position position) {
        super(position,1);
    }

    @Override
    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#1F26C2"));
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
