package GameStates;

import Audio.AudioLoader;
import MainG.GameLauncher;
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
import UI.UIObject;
import UtilLoader.SaveGame;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import tinysound.Music;
import tinysound.Sound;

public class MenuState extends GameState implements SaveGame {

    Music bgMusic;
    Sound menuUp;
    Background bg;
    Handler handler;

    private int currentChoice = 0;
    public long lastPressedTime = 0;

    private Animation anm;
    private boolean sw;
    static final long minPressedDelay = 150;

    private UIManager uimanager;
    public UIImageButton exit,option,newg,continu,tutorial;


    private Color titleColor;
    private Font titleFont;
    private Font font;

    public MenuState(GameStateManager gsm, Handler handler) {
        super(gsm);
        this.handler = handler;
        sw=false;
        
        uimanager = new UIManager(handler);
        uimanager.addUIObject( new UIImageButton(1047f, 0f, 32, 32, Assets.minimize, new ClickListener() {
            @Override
            public void onClick() {
                GameLauncher.window.setState(GameLauncher.window.ICONIFIED);
            }
        }));
        uimanager.addUIObject(exit=new UIImageButton(50f,550f, 256, 57, Assets.UIMenu[5], new ClickListener() {
            @Override
            public void onClick() {
                System.exit(0);
            }
        }));
        
        uimanager.addUIObject(option=new UIImageButton(50f,460f, 256, 57, Assets.UIMenu[6], new ClickListener() {
            @Override
            public void onClick() {
                
            }
        }));
        
        uimanager.addUIObject(continu=new UIImageButton(50f,380f, 256, 57, Assets.UIMenu[7], new ClickListener() {
            @Override
            public void onClick() {
                
            }
        }));
        
        uimanager.addUIObject(newg=new UIImageButton(50f,300f, 256, 57, Assets.UIMenu[8], new ClickListener() {
            @Override
            public void onClick() {
                gsm.setState(1);
            }
        }));
        uimanager.addUIObject(tutorial=new UIImageButton(900,650, 256, 57, Assets.UIMenu[1], new ClickListener() {
            @Override
            public void onClick() {
                
            }
        }));
        anm = new Animation(100, Assets.backgroundMenu);
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
        if( sw ){
        g.drawImage(Assets.backgroundMenu[22], 0, 0, 1080, 720, null);
        g.drawImage(Assets.Title2,  380, -60,null);
            // Aplica colores al titulo del juego
        g.drawString("x" + Window.mouse.getMouseX() + " y" + Window.mouse.getMouseY(), Window.mouse.getMouseX(), Window.mouse.getMouseY());

        // Añade las opciones del menu
        if(noHovering()){
            noMoreCurrently();
            currentChoice=0;
        }else{
        for (int i = 1; i <= 4; i++) {
            if (i == currentChoice) {
                    switch(currentChoice){
                        case 1:
                            newg.setCurrent(true);
                            noMoreCurrently(newg);
                            
                        break;
                        case 2:
                            continu.setCurrent(true);
                            noMoreCurrently(continu);
                        break;
                        case 3:
                            option.setCurrent(true);
                            noMoreCurrently(option);
                        break;
                        case 4:
                            exit.setCurrent(true);
                            noMoreCurrently(exit);
                        break;
                    }
                } 
            }
        }

        uimanager.render(g);
        }else{
            g.drawImage(getCurrentFrame(), 0, 0, 1080, 720, null);
            if(getCurrentFrame().equals(Assets.backgroundMenu[22])){
                sw=true;
            }
        }
        
    }

    @Override
    public void init() {
        while (Window.mouse == null) {
            System.out.println("Cargando");
        }
        
        Window.mouse.setUIManager(uimanager);
        bgMusic = AudioLoader.bgMusic;
        bgMusic.setVolume(0.3);
        bgMusic.play(true);
        menuUp = AudioLoader.upMenu;
    }

    public void handleInput() {
        long now = System.currentTimeMillis();
        if (Window.keyManager.space) {
            bgMusic.stop();
            gsm.setState(5);
        }
        // Opcion de continuar donde se habia dejado la partida
        if (Window.keyManager.enter) {
            switch (currentChoice) {
                case 2:
                    loadData();
                    break;
                case 4:
                    exit.onClick();
                    break;
                default:
                    bgMusic.stop();
                    gsm.setState(2);
                    break;
            }
        }
        if (Window.keyManager.test) {
            bgMusic.stop();
            gsm.setState(1);
        }
        
        if (now - lastPressedTime < minPressedDelay) {
            return;
        }
        if (Window.keyManager.up) {
            currentChoice--;
            menuUp.play();
            if (currentChoice < 1) {
                currentChoice = 4;
            }
        }
        if (Window.keyManager.down) {
            currentChoice++;
            menuUp.play();
            if (currentChoice > 4) {
                currentChoice = 1;
            }
        }
        
        lastPressedTime = now;
    }

    @Override
    public void musicControl() {

    }

    BufferedImage getCurrentFrame() {
        
            return anm.getCurrentFrame();
        
    }

    @Override
    public World getWorld() {
        return null;
    }

    @Override
    public void getInsertData() {

    }

    @Override
    public void loadData() {
        BufferedReader br = null;
        int state = 0;
        String stateVerification = "";
        try {
            br = new BufferedReader(new FileReader("savefile.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MenuState.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            stateVerification = br.readLine();
        } catch (Exception e) {

        }
        // Se verifica que el archivo de TXT de guardado, tenga un state como primera linea, sino, esta es la primera vez que se inicia el juego
        if (stateVerification.isEmpty() || stateVerification.isEmpty()) {
            System.out.println("PARTIDA NUEVA");
        }
        state = Integer.parseInt(stateVerification);
        // Se verifica si el State cargado por el TXT, no es nulo. Si este es nulo indica que el juego se cerro y se abrio de nuevo para crear el State, de lo contaro se carga normalmente con el reloadState.
        if (!gsm.VerificarReinicioJuego(state)) {
            gsm.reloadState(state);  // Se recarga el juego
            gsm.getGameStates()[state].getLoadData(); // Se insertan los datos del txt
        } else {
            gsm.setState(state); // Se crea el state donde termino el guardado, toca verificar a futuro, como enlazarlo con el acceso a superiores ( del 1 a 3 y a 2)
        }
    }

    
    public boolean noHovering(){
        if(exit.isHovering() || option.isHovering() || continu.isHovering() || newg.isHovering())
            return true;
        else
            return false;
                    
    }
    
    public void noMoreCurrently(UIImageButton u){
        for (UIObject object : uimanager.getObjects()) {
            if(object instanceof UIImageButton){
                UIImageButton ui= (UIImageButton) object;
                if(!u.equals(ui))
                    ui.setCurrent(false);
            }
        }
    }
    
    private void noMoreCurrently() {
        for (UIObject object : uimanager.getObjects()) {
            if(object instanceof UIImageButton){
                UIImageButton ui= (UIImageButton) object;
                ui.setCurrent(false);
            }
        }
    }
    
    @Override
    public void getLoadData() {
    }

    @Override
    public void insertData() {
    }

    
}
