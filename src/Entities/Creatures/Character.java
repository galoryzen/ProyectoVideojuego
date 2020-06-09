package Entities.Creatures;

import Entities.EntityManager;
import MainG.Handler;

/**
 * Clase character de donde heredan los Players. A su vez esta hereda de Creature.
 */
public abstract class Character extends Creature {
    
    
    public Character(Handler handler, EntityManager manager, float x, float y, int width, int height) {
        super(handler, manager, x, y, width, height);
    }

    public abstract void getInput();

    protected boolean collisionWithTile(int x, int y) {
        return handler.getWorld().getTile(x, y).isSolid();
    }
}
