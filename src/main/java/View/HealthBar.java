package View;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class HealthBar implements StatusBar{

    @Override
    public void drawStatusBar(TextGraphics graphics, int remainingHealth) {
        StringBuilder lifeDisplay = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            if (i < remainingHealth) {
                lifeDisplay.append(" ♥ ");
            } else {
                lifeDisplay.append(" ");
            }
        }
        graphics.setForegroundColor(TextColor.Factory.fromString("#00ff04"));
        graphics.putString(2, 1, "Health: " + lifeDisplay);
    }
}
