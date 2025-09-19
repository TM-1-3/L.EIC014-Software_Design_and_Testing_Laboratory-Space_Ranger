package Controller;

import States.Game;
import States.GameStates;
import View.MenuViewer;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;

@SuppressWarnings("ALL")

public class MenuController {

    public void startLevel(Screen screen, BasicWindow menuWindow, Game game, GameStates state, int levelNumber, int seconds) {
        menuWindow.close();
        try {
            game.getSoundPlayer().stopSound();
            new MenuViewer().showLoadingLevelScreen(screen,levelNumber);
            Thread.sleep(seconds* 1000L);
            game.setState(state);
            game.start();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void handleExit(Screen screen, BasicWindow menuWindow, Game game) {
        menuWindow.close();
        try {
            new MenuViewer().showDecision(screen, "Are you sure you want to exit?", "Confirm exit",game);
            if (new MenuController().handleInputInExit(screen)) {
                screen.stopScreen();
                System.exit(0);
            } else {
                new MenuViewer().showMenu(game,new MenuController());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean handleInputInExit(Screen screen) throws IOException {
        KeyStroke key;
        while (true) {
            key = screen.readInput();
            if (key.getKeyType() == KeyType.Character) {
                char input = Character.toUpperCase(key.getCharacter());
                if (input == 'Y') {
                    return true;
                } else if (input == 'N') {
                    return false;
                }
            }
        }
    }
}
