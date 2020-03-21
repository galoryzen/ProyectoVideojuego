package GameStates;

import java.awt.image.BufferedImage;
import Tilemaps.Background;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import Handlers.detectorTeclas;
import Tilemaps.*;


public class MenuState extends GameState {

    private detectorTeclas Teclas = new detectorTeclas();

    Background bg;

    private int currentChoice = 0;
    private String[] options = {
        "Start",
        "Help",
        "Creators",
        "Story",
        "Quit",};

    private Color titleColor;
    private Font titleFont;
    private Font font;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        try {
            bg = new Background(Assets.fondoMenu, 1);
            bg.setVector(2, 0);
            titleColor = new Color(128, 0, 0);
            titleFont = new Font("Century Gothic", Font.PLAIN, 28);
            font = new Font("Arial", Font.PLAIN, 12);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        handleInput();
        bg.update();
    }

    public void draw(Graphics2D g) {

        // Dibuja el Background 
        bg.draw(g);

        // Aplica colores al titulo del juego
        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("SIRVE", 270, 70);

        // Añade las opciones del menu
        g.setFont(font);
        for (int i = 0; i < options.length; i++) {
            if (i == currentChoice) {
                g.setColor(Color.BLACK);
            } else {
                g.setColor(Color.red);
            }
            g.drawString(options[i], 270, 160 + i * 15);
        }

    }

    @Override
    public void init() {

    }

    @Override
    public void handleInput() {
        if (Teclas.UP.esPresionada) {
            currentChoice++;
            if (currentChoice >= options.length) {
                currentChoice = 0;
            }
        } else if (Teclas.DOWN.esPresionada) {
            currentChoice--;
            if (currentChoice <= 0) {
                currentChoice = 4;
            }
        }else if(Teclas.TEST.esPresionada){
            gsm.setState(4);
        }
    }
}
