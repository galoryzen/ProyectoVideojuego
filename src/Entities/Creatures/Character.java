package Entities.Creatures;

import Entities.EntityManager;
import MainG.Handler;
import java.awt.Graphics;


public abstract class Character extends Creature {

    public Character(Handler handler, EntityManager manager, float x, float y, int width, int height) {
        super(handler, manager, x, y, width, height);
    }

    @Override
    public void die() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {

    }
    
}
