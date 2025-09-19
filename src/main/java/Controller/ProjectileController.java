package Controller;

import Model.Level;
import Sound.SoundPlayer;

public abstract class ProjectileController{
    protected final Level level;
    protected final SoundPlayer soundPlayer;

    ProjectileController(Level level) {
        this.level = level;
        soundPlayer = new SoundPlayer();
    }

    public abstract void move();

    public abstract void treatCollisions();
}
