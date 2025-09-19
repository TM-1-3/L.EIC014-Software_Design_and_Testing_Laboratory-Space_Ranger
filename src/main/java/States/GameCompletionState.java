package States;

import java.io.IOException;
import Sound.SoundPlayer;
import View.GameCompletionViewer;

public class GameCompletionState implements GameState {

    @Override
    public boolean run(Game game) throws IOException, InterruptedException {
        new SoundPlayer().playSound("src\\main\\resources\\win.wav");
        GameCompletionViewer gameCompletionViewer = new GameCompletionViewer();
        gameCompletionViewer.showGameCompletion(3);
        game.getScreen().close();
        game.setState(GameStates.NULL);
        return false;
    }
}