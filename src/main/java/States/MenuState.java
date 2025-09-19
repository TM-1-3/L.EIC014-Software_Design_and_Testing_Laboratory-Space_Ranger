package States;

import Controller.MenuController;
import View.MenuViewer;

import java.io.IOException;

public class MenuState implements GameState {

    @Override
    public boolean run(Game game) {
        try {
            MenuViewer menuViewer = new MenuViewer();
            MenuController menuController = new MenuController();
            menuViewer.showMenu(game,menuController);
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Exit on error
        }
        return true; // Continue to the next state
    }
}
