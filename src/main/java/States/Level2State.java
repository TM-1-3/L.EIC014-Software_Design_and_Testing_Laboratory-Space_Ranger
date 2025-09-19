package States;

public class Level2State implements GameState {

    @Override
    public boolean run(Game game) {
        boolean levelCompleted=game.checkIfGameContinues(game.getLevel2(),game.getLevel2Controller(),"src\\main\\resources\\level2.wav");
        if (levelCompleted) {
            game.getSoundPlayer().stopSound();
            game.setState(GameStates.LEVEL_3);
            return true;
        }
        game.setState(GameStates.GAME_OVER);
        return false;
    }
}
