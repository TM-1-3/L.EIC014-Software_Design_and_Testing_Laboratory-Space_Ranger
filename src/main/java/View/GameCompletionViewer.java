package View;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import java.io.IOException;

public class GameCompletionViewer implements BlackScreenWithText {

    public void showGameCompletion(int seconds) throws IOException, InterruptedException {
        TerminalSize terminalSize = new TerminalSize(120, 30);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        Screen screen = terminalFactory.createScreen();
        try {
            screen.startScreen();
            showBlackScreen(screen, "YOU WON!! THANKS FOR PLAYING");
            Thread.sleep(seconds * 1000L);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Ensure resources are released
            screen.stopScreen();
            screen.close();
        }
    }
}