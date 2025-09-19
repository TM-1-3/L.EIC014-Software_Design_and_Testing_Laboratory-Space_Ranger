package View;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public interface BlackScreenWithText {

    default void showBlackScreen(Screen screen, String message) throws IOException, InterruptedException {
        TextGraphics graphics = screen.newTextGraphics();

        // Set background color and clear the screen
        graphics.setBackgroundColor(TextColor.ANSI.BLACK);
        graphics.fillRectangle(new TerminalPosition(0, 0), screen.getTerminalSize(), ' ');

        // Set text color and draw the message
        TerminalSize screenSize = screen.getTerminalSize();
        int centerX = screenSize.getColumns() / 2 - message.length() / 2;
        int centerY = screenSize.getRows() / 2;

        graphics.setForegroundColor(TextColor.ANSI.WHITE);
        graphics.putString(centerX, centerY, message);

        screen.refresh();
    }
}
