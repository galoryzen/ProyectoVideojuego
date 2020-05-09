package GameStates;

import Audio.AudioLoader;
import Tilemaps.Background;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import MainG.Handler;
import MainG.Window;
import Tilemaps.*;
import UI.ClickListener;
import UI.UIImageButton;
import UI.UIManager;
import java.awt.image.BufferedImage;
import tinysound.Music;
import tinysound.Sound;

public class MenuState extends GameState{

    Music bgMusic;
    Sound menuUp;
    Background bg;
    Handler handler;

    private int currentChoice = 0;
    public long lastPressedTime = 0;
    
    private Animation anm;
    
    static final long minPressedDelay = 150;
    
    private UIManager uimanager;

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
        uimanager= new UIManager(handler);
        uimanager.addUIObject(new UIImageButton(100f,100f,100,100,Assets.Boss,new ClickListener() {
            @Override
            public void onClick() {
                gsm.setState(2);
            }
        }));
        anm=new Animation(100,Assets.backgroundMenu);
        init();
    }

    @Override
    public void update() {
        handleInput();
        anm.update();
        musicControl();
        uimanager.tick();
    }

    public void draw(Graphics2D g) {

        g.drawImage(getCurrentFrame(), 0, 0,1080, 720,null);
        // Aplica colores al titulo del juego
        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("SIRVE", 270, 70);
        g.drawString("x"+Window.mouse.getMouseX()+" y"+Window.mouse.getMouseY() ,Window.mouse.getMouseX(), Window.mouse.getMouseY());
        if(Window.mouse.isLeftPressed()){
            g.setColor(Color.blue);
        }
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
        
        uimanager.render(g);
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
        if (Window.keyManager.up) {
            currentChoice--;
            menuUp.play();
            if (currentChoice < 0) {
                currentChoice = 4;
            }
        }
        if (Window.keyManager.down) {
            currentChoice++;
            menuUp.play();
            if (currentChoice > 4) {
                currentChoice = 0;
            }
        }
        if (Window.keyManager.space) {
            bgMusic.stop();
            gsm.setState(3);
        }
        if (Window.keyManager.enter) {
            bgMusic.stop();
            gsm.setState(2);
        }
        if (Window.keyManager.test) {
            bgMusic.stop();
            gsm.setState(1);
        }
        

        lastPressedTime = now;
    }

    @Override
    public void musicControl() {

    }
    
    BufferedImage getCurrentFrame(){
       return anm.getCurrentFrame();
    }

    @Override
    public World getWorld() {
        return null;
    }
}
