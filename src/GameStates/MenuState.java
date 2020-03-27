package GameStates;

import Audio.AudioClip;
import Audio.AudioPlayer;
import Handlers.ThreadPool;
import java.awt.image.BufferedImage;
import Tilemaps.Background;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import Handlers.KeyManager;
import MainG.Handler;
import Tilemaps.*;

public class MenuState extends GameState {


    AudioPlayer menuMovement, menuMomementDown;
    AudioPlayer bgMusic;
    Background bg;
    Handler handler;
    ThreadPool pool;

    private int currentChoice = 0;
    public long lastPressedTime = 0;

    static final long minPressedDelay = 100;

    private String[] options = {
        "Start",
        "Help",
        "Creators",
        "Story",
        "Quit",};

    private Color titleColor;
    private Font titleFont;
    private Font font;

    public MenuState(GameStateManager gsm, ThreadPool pool, Handler handler) {
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
        bgMusic = new AudioPlayer(AudioClip.bgMusic, -15);
        menuMovement = new AudioPlayer(AudioClip.movementMenu, 0);
        menuMomementDown = new AudioPlayer(AudioClip.movementMenuDown, 0);
        pool.runTask(bgMusic);
    }

    public void handleInput() {
        long now = System.currentTimeMillis();
        if(now - lastPressedTime < minPressedDelay){
            return;
        }
        if(handler.getGame().getKeyManager().up){
            while (!menuMovement.clip.isRunning()) {
                menuMovement.play();
            }
            currentChoice--;
            if (currentChoice < 0) {
                currentChoice = 4;
            }
        } else if (handler.getGame().getKeyManager().down) {
            while (!menuMovement.clip.isRunning()) {
                menuMovement.play();
            }
            currentChoice++;
            if (currentChoice > 4) {
                currentChoice = 0;
            }
        } else if (handler.getGame().getKeyManager().space) {
            gsm.setState(4);
            bgMusic.clip.stop();
        }
        lastPressedTime = now;
    }

    @Override
    public void musicControl() {

    }
}
