package States;

public class Level3State implements GameState {

    @Override
    public boolean run(Game game) {
        boolean levelCompleted=game.checkIfGameContinues(game.getLevel3(),game.getLevel3Controller3(),"src\\main\\resources\\level3.wav");
        if (levelCompleted) {
            System.out.println("Congratulations! You've completed the game!");
            game.setState(GameStates.GAME_WIN);
            return true; // End the game
        }
        game.setState(GameStates.GAME_OVER);
        return false; // Retry logic if needed
    }
}
