package Entities.Creatures;

import Entities.EntityManager;
import MainG.Handler;
import MainG.Window;
import MainLevel.Tiles.TileMainLevel;
import MainLevel.WorldGenerator.WorldPlat;
import Tilemaps.Animation;
import Tilemaps.Assets;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class MainPlayer extends Character {

    private double speedX, speedY, percentajeVelocity, maxSpeedY, xMove, yMove, maxPercentajeVelocity;
    private final double gravity = 600;
    private int antispamerJump = 0;
    private boolean isGround = false;
    private boolean jumping = false;
    private boolean reset = false;
    private Punto returnPoint;
    private boolean buttonPressed = false;
    private int amountOfReturns;
    private int maxReturns;
    private double timePressed;

    private Animation animD, animUp, animR, animL, animSS, animRi;

    public void setPosition(int[] position) {
        this.x = position[0];
        this.y = position[1];
    }

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
        bounds.width = 40;

        xMove = 0;
        yMove = 0;
        speedX = 125;
        speedY = 100;
        percentajeVelocity = 0;
        maxPercentajeVelocity = 1.20;
        maxSpeedY = 400;

        //Animations 
        animUp = new Animation(100, Assets.mainPlayerUp);
        animL = new Animation(100, Assets.mainPlayerLeft);
        animR = new Animation(100, Assets.mainPlayerRunning);
        animD = new Animation(100, Assets.mainPlayerFalling);
        animSS = new Animation(100, Assets.mainPlayerStandStill);
        timePressed = System.currentTimeMillis();
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
        if (Window.keyManager.space && System.currentTimeMillis() - timePressed >= 2000) {
            if (returnPoint == null && amountOfReturns < maxReturns) {
                returnPoint = new Punto(x, y);
                System.out.println("Guardada");
                timePressed = System.currentTimeMillis();
            } else {
                if (amountOfReturns == maxReturns) {
                    System.out.println("Pailas");
                } else {
                    backwards(returnPoint);
                    amountOfReturns++;
                    returnPoint = null;
                }

            }
        }
        if (Window.keyManager.test) {
            checkInteraction(rightHand());
        }
        if (Window.keyManager.reset) {
            reset = true;
        }
    }

    @Override
    public void die() {
        reset = true;
        active = true;
    }

    public void move() {
        moveX();
        moveY();
    }

    @Override
    public void update() {
        getInput();
        move();
        animL.update();
        animUp.update();
        animSS.update();
        animD.update();
        animR.update();
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.yellow);
        g.fillRect((int) (x + bounds.x), (int) (y + bounds.y), bounds.width, bounds.height);
        g.drawImage(getCurrentAnimationFrame(), (int) x, (int) y, null);
        g.draw(rightHand());
    }

    @Override
    protected boolean collisionWithTile(int x, int y) {
        return handler.getWorld().getTile(x, y).isSolid();
    }

    protected TileMainLevel getSpecificTile(int x, int y) {
        WorldPlat tempWorld = (WorldPlat) handler.getWorld();
        TileMainLevel tempTile = (TileMainLevel) tempWorld.getTile(x, y);
        return tempTile;
    }

    public void backwards(Punto punto) {
        x = punto.x;
        y = punto.y;
    }

    public void moveX() {
        if (Window.keyManager.right) {
            if (hasRightCollision()) {
                double extraVelocity = xMove * percentajeVelocity * handler.getDeltaTime();
                x += (xMove * handler.getDeltaTime() + extraVelocity);
            } else {
                int tx = (int) (x + xMove * handler.getDeltaTime() + bounds.x + bounds.width) / TileMainLevel.TILEWIDTH;
                x = tx * TileMainLevel.TILEWIDTH - bounds.x - bounds.width - 1;
            }
        }
        if (Window.keyManager.left) {
            if (hasLeftCollision()) {
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
        cheackDamage();
    }

    private Rectangle rightHand() {
        Rectangle hand = new Rectangle((int) (x + bounds.x) + 25, (int) (y + bounds.y) + 35, bounds.width / 2 + 5, bounds.height / 4);
        return hand;
    }

    private TileMainLevel getRightHandInteraction(Rectangle hand) {
        hand.width = hand.width * 2 - 5;
        hand.x += 20;
        return getSpecificTile(hand.x / TileMainLevel.TILEWIDTH, hand.y / TileMainLevel.TILEHEIGHT);
    }

    private boolean hasLeftCollision() {
        int tx = (int) (x + xMove * handler.getDeltaTime() + bounds.x) / TileMainLevel.TILEWIDTH;
        return !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / TileMainLevel.TILEHEIGHT)
                && !collisionWithTile(tx, (int) (y + bounds.y) / TileMainLevel.TILEHEIGHT);
    }

    private TileMainLevel getTileLeftTouching() {
        int tx = (int) (x + xMove * handler.getDeltaTime() + bounds.x) / TileMainLevel.TILEWIDTH;
        if (!hasLeftCollision()) {
            return getSpecificTile(tx, (int) (y + bounds.y) / TileMainLevel.TILEHEIGHT);
        } else {
            return null;
        }
    }

    private boolean hasRightCollision() {
        int tx = (int) (x + xMove * handler.getDeltaTime() + bounds.x + bounds.width) / TileMainLevel.TILEWIDTH;
        return !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / TileMainLevel.TILEHEIGHT)
                && !collisionWithTile(tx, (int) (y + bounds.y) / TileMainLevel.TILEHEIGHT);
    }

    private TileMainLevel getTileRightTouching() {
        int tx = (int) (x + xMove * handler.getDeltaTime() + bounds.x + bounds.width) / TileMainLevel.TILEWIDTH;
        if (!hasRightCollision()) {
            return getSpecificTile(tx, (int) (y + bounds.y) / TileMainLevel.TILEHEIGHT);
        } else {
            return null;
        }
    }

    private boolean hasDownCollision() {
        int ty = (int) (y + yMove * handler.getDeltaTime() + bounds.y + bounds.height) / TileMainLevel.TILEHEIGHT;
        return !collisionWithTile((int) (x + bounds.x + bounds.width) / TileMainLevel.TILEWIDTH, ty)
                && !collisionWithTile((int) (x + bounds.x) / TileMainLevel.TILEWIDTH, ty);
    }

    private TileMainLevel getTileDownTouching() {
        int ty = (int) (y + yMove * handler.getDeltaTime() + bounds.y + bounds.height) / TileMainLevel.TILEHEIGHT;
        if (!hasDownCollision()) {
            return getSpecificTile((int) (x + bounds.x) / TileMainLevel.TILEWIDTH, ty);
        } else {
            return null;
        }
    }

    private boolean hasUpCollision() {
        int ty = (int) (y + bounds.y + yMove * handler.getDeltaTime()) / TileMainLevel.TILEHEIGHT;
        return !collisionWithTile((int) (x + bounds.x + bounds.width) / TileMainLevel.TILEWIDTH, ty)
                && !collisionWithTile((int) (x + bounds.x) / TileMainLevel.TILEWIDTH, ty);
    }

    private TileMainLevel getTileUpTouching() {
        int ty = (int) (y + bounds.y + yMove * handler.getDeltaTime()) / TileMainLevel.TILEHEIGHT;
        if (!hasDownCollision()) {
            return getSpecificTile((int) (x + bounds.x) / TileMainLevel.TILEWIDTH, ty);
        } else {
            return null;
        }
    }

    public void fall() {
        if (hasDownCollision() && !jumping) {
            yMove = speedY;
            y += yMove * handler.getDeltaTime();
            speedY += gravity * handler.getDeltaTime();
        } else {
            if (!jumping) {
                int ty = (int) (y + yMove * handler.getDeltaTime() + bounds.y + bounds.height) / TileMainLevel.TILEHEIGHT;
                y = ty * TileMainLevel.TILEHEIGHT - bounds.y - bounds.height - 1;
            }
            yMove = 100;
            isGround = true;
        }
    }

    public void jumping() {
        if (hasUpCollision() && jumping) {
            yMove = speedY;
            y -= yMove * handler.getDeltaTime();
            speedY -= gravity * handler.getDeltaTime();
            if (speedY <= 0 || !hasUpCollision()) {
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

    //Conseguir la animación en cada movimiento
    private BufferedImage getCurrentAnimationFrame() {
        if (jumping || !isGround) {
            if (!isGround) {
                return animD.getCurrentFrame();
            } else if (jumping) {
                return animUp.getCurrentFrame();
            }
        } else {
            if (Window.keyManager.left) {
                return animL.getCurrentFrame();
            } else if (Window.keyManager.right) {
                return animR.getCurrentFrame();
            }
        }
        return animSS.getCurrentFrame();
    }

    public void checkAttacks() {
        Rectangle cb = getCollisionBounds();
    }

    private void checkInteraction(Rectangle hand) {
        TileMainLevel auxT = getRightHandInteraction(hand);
        if (auxT != null) {
            if (auxT.isInteractive()) {
                System.out.println("Interaccion IZQUIERDA");
                auxT.changeInteraction();
                buttonPressed = true;
            } else {
                buttonPressed = false;
            }
        }
        auxT = getTileRightTouching();
        if (auxT != null) {
            if (auxT.isInteractive()) {
                System.out.println("Interaccion DERECHA");
                buttonPressed = true;
            }
        }

    }

    private void cheackDamage() {
        TileMainLevel auxT = getTileDownTouching();
        if (auxT != null) {
            if (auxT.makeDamage()) {
                System.out.println("MUERTO ABAJO");
                active = false;
                die();
            }
        }
        auxT = getTileLeftTouching();
        if (auxT != null) {
            if (auxT.makeDamage()) {
                System.out.println("MUERTO IZQUIERDA");
                active = false;
                die();
            }
        }
        auxT = getTileRightTouching();
        if (auxT != null) {
            if (auxT.makeDamage()) {
                System.out.println("MUERTO DERECHA");
                active = false;
                die();
            }
        }
    }

    public boolean isTouchingLap() {
        TileMainLevel auxT = getTileRightTouching();
        if (auxT != null) {
            if (auxT.getId() == 5) {
                return true;
            }
        }
        return false;
    }

    public boolean isButtonPressed() {
        return buttonPressed;
    }

    public void setButtonPressed() {
        this.buttonPressed = !buttonPressed;
    }

    public boolean isReset() {
        return reset;
    }

    public void setReset(boolean reset) {
        this.reset = reset;
    }

    public int getAmountOfReturns() {
        return amountOfReturns;
    }

    public int getMaxReturns() {
        return maxReturns;
    }

    public void setAmountOfReturns(int amountOfReturns) {
        this.amountOfReturns = amountOfReturns;
    }

    public void setMaxReturns(int maxReturns) {
        this.maxReturns = maxReturns;
    }

}
