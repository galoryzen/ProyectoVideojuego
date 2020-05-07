package GameStates;

import Audio.AudioLoader;
import Tilemaps.Background;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import MainG.Handler;
import Tilemaps.*;
import tinysound.Music;
import tinysound.Sound;

public class MenuState extends GameState{

    Music bgMusic;
    Sound menuUp;
    Background bg;
    Handler handler;

    private int currentChoice = 0;
    public long lastPressedTime = 0;

    static final long minPressedDelay = 150;

    private String[] options = {
        "Start",
        "Help",
        "Creators",
        "Story",
        "Quit",};

    private Color titleColor;
    private Font titleFont;
    private Font font;

    public MenuState(GameStateManager gsm, Handler handler) {
        super(gsm);
        this.handler = handler;
        try {
            bg = new Background(Assets.fondoMenu, 1);
            bg.setVector(2, 0);
            titleColor = new Color(128, 0, 0);
            titleFont = new Font("Century Gothic", Font.PLAIN, 28);
            font = new Font("Arial", Font.PLAIN, 12);
        } catch (Exception e) {
            e.printStackTrace();
        }
        init();
    }

    @Override
    public void update() {
        handleInput();
        bg.update();
        musicControl();
    }

    @Override
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
        bgMusic = AudioLoader.bgMusic;
        bgMusic.setVolume(0.3);
        bgMusic.play(true);
        menuUp = AudioLoader.upMenu;
    }

    public void handleInput() {
        long now = System.currentTimeMillis();
        if (now - lastPressedTime < minPressedDelay) {
            return;
        }
        if (handler.getGame().getKeyManager().up) {
            currentChoice--;
            menuUp.play();
            if (currentChoice < 0) {
                currentChoice = 4;
            }
        } else if (handler.getGame().getKeyManager().down) {
            currentChoice++;
            menuUp.play();
            if (currentChoice > 4) {
                currentChoice = 0;
            }
        } else if (handler.getGame().getKeyManager().space) {
            bgMusic.stop();
            gsm.setState(3);
        }
        if (handler.getGame().getKeyManager().enter) {
            bgMusic.stop();
            gsm.setState(2);
        }
        if(handler.getGame().getKeyManager().test){
            bgMusic.stop();
            gsm.setState(1);
        }

        lastPressedTime = now;
    }

    @Override
    public void musicControl() {

    }

    @Override
    public World getWorld() {
        return null;
    }
}
