package View;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class LevelBar implements StatusBar {

    @Override
    public void drawStatusBar(TextGraphics graphics, int numberLevel) {
        String status ="LEVEL " + numberLevel;
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(new TerminalPosition(55, 1), status);
    }
}
