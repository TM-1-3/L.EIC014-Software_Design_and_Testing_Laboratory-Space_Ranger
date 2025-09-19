package View;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class EnemiesBar implements StatusBar {

    @Override
    public void drawStatusBar(TextGraphics graphics, int remainingEnemies){
        String remainingEnemiesDisplay = "☠ Remaining: " + remainingEnemies;
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(new TerminalPosition(100, 1), remainingEnemiesDisplay);
    }
}
