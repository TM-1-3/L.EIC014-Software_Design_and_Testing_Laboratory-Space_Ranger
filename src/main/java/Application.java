import States.Game;
import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException {
        Game game = Game.getInstance();
        game.start();
    }
}
