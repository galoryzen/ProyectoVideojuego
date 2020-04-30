package GameStates;

import FirstMinigame.Level1UpManager;
import FirstMinigame.WorldGenerator.World;
import MainG.Handler;
import SecondMinigame.Level2UpManager;

public class GameStateManager {

    public Handler handler;

    public static final int NUMGAMESTATE = 5;
    private static GameState[] gameStates;
    private int currentState;
    private LevelUpManager levelManager;
    private static GameCamara gameCamera;
    private World world;

    private final int MENUSTATE = 0;
    private final int MAINLEVELSTATE = 1;
    private final int LEVEL1STATE = 2;
    private final int LEVEL2STATE = 3;
    private final int LEVEL3STATE = 4;
    private screenLoading screenLoading = new screenLoading();
    private boolean carga = false;

    private Thread hiloCarga;

    public GameStateManager(Handler handler, GameCamara gameCamara) {
        this.handler = handler;
        handler.setGSM(this);
        gameStates = new GameState[NUMGAMESTATE];
        currentState = MENUSTATE;
        loadState(currentState);
        hiloCarga = new Thread(screenLoading, "Hilo Carga");
        this.gameCamera = gameCamara;
    }

    public void setState(int state) {
        unloadState(currentState);
        currentState = state;
        hiloCarga.start();
        carga = true;
        loadState(currentState);
    }

    public void unloadState(int state) {
        gameStates[state] = null;
    }

    // Funcion encargada de cargar los State, controlador de niveles
    private void loadState(int state) {
        switch (state) {
            case MENUSTATE:
                gameStates[state] = new MenuState(this, this.handler);
                break;
            case MAINLEVELSTATE:
                gameStates[state] = new MainLevel(this, this.handler);
                break;
            case LEVEL1STATE:
                gameStates[state] = new Level1State(this, this.handler, (Level1UpManager) levelManager);
                break;
            case LEVEL2STATE:
                levelManager = new Level2UpManager();
                gameStates[state] = new Level2State(this, this.handler, (Level2UpManager) levelManager);
                break;
            case LEVEL3STATE:
                gameStates[state] = new Level3State(this, handler);
        }
    }
    
    public void update() {
        if(carga == false) {
            gameStates[currentState].update();
        }
    }

    public void draw(java.awt.Graphics2D g) {
        gameStates[currentState].draw(g);
        if (carga == true) {
            while (screenLoading.isFinished()) {
                System.out.println("CARGANDO");
            }
            gameStates[currentState].init();
            carga = false;
        }
    }

    public int inGameState() {
        return currentState;
    }

    public World getWorld() {
        Level1State level = (Level1State) gameStates[2];
        return level.getWorld();
    }
}
