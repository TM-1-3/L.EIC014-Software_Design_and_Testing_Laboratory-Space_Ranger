package States;

import Controller.LevelController;
import Model.Level;
import Model.Level1;
import Model.Level2;
import Model.Level3;
import Sound.SoundPlayer;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;


public class Game {
    private static Game instance;
    private final TerminalScreen screen;
    private final Level1 level1;
    private final Level2 level2;
    private final Level3 level3;
    private final LevelController level1Controller;
    private final LevelController level2Controller;
    private final LevelController level3Controller;
    private final SoundPlayer soundPlayer;
    private GameState currentState;


    private Game() throws IOException {
        this.level1 = Level1.getInstance(120, 30);
        this.level2 = Level2.getInstance(120, 30);
        this.level3 = Level3.getInstance(120, 30);
        TerminalSize size = new TerminalSize(120, 30);
        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(size).createTerminal();
        this.screen = new TerminalScreen(terminal);
        this.screen.startScreen();
        this.level1Controller = new LevelController(level1);
        this.level2Controller = new LevelController(level2);
        this.level3Controller = new LevelController(level3);
        this.soundPlayer = new SoundPlayer();
        this.setState(GameStates.MENU);
    }

    public static Game getInstance() throws IOException {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setState(GameStates state) {
        switch (state) {
            case GameStates.MENU:
                this.currentState=new MenuState();
                break;
            case GameStates.LEVEL_1:
                this.currentState = new Level1State();
                break;
            case GameStates.LEVEL_2:
                this.currentState = new Level2State();
                break;
            case GameStates.LEVEL_3:
                this.currentState = new Level3State();
                break;
            case GameStates.GAME_OVER:
                this.currentState=new GameOverState();
                break;
            case GameStates.GAME_WIN:
                this.currentState=new GameCompletionState();
                break;
            case NULL:
                this.currentState = null;
                break;
            default:
                throw new IllegalArgumentException("Invalid state");
        }
    }

    public Level1 getLevel1() {
        return level1;
    }

    public Level2 getLevel2() {
        return level2;
    }

    public Level3 getLevel3() {
        return level3;
    }

    public LevelController getLevel1Controller() {
        return level1Controller;
    }

    public LevelController getLevel2Controller() {
        return level2Controller;
    }

    public LevelController getLevel3Controller3() {
        return level3Controller;
    }

    public SoundPlayer getSoundPlayer() {
        return soundPlayer;
    }

    public boolean checkIfGameContinues(Level level, LevelController levelController, String soundPath){
        return levelController.runLevel(level,this,soundPath);
    }

    public void start() throws IOException, InterruptedException {
        boolean keepRunning = true; // Control whether the game continues or not
        while (keepRunning && currentState != null) {
            // Execute the current state's `run()` method to manage the game flow
            boolean continueGame = currentState.run(this);
            if (!continueGame) {
                keepRunning = false; // Stop the game if state logic tells us to
            }
        }
        screen.close(); // Make sure to close the screen once the game loop ends
    }
}
