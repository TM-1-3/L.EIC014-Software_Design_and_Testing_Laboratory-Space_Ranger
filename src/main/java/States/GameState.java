package States;

import java.io.IOException;

public interface GameState {
    boolean run(Game game) throws IOException, InterruptedException;
}
