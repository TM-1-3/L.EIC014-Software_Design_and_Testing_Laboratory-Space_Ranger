package States;

import java.io.IOException;
import Sound.SoundPlayer;
import View.GameOverViewer;

public class GameOverState implements GameState {

    @Override
    public boolean run(Game game) throws IOException, InterruptedException {
        new SoundPlayer().playSound("src\\main\\resources\\lose.wav");
        GameOverViewer gameOver = new GameOverViewer();
        gameOver.showGameOver(3);
        game.getScreen().close();
       return false; // Continue to the next state
    }
}
