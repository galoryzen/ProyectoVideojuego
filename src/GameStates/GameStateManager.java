package GameStates;

import FirstMinigame.Level1UpManager;
import FirstMinigame.WorldGenerator.World;
import MainG.Handler;
import SecondMinigame.Level2UpManager;
import ThirdMinigame.Level3UpManager;
import java.util.concurrent.ThreadPoolExecutor;

public class GameStateManager {

    public ThreadPoolExecutor pool;
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

    public GameStateManager(ThreadPoolExecutor pool, Handler handler, GameCamara gameCamara) {
        this.pool = pool;
        this.handler = handler;
        handler.setGSM(this);
        gameStates = new GameState[NUMGAMESTATE];
        currentState = MENUSTATE;
        loadState(currentState);
        this.gameCamera = gameCamara;
    }

    public void setState(int state) {
        unloadState(currentState);
        currentState = state;
        loadState(currentState);
    }

    public void unloadState(int state) {
        gameStates[state] = null;
    }

    // Funcion encargada de cargar los State, controlador de niveles
    private void loadState(int state) {
        switch (state) {
            case MENUSTATE:
                gameStates[state] = new MenuState(this, this.pool, this.handler);
                break;
            case MAINLEVELSTATE:
                gameStates[state] = new MainLevel(this, this.pool, this.handler);
                break;
            case LEVEL1STATE:
                levelManager = new Level1UpManager();
                gameStates[state] = new Level1State(this, this.pool, this.handler, (Level1UpManager) levelManager);
                break;
            case LEVEL2STATE:
                levelManager = new Level2UpManager();
                gameStates[state] = new Level2State(this, this.pool, this.handler, (Level2UpManager) levelManager);
                break;
            case LEVEL3STATE:
                levelManager = new Level3UpManager();
                gameStates[state] = new Level3State(this, this.pool, this.handler, (Level3UpManager) levelManager);
                break;
        }
    }

    public void update() {
        gameStates[currentState].update();
    }

    public void draw(java.awt.Graphics2D g) {
        gameStates[currentState].draw(g);
    }

    public int inGameState() {
        return currentState;
    }
    
    public World getWorld(){
        Level1State level = (Level1State) gameStates[2]; 
        return level.getWorld();
    }

}
