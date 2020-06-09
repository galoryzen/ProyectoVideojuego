package Entities.Creatures;

import Audio.AudioLoader;
import Entities.Entity;
import Entities.EntityManager;
import Entities.Items.Bullet;
import MainG.Handler;
import SecondMinigame.HUD;
import Tilemaps.Animation;
import Tilemaps.Assets;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import tinysound.Music;

/**
 * Enemigo DownEnemy del Space Invaders.
 */
public class DownEnemy extends Enemy {

    private HUD hud;
    private long lastAttackTimer, attackCooldown = 2000, attackTimer = 0;
    private boolean updownswitch = false;
    private static Music explosionEnemy;
    private Animation anm;
    
    /**
     * Constructor de DownEnemy.
     * @param handler Handler.
     * @param manager Entity manager al que pertenece.
     * @param x Coordenada en X.
     * @param y Coordenada en Y.
     * @param width Anchura.
     * @param height Altura.
     * @param hud HUD del enemigo.
     */
    public DownEnemy(Handler handler, EntityManager manager, float x, float y, int width, int height, HUD hud) {
        super(handler, manager, x, y, width, height, hud);
        this.hud = hud;
        this.setHealth(20);
        Ymove = (int) (Math.random() * 100 + 300);
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = 104;
        bounds.height = 110;
        explosionEnemy = AudioLoader.damageEnemyShip;
        
        anm=new Animation(400,Assets.downEnemy);
    }
    
    /**
     * Lo que se ejecuta cuando muere.
     */
    @Override
    public void die() {
        explosionEnemy.play(false);
        hud.setPoint(hud.getPoint() + 2);
        this.setActive(false);
    }
    
    /**
     * Actualiza lo que está realizando.
     */
    @Override
    public void update() {
        move();
        anm.update();
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if (attackTimer >= attackCooldown) {
            shot();
            attackTimer = 0;
        }
        checkAttacks();
    }
    
    /**
     * Renderiza al enemigo.
     * @param g Graphics2D que necesita.
     */
    @Override
    public void render(Graphics2D g) {
        g.drawImage(getCurrentAnimationFrame(), (int) this.x, (int) this.y, width, height, null);
    }
    /**
     * Mueve al enemigo.
     */
    public void move() {
        if (updownswitch) {
            if (this.y <= 40) {
                updownswitch = !updownswitch;
            }
            this.y -= Ymove * handler.getDeltaTime();
        } else {
            if (this.y >= 580) {
                updownswitch = !updownswitch;
            }
            this.y += Ymove * handler.getDeltaTime();
        }
    }
    
    /**
     * Hace que el enemigo dispare.
     */
    private void shot() {
        manager.addEntity(new Bullet(handler, manager, this.getX(), this.getY() + this.getHeight() / 3.3f, 100, 100, this));
    }
    
    /**
     * Revisa si fue herido por una bala.
     */
    private void checkAttacks() {
        if (attackTimer < attackCooldown) //No hace lo de abajo
        {
            return;
        }

        Rectangle cb = getCollisionBounds();

        attackTimer = 0;

        for (Entity e : manager.getEntities()) {
            if (!e.equals(this) && !(e instanceof Asteroid)) {
                if (e.getCollisionBounds().intersects(cb)) {
                    System.out.println("Me pego");
                    e.hurt(3);
                    return;
                }
            }
        }
    }
    
    /**
     * Conseguir la animación en cada movimiento
     */
    private BufferedImage getCurrentAnimationFrame(){
        return anm.getCurrentFrame();
    }
}
