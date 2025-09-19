package Model;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Explosion extends Element {
    private int lifetime;

    public Explosion(Position position){
        super(position);
        this.lifetime=10;
    }

    @Override
    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFA500"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(getX(), getY()), "O");
        graphics.putString(new TerminalPosition(getX() + 1, getY()), "O");
        graphics.putString(new TerminalPosition(getX() - 1, getY()), "O");
        graphics.putString(new TerminalPosition(getX(), getY() + 1), "O");
        graphics.putString(new TerminalPosition(getX(), getY() - 1), "O");
    }

    public boolean duration(){
        lifetime--;
        return lifetime==0;
    }

}
