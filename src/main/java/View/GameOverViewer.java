package View;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import java.io.IOException;

public class GameOverViewer implements BlackScreenWithText {

    public void showGameOver(int seconds) throws IOException, InterruptedException {
        TerminalSize terminalSize = new TerminalSize(120, 30);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        Screen screen = terminalFactory.createScreen();
        screen.startScreen();
        try {
            showBlackScreen(screen, "GAME OVER");
            Thread.sleep(seconds * 1000L);
            screen.stopScreen();
            screen.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}