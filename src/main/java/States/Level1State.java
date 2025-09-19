package States;

public class Level1State implements GameState {

    @Override
    public boolean run(Game game) {
        boolean levelCompleted=game.checkIfGameContinues(game.getLevel1(),game.getLevel1Controller(),"src\\main\\resources\\level1.wav");
        if (levelCompleted) {
            game.getSoundPlayer().stopSound();
            game.setState(GameStates.LEVEL_2);
            return true;
        }
        game.setState(GameStates.GAME_OVER);
        return false;
    }
}
