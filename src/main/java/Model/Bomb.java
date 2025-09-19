package Model;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import java.io.IOException;

public class Bomb extends Element {
    private static final int RADIUS = 2;  // Raio de impacto fixo

    public int getRadius() {
        return RADIUS;
    }

    public static int getMaxBombsInLevel() {
        return 5;  // Número máximo de bombas por nível
    }

    public Bomb(Position position) {
        super(position);
    }

    @Override
    public void draw(TextGraphics graphics) throws IOException {
        graphics.setForegroundColor(TextColor.Factory.fromString("#964B00"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(getX(), getY()),"Φ");
    }

}
