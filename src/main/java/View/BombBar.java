package View;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class BombBar implements StatusBar{

    @Override
    public void drawStatusBar(TextGraphics graphics, int bombsAvailable) {
        String status ="Bombs: " + bombsAvailable;
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(new TerminalPosition(2, 0), status);
    }
}
