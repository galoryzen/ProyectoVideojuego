package GameStates;

import Audio.AudioLoader;
import Tilemaps.Background;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import MainG.Handler;
import Tilemaps.*;
import java.util.concurrent.ThreadPoolExecutor;
import tinysound.Music;

public class MenuState extends GameState {
    
    Music bgMusic;
    Background bg;
    Handler handler;
    ThreadPoolExecutor pool;

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

    public MenuState(GameStateManager gsm, ThreadPoolExecutor pool, Handler handler) {
        super(gsm);
        this.handler = handler;
        this.pool = pool;
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
    }

    public void handleInput() {
        long now = System.currentTimeMillis();
        if(now - lastPressedTime < minPressedDelay){
            return;
        }
        if(handler.getGame().getKeyManager().up){
            currentChoice--;
            if (currentChoice < 0) {
                currentChoice = 4;
            }
        } else if (handler.getGame().getKeyManager().down) {
            currentChoice++;
            if (currentChoice > 4) {
                currentChoice = 0;
            }
        } else if (handler.getGame().getKeyManager().space) {
            gsm.setState(4);
            bgMusic.stop();
        }
        lastPressedTime = now;
    }

    @Override
    public void musicControl() {

    }
}
