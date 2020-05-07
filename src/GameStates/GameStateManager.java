package GameStates;

import FirstMinigame.Level1UpManager;
import MainG.Handler;
/**
 * La clase GameStateManager se encarga de la administracion de los niveles
 * Es el que dice en que nivel se encuentra actualmente, en cual estado.
 */
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

    public GameStateManager(Handler handler, GameCamara gameCamara) {
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
                gameStates[state] = new MenuState(this, this.handler);
                break;
            case MAINLEVELSTATE:
                gameStates[state] = new MainLevel(this, this.handler,"Level 1");
                break;
            case LEVEL1STATE:
                gameStates[state] = new Level1State(this, this.handler,"Level 2");
                break;
            case LEVEL2STATE:
                gameStates[state] = new Level2State(this, handler, "Level 3");
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

    public World getWorld() {
        switch (currentState) {
            case 1: {
                MainLevel state = (MainLevel) gameStates[currentState];
                return state.getWorld();
            }
            case 2: {
                Level1State state = (Level1State) gameStates[currentState];
                return state.getWorld();
            }
            default: {
                Level2State state = (Level2State) gameStates[currentState];
                return state.getWorld();
            }
        }
    }
}
