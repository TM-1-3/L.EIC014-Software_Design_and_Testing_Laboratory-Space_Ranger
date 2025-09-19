package View;

import States.Game;
import States.GameStates;
import Controller.MenuController;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;
import java.util.List;

public class MenuViewer implements BlackScreenWithText {

    public void showMenu(Game game, MenuController menuController) throws IOException {
        Screen screen = game.getScreen();
        game.getSoundPlayer().playSound("src\\main\\resources\\Menu.wav");
        MultiWindowTextGUI gui = new MultiWindowTextGUI(screen, new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.BLACK));
        BasicWindow menuWindow = new BasicWindow("Space Ranger");
        Panel mainPanel = new Panel();
        mainPanel.setLayoutManager(new LinearLayout(Direction.HORIZONTAL));
        Panel menuPanel = new Panel();
        menuPanel.setLayoutManager(new GridLayout(1)); // Vertical layout for buttons
        Label titleLabel = new Label("Game Menu");
        titleLabel.setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.BEGINNING, GridLayout.Alignment.CENTER));
        menuPanel.addComponent(titleLabel);
        menuPanel.addComponent(new Button("Start Game (Level 1)", () -> menuController.startLevel(screen, menuWindow,game, GameStates.LEVEL_1,1,3)));
        menuPanel.addComponent(new Button("Start Level 2", () -> menuController.startLevel(screen, menuWindow,game, GameStates.LEVEL_2,2,3)));
        menuPanel.addComponent(new Button("Start Level 3", () -> menuController.startLevel(screen, menuWindow,game, GameStates.LEVEL_3,3,3)));
        menuPanel.addComponent(new Button("Exit", () -> menuController.handleExit(screen, menuWindow,game)));
        mainPanel.addComponent(menuPanel);
        mainPanel.addComponent(new EmptySpace().setLayoutData(LinearLayout.createLayoutData(LinearLayout.Alignment.Fill)));
        menuWindow.setComponent(mainPanel);
        menuWindow.setHints(List.of(Window.Hint.FULL_SCREEN));
        gui.addWindowAndWait(menuWindow);
    }

    public void showDecision(Screen screen, String message, String prompt, Game game) throws IOException, InterruptedException {
        game.getSoundPlayer().stopSound();
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.ANSI.BLACK);
        graphics.fillRectangle(new TerminalPosition(0, 0), screen.getTerminalSize(), ' ');
        TerminalSize screenSize = screen.getTerminalSize();
        int centerX = screenSize.getColumns() / 2 - message.length() / 2;
        int centerY = screenSize.getRows() / 2;
        graphics.setForegroundColor(TextColor.ANSI.WHITE);
        graphics.putString(centerX, centerY, message);
        String decisionPrompt = prompt + " (Y/N)";
        graphics.putString(screenSize.getColumns() / 2 - decisionPrompt.length() / 2, centerY + 2, decisionPrompt);
        screen.refresh();
    }

    public void showLoadingLevelScreen(Screen screen, int levelNumber) throws IOException, InterruptedException {
        showBlackScreen(screen, "Starting Level " + levelNumber + " ...");
    }
}
