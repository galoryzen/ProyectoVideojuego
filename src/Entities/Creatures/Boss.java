/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Creatures;

import Entities.Entity;
import Entities.EntityManager;
import Entities.Items.AutoMissil;
import MainG.Handler;
import SecondMinigame.HUD;
import Tilemaps.Animation;
import Tilemaps.Assets;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * Clase del Jefe del minijuego.
 * @version 1.6
 */
public class Boss extends Enemy {

    private long now = 0, last = 0, GameTime;
    private int TackleSpeed = 20, TackleCooldown = 10000;
    private long lastAttackTimer, attackCooldown = 1000, attackTimer = attackCooldown;
    private boolean tackling = false;
    private int summonNumber = 2;
    private Animation anm;
    private HUD hud;
    /**
     * 
     * @param handler Handler del juego.
     * @param manager Entity manager al que se agrega.
     * @param x Coordenada en X.
     * @param y Coordenada en Y.
     * @param width Ancho.
     * @param height Alto.
     * @param hud su HUD.
     */
    public Boss(Handler handler, EntityManager manager, float x, float y, int width, int height, HUD hud) {
        super(handler, manager, x, y, width, height, hud);

        last = System.currentTimeMillis();
        this.setHealth(300);
        speed = 200;
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = 100;
        bounds.height = 100;
        anm = new Animation(200, Assets.Boss);
    }
    
    /**
     * Metodo de lo que sucede luego que el enemigo muere.
     */
    @Override
    public void die() {
    }
    
    /**
     * Metodo que actualiza lo que debe pasar con el boss.
     */
    @Override
    public void update() {
        //Attack conditions
        if (this.getHealth() < 50) {
            this.setTackleSpeed(8);
            this.setTackleCooldown(8000);
            setSummonNumber(4);
        } else if (this.getHealth() < 20) {
            this.setTackleSpeed(10);
            this.setTackleCooldown(5000);
            setSummonNumber(6);
        }
        anm.update();
        move();
        checkAttacks();

    }
    
    /**
     * Metodo para rendear al boss en pantalla.
     * @param g Los graficos que necesita.
     */
    public void render(Graphics2D g) {
        g.setColor(Color.red);
        if (tackling == false) {
            g.drawImage(getCurrentFrameAnimation(), (int) x, (int) y, bounds.width, bounds.height, null);
        } else {
            g.drawImage(Assets.charge, (int) x, (int) y, bounds.width, bounds.height, null);
        }
        //Health bar
        g.setColor(Color.red);
        g.fillRect(20, 50, (int) (this.getHealth() * 3.5), 20);

    }
    
    /**
     * Mueve al enemigo.
     */
    @Override
    public void move() {
        now += System.currentTimeMillis() - last;
        last = System.currentTimeMillis();

        if (now > TackleCooldown) {
            tackle();
        } else {
            moveY();
            y += Ymove;
            if (x > 980) {
                x -= speed * handler.getDeltaTime();
            }
        }
    }
    
    /**
     * Mueve al enemigo en Y
     */
    private void moveY() {
        if (y > manager.getPlayer().getY()) {
            Ymove = (float) (-speed * handler.getDeltaTime());
        } else if (y < manager.getPlayer().getY()) {
            Ymove = (float) (speed * handler.getDeltaTime());
        } else {
            Ymove = 0;
        }
    }
    
    /**
     * Revisa si le ha pegado una bala del usuario.
     */
    private void checkAttacks() {

        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if (attackTimer < attackCooldown) //No hace lo de abajo
        {
            return;
        }
        shoot();
        Rectangle cb = getCollisionBounds();
        attackTimer = 0;
        for (Entity e : manager.getEntities()) {
            if (!e.equals(this)) {
                if (e.getCollisionBounds().intersects(cb) && !(e instanceof AutoMissil)) {
                    if (tackling) {
                        e.hurt(3);
                    } else {
                        e.hurt(1);
                    }
                }
            }
        }
    }
    
    /**
     * Ejecuta el ataque tackle.
     */
    public void tackle() {

        tackling = true;

        if (x > -50) {
            x -= TackleSpeed;
        } else {
            System.out.println("llegó");
            setX(1050);
            tackling = false;
            now = 0;
        }
    }
    
    /**
     * Hace que el boss dispare.
     */
    private void shoot() {
        for (int i = 1; i <= this.getSummonNumber(); i++) {
            if (i % 2 == 0) {
                manager.addEntity(new AutoMissil(handler, manager, this.getX(), this.getY() + 20 * i));
            } else if (i % 2 != 0) {
                manager.addEntity(new AutoMissil(handler, manager, this.getX(), this.getY() - 20 * i));
            }
        }
    }

    private BufferedImage getCurrentFrameAnimation() {
        return anm.getCurrentFrame();
    }

    public void setX(float x) {
        this.x = x;
    }

    public int getTackleSpeed() {
        return TackleSpeed;
    }

    public void setTackleSpeed(int TackleSpeed) {
        this.TackleSpeed = TackleSpeed;
    }

    public int getTackleCooldown() {
        return TackleCooldown;
    }

    public void setTackleCooldown(int TackleCooldown) {
        this.TackleCooldown = TackleCooldown;
    }

    public int getSummonNumber() {
        return summonNumber;
    }

    public void setSummonNumber(int summonNumber) {
        this.summonNumber = summonNumber;
    }

}
