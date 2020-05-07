package Entities.Creatures;

import Audio.AudioLoader;
import Entities.EntityManager;
import MainG.Handler;
import SecondMinigame.HUD;
import tinysound.Music;

public abstract class Enemy extends Creature {

    private HUD hud;

    public Enemy(Handler handler, EntityManager manager, float x, float y, int width, int height, HUD hud) {
        super(handler, manager, x, y, width, height);
        this.hud = hud;
    }

}
