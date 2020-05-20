package Entities.Creatures;

import Entities.EntityManager;
import MainG.Handler;
import MainG.Window;
import MainLevel.Tiles.TileMainLevel;
import Tilemaps.Animation;
import Tilemaps.Assets;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainPlayer extends Character {

    private double speedX, speedY, percentajeVelocity, maxSpeedY, xMove, yMove, maxPercentajeVelocity;
    private final double gravity = 600;
    private int antispamerJump = 0;
    private double timePressed;
    private boolean isGround = false;
    private boolean jumping = false;
    private Punto returnPoint;

    private Animation animDown, animUp, animR, animL;

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

        bounds.x = 40;
        bounds.y = 35;
        bounds.height = 60;
        bounds.width = 35;

        xMove = 0;
        yMove = 0;
        speedX = 125;
        speedY = 100;
        percentajeVelocity = 0;
        maxPercentajeVelocity = 1.50;
        maxSpeedY = 400;

        //Animations 
        animDown = new Animation(300, Assets.playerDown);
        animUp = new Animation(300, Assets.playerUp);
        animR = new Animation(300, Assets.playerRight);
        animL = new Animation(300, Assets.playerLeft);
    }

    @Override
    public void getInput() {
        if (Window.keyManager.right) {
            xMove = speedX;
        }
        if (Window.keyManager.left) {
            xMove = -speedX;
        }
        if (Window.keyManager.up) {
            if (isGround && !jumping && antispamerJump == 0) {
                speedY = maxSpeedY;
                yMove = speedY;
                jumping = true;
                antispamerJump = 1;
            }
        } else {
            antispamerJump = 0;
        }

        // Running
        if (Window.keyManager.shift) {
            if (percentajeVelocity <= maxPercentajeVelocity) {
                percentajeVelocity += 0.05;
            } else { 
                percentajeVelocity = maxPercentajeVelocity;
            }
        } else {
            if (percentajeVelocity > 0) {
                percentajeVelocity -= 0.02;
            } else {
                percentajeVelocity = 0;
            }
        }
        // Time Travel
        if (Window.keyManager.space) {
            if (returnPoint == null) {
                returnPoint = new Punto(x, y);
            } else {
                backwards(returnPoint);
            }
        }
        if (Window.keyManager.test) {
            if (hasInteraction()) {
            }
        }
    }

    @Override
    public void die() {

    }

    public void move() {
        moveX();
        moveY();
    }

    @Override
    public void update() {
        getInput();
        move();
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.yellow);
        g.fillRect((int) (x + bounds.x), (int) (y + bounds.y), bounds.width, bounds.height);
        g.drawImage(Assets.spriteNina, (int) x, (int) y, null);
    }

    @Override
    protected boolean collisionWithTile(int x, int y) {
        return handler.getWorld().getTile(x, y).isSolid();
    }

    protected boolean cheackInteraction(int x, int y) {
        return handler.getWorld().getTile(x, y).isInteractive();
    }

    public void backwards(Punto punto) {
        x = punto.x;
        y = punto.y;
    }

    public void moveX() {
        if (Window.keyManager.right) {
            if (hasHorizontalCollision(0)) {
                double extraVelocity = xMove * percentajeVelocity * handler.getDeltaTime();
                x += (xMove * handler.getDeltaTime() + extraVelocity);
            } else {
                int tx = (int) (x + xMove * handler.getDeltaTime() + bounds.x + bounds.width) / TileMainLevel.TILEWIDTH;
                x = tx * TileMainLevel.TILEWIDTH - bounds.x - bounds.width - 1;
            }
        }
        if (Window.keyManager.left) {
            if (hasHorizontalCollision(1)) {
                double extraVelocity = xMove * percentajeVelocity * handler.getDeltaTime();
                x += xMove * handler.getDeltaTime() + extraVelocity;
            } else {
                int tx = (int) (x + xMove * handler.getDeltaTime() + bounds.x) / TileMainLevel.TILEWIDTH;
                x = tx * TileMainLevel.TILEWIDTH + TileMainLevel.TILEWIDTH - bounds.x;
            }
        }
    }

    public void moveY() {
        jumping();
        fall();
    }

    private boolean hasInteraction() {
        return hasHorizontalInteractive(0) || hasHorizontalInteractive(1) || hasVerticalInteraction(0) || hasVerticalInteraction(1);
    }

    private boolean hasHorizontalCollision(int mode) {
        int tx = 0;
        if (mode == 0) {
            tx = (int) (x + xMove * handler.getDeltaTime() + bounds.x + bounds.width) / TileMainLevel.TILEWIDTH;
        } else if (mode == 1) {
            tx = (int) (x + xMove * handler.getDeltaTime() + bounds.x) / TileMainLevel.TILEWIDTH;
        }
        return !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / TileMainLevel.TILEHEIGHT)
                && !collisionWithTile(tx, (int) (y + bounds.y) / TileMainLevel.TILEHEIGHT);
    }

    private boolean hasHorizontalInteractive(int mode) {
        int tx = 0;
        if (mode == 0) {
            tx = (int) (x + xMove * handler.getDeltaTime() + bounds.x + bounds.width) / TileMainLevel.TILEWIDTH;
        } else if (mode == 1) {
            tx = (int) (x + xMove * handler.getDeltaTime() + bounds.x) / TileMainLevel.TILEWIDTH;
        }
        return cheackInteraction(tx, (int) (y + bounds.y + bounds.height) / TileMainLevel.TILEHEIGHT)
                && cheackInteraction(tx, (int) (y + bounds.y) / TileMainLevel.TILEHEIGHT);
    }

    private boolean hasVerticalCollision(int mode) {
        int ty = 0;
        if (mode == 0) {
            ty = (int) (y + bounds.y + yMove * handler.getDeltaTime()) / TileMainLevel.TILEHEIGHT;
        } else if (mode == 1) {
            ty = (int) (y + yMove * handler.getDeltaTime() + bounds.y + bounds.height) / TileMainLevel.TILEHEIGHT;
        }
        return !collisionWithTile((int) (x + bounds.x + bounds.width) / TileMainLevel.TILEWIDTH, ty)
                && !collisionWithTile((int) (x + bounds.x) / TileMainLevel.TILEWIDTH, ty);
    }

    private boolean hasVerticalInteraction(int mode) {
        int ty = 0;
        if (mode == 0) {
            ty = (int) (y + bounds.y + yMove * handler.getDeltaTime()) / TileMainLevel.TILEHEIGHT;
        } else if (mode == 1) {
            ty = (int) (y + yMove * handler.getDeltaTime() + bounds.y + bounds.height) / TileMainLevel.TILEHEIGHT;
        }
        return cheackInteraction((int) (x + bounds.x + bounds.width) / TileMainLevel.TILEWIDTH, ty)
                && cheackInteraction((int) (x + bounds.x) / TileMainLevel.TILEWIDTH, ty);
    }

    public void fall() {
        if (hasVerticalCollision(1) && !jumping) {
            yMove = speedY;
            y += yMove * handler.getDeltaTime();
            speedY += gravity * handler.getDeltaTime();
        } else {
            if (!jumping) {
                int ty = (int) (y + yMove * handler.getDeltaTime() + bounds.y + bounds.height) / TileMainLevel.TILEHEIGHT;
                y = ty * TileMainLevel.TILEHEIGHT - bounds.y - bounds.height - 1;
            }
            isGround = true;
        }
    }

    public void jumping() {
        if (hasVerticalCollision(0) && jumping) {
            yMove = speedY;
            y -= yMove * handler.getDeltaTime();
            speedY -= gravity * handler.getDeltaTime();
            System.out.println(speedY);
            if (speedY <= 0 || !hasVerticalCollision(0)) {
                jumping = false;
                isGround = false;
                speedY = 0;
            }
        } else {
            if (jumping) {
                int ty = (int) (y + bounds.y + yMove * handler.getDeltaTime()) / TileMainLevel.TILEHEIGHT;
                y = ty * TileMainLevel.TILEHEIGHT + TileMainLevel.TILEHEIGHT - bounds.y;
                jumping = false;
            }
        }
    }

    public void checkAttacks() {
        Rectangle cb = getCollisionBounds();
    }
}
