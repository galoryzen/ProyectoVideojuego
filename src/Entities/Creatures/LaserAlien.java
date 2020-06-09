/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Creatures;

import Entities.Entity;
import Entities.EntityManager;
import GameStates.MenuState;
import MainG.Handler;
import Tilemaps.Assets;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * Enemigo LaserAlien del Space Invaders.
 */
public class LaserAlien extends Creature {

    private long lastAttackTimer, attackCooldown = 2000, attackTimer = 0;
    private boolean shootin = false;
    
    /**
     * Constructor de Creature.
     * @param handler Handler.
     * @param manager Entity manager al que pertenece.
     * @param x Coordenada en X.
     * @param y Coordenada en Y.
     * @param width Anchura.
     * @param height Altura.
     */
    public LaserAlien(Handler handler, EntityManager manager, float x, float y, int width, int height) {
        super(handler, manager, x, y, width, height);
        //Cronometro para saber el tiempo transcurrido antes de su generación
        lastAttackTimer = System.currentTimeMillis();
        this.width = 60;
        this.height = 36;

        this.speed = 0.005f;

        bounds.x = 0;
        bounds.y = 0;
        bounds.width = 60;
        bounds.height = 36;
    }
    /**
     * Lo que se realiza luego de que muere.
     */
    @Override
    public void die() {
    }
    /**
     * Actualiza al enemigo.
     */
    @Override
    public void update() {

        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();

        if (attackTimer > 2000) {
            shootRay();
        } else {
            movex();
            x += Xmove ;
            if (y < 0) {
                y += speed * handler.getDeltaTime();
            }
        }

    }
    
    /**
     * Mueve al enemigo en x.
     */
    public void movex() {
        if (x > manager.getPlayer().getX() + manager.getPlayer().getWidth() / 2) {
            Xmove = (float) (-speed * handler.getDeltaTime());
        } else if (x < manager.getPlayer().getX() + manager.getPlayer().getWidth() / 2) {
            Xmove = (float) (speed * handler.getDeltaTime());
        } else {
            Xmove = 0;
        }
    }
    
    /**
     * Reenderiza al enemigo.
     * @param g Graphics2D que necesita.
     */
    @Override
    public void render(Graphics2D g) {
        g.drawImage(Assets.LaserAlien, (int) x, 0, null);
        if (shootin) {
            g.drawImage(Assets.laser, (int) (this.getX() + this.getWidth() / 4), (int) (this.getY()), null);
        }
    }
    
    /**
     * Dispara el rasho laser.
     */
    private void shootRay() {

        shootin = true;
        Rectangle laser = new Rectangle((int) this.x, (int) (y + this.getWidth()), 40, 720);

        for (Entity e : manager.getEntities()) {
            if (e.getCollisionBounds().intersects(laser)) {
                e.hurt(1);
            }
        }

        if (attackTimer > 4000) {
            attackTimer = 0;
            shootin = false;

        }
    }
}
