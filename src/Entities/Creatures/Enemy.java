package Entities.Creatures;

import Audio.AudioLoader;
import Entities.EntityManager;
import MainG.Handler;
import SecondMinigame.HUD;
import tinysound.Music;

/**
 * Clase de donde heredan los enemigos. A su vez esta hereda de Creature.
 */
public abstract class Enemy extends Creature {

    private HUD hud;
    
    /**
     * Constructor de Enemy.
     * @param handler Handler.
     * @param manager EntityManager.
     * @param x Coordenada en X.
     * @param y Coordenada en Y.
     * @param width Ancho.
     * @param height Alto.
     * @param hud HUD.
     */
    public Enemy(Handler handler, EntityManager manager, float x, float y, int width, int height, HUD hud) {
        super(handler, manager, x, y, width, height);
        this.hud = hud;
    }

}
