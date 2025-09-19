package View;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class ControlsBar implements StatusBar{

    @Override
    public void drawStatusBar(TextGraphics graphics, int placeHolder) {
        String status ="↑ : UP    ↓ : DOWN     A : SHOOT LASER     S : RELEASE BOMB     Q : QUIT";
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(new TerminalPosition(25, 28), status);
    }
}
