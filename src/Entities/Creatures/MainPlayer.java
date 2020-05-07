package Entities.Creatures;

import Entities.EntityManager;
import MainG.Handler;
import Tilemaps.Assets;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

public class MainPlayer extends Character {

    private long timePassed;
    private double dx, dy;
    private double maxDY;
    private double gravity = 0.99d;
    private boolean falling;
    private boolean jumping;
    private Punto punto;
    
    public class Punto {
        
        public Punto(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public float x;
        public float y;
    }

    public MainPlayer(Handler handler, EntityManager entityM, float x, float y) {
        super(handler, entityM, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATUR_HEIGHT);
        //points = new LinkedList();
        timePassed = System.currentTimeMillis();

        bounds.x = 40;
        bounds.y = 30;
        bounds.height = 65;
        bounds.width = 50;

        dx = 3;
        dy = 12;
    }

    @Override
    public void getInput() {
        if (handler.getKeyManager().up) {
            if (!jumping && !falling) {
                dy = 18;
                jumping = true;
                falling = false;
            }
        }
        if (handler.getGame().getKeyManager().down) {

        }
        if (handler.getGame().getKeyManager().left) {
            x -= dx;
        }
        if (handler.getGame().getKeyManager().right) {
            x += dx;
        }
        // Running
        if (handler.getGame().getKeyManager().test) {
            if (dx < 5) {
                dx += 0.1;
            }
        }else{
            if(dx >= 3){
                dx -= 0.1;
            }
        }
        // Time Travel
        if (handler.getGame().getKeyManager().space) {
            Punto puntoO = punto;
            if(punto == null){
                System.out.println("HABILIDAD NO LISTA");
            }else{
            backwards(puntoO);
            }
        }
    }

    @Override
    public void die() {

    }

    @Override
    public void update() {
        getInput();
        checkPuntos();
        jumping();
        fall();
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.yellow);
        g.drawRect((int) x + (int) bounds.x, (int) y + (int) bounds.y, (int) bounds.width, (int) bounds.height);
        g.drawImage(Assets.spriteNina, (int) x, (int) y, null);
    }

    public void checkPuntos() {
        if (System.currentTimeMillis() - timePassed >= 2000) {
            punto = new Punto(x,y);
            timePassed = System.currentTimeMillis();
        }
    }

    public void backwards(Punto punto) {
        x = punto.x;
        y = punto.y;
    }

    public void fall() {
        if (falling && !jumping) {
            y += dy;
            dy += gravity;
            if (y >= 450) {
                y = 450;
                falling = false;
            }
        }
    }

    public void jumping() {
        if (!falling && jumping) {
            y -= dy;
            dy -= gravity;
            if (dy <= 0) {
                falling = true;
                jumping = false;
            }
        }
    }

    public void checkAttacks() {
        Rectangle cb = getCollisionBounds();
    }
}
