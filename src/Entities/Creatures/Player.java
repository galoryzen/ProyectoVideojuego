package Entities.Creatures;

import Audio.AudioLoader;
import Entities.EntityManager;
import Entities.Items.Bullet;
import Tilemaps.Assets;
import MainG.Handler;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import tinysound.Sound;

/**
 *
 * @author German David
 */
public class Player extends Character {

    private Sound shot = AudioLoader.shot;
    public int Score;
    public Bullet bullet;
    public static int bullcount = 0;
    private long clock, now = 0;
    private float ShootSpeed = 0.3f;
    private BufferedImage[] naveStates = new BufferedImage[3];

    public int i = 0;
    private long timerAnimation = 0;

    public Player(Handler handler, EntityManager manager, float x, float y) {
        super(handler, manager, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATUR_HEIGHT);
        
        timerAnimation = System.currentTimeMillis();
        Score = 0;
        bounds.x = 16;
        bounds.y = 10;
        bounds.width = 160;
        bounds.height = 60;
        
        this.setHealth(15);
        naveStates[0] = Assets.naveOn;
        naveStates[1] = Assets.naveSemiOff;
        naveStates[2] = Assets.naveOff;
    }

    @Override
    public void update() {
        clock = System.nanoTime();
        getInput();
        move();

    }

    @Override
    public void move() {

        x += Xmove;
        if (x + bounds.width < 0) {
            x = -bounds.width;
        }
        if (x + bounds.width > handler.getGame().getWidth()) {
            x = handler.getGame().getWidth() - bounds.width;
        }
        y += Ymove;
        if (y < -bounds.height) {
            y = -bounds.height + 1;
        }
        if (y + bounds.height > handler.getGame().getHeight()) {
            y = handler.getGame().getHeight() - bounds.height;
        }
    }

    @Override
    public void getInput() {

        Xmove = 0;
        Ymove = 0;

        if (handler.getGame().getKeyManager().up) {
            Ymove = -speed;
        }
        if (handler.getGame().getKeyManager().down) {
            Ymove = speed;
        }
        if (handler.getGame().getKeyManager().right) {
            Xmove = speed;
        }
        if (handler.getGame().getKeyManager().left) {
            Xmove = -speed;
        }
        if (handler.getGame().getKeyManager().space && canShoot(clock - now)) {
            shot.play();
            manager.addEntity(new Bullet(handler, manager, this.getX() + this.getWidth() / 1.3f, this.getY() + this.getHeight() / 3.3f, 100, 100, this));
        }
    }

    @Override
    public void render(Graphics2D g) {
//        g.setColor(Color.red);
//        g.fillRect((int) x + bounds.x, (int) y + bounds.y, bounds.width, bounds.height);
        g.drawImage(getCurrentImage(), (int) (x), (int) (y), null);
    }

    public boolean canShoot(long c) {
        if ((double) c / 1000000000 >= ShootSpeed) {
            System.out.println("" + (double) c / 1000000000);
            now = clock;
            return true;
        } else {
            return false;
        }
    }

    public float getShootSpeed() {
        return ShootSpeed;
    }

    public void setShootSpeed(float ShootSpeed) {
        this.ShootSpeed = ShootSpeed;
    }

    @Override
    public void die() {
        System.out.println("You lose");
    }

    private BufferedImage getCurrentImage() {
        if (System.currentTimeMillis() - timerAnimation>= 100) {
            i++;
            if(i == 3){
                i = 0;
            }
            timerAnimation = System.currentTimeMillis();
        }
        return naveStates[i];
    }

    public int getBulletCount() {
        return bullcount;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int Score) {
        this.Score = Score;
    }

}
