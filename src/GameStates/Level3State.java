package GameStates;

import Tilemaps.Background;
import java.awt.Graphics2D;
import Handlers.detectorTeclas;
import Tilemaps.Assets;
import java.awt.Color;
import Tilemaps.Assets;
import java.awt.image.BufferedImage;
import ThirdMinigame.Nave;

public class Level3State extends GameState implements Mechanics {

    Background bg;
    public detectorTeclas teclas;
    BufferedImage personaje_nave;
    Nave nave;

    public Level3State(GameStateManager gsm) {
        super(gsm);
        teclas = new detectorTeclas();
        try {
            bg = new Background(Assets.fondoSpaceInvaders, 1);
            bg.setVector(-3, 0);
        } catch (Exception e) {
            System.out.print(e);
        }
        personaje_nave = Assets.spriteNina;
        nave = new Nave(personaje_nave, 0, 0, true, 0f, 0f);
    }

    @Override
    public void init() {

    }

    @Override
    public void update() {
        handleInput();
        bg.update();
    }

    @Override
    public void draw(Graphics2D g) {
        // Dibuja el fondo
        bg.draw(g);

        // Crear Personaje
        g.drawImage(nave.getSprite(), (int) nave.getX(), (int) nave.getY(), null);
    }

    @Override
    // ALEX E ISAAC
    // Mecanicas de cada minijuego
    public void handleInput() {
        // Se mueve arriba
        if (teclas.UP.esPresionada) {
            nave.setY( (int) nave.getY() - 1);
        }
        // Se mueve abajo
        if (teclas.DOWN.esPresionada) {
            nave.setY((int)nave.getY() + 1);
        }
        // Se mueve a la derecha
        if (teclas.RIGHT.esPresionada) {
            nave.setX((int)nave.getX() + 1);
        }
        // Se mueve a la izquierda
        if (teclas.LEFT.esPresionada) {
            nave.setX((int) nave.getX() - 1);
        }
        // Dispara balas
        if (teclas.TEST.esPresionada) {

        }
    }

    // Jorge 
    // Generar asteroides aleatorios en pantalla
    public void generarAsteroides() {

    }

    @Override
    public void detectarColisiones() {
    }
}
