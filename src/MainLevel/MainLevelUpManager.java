package MainLevel;

import Entities.Creatures.MainPlayer;
import Entities.EntityManager;
import GameStates.LevelUpManager;
import GameStates.MainLevel;
import UtilLoader.SwingWorkerMusic;
import GameStates.World;
import MainLevel.WorldGenerator.WorldPlat;
import UtilLoader.SaveGame;
import tinysound.Music;

/**
 *
 * @author Omen
 */
public class MainLevelUpManager extends LevelUpManager implements SaveGame {

    private Music[] musicPlaylist;
    private int random = 0;
    private boolean endMinigame;
    private final int AMUONTWORLDS = 10;
    private int currentWorld = 1;
    private int buttons;
    private int activeButtons;
    private MainPlayer player;
    private int currentButtonsPressed;
    private boolean flag1 = false, flag2 = false, flag3 = false, flag4 = false, flag5 = false, flag6 = false, flag7 = false, flag8 = false;

    private MainLevel state;

    public MainLevelUpManager(World world, EntityManager entityManager, MainLevel state) {
        super(world, entityManager);
        player = this.entityManager.getMainPlayer();
        endMinigame = false;
        this.state = state;
    }

    @Override
    public void levelUpManager() {
        changeMusic();
        WorldPlat worldAux = (WorldPlat) world.cast(this);
        int[] position = new int[2];
        if (!endMinigame) {
            switch (currentWorld) {
                case 1:
                    if (!flag1) {
                        buttons = 1;
                        player.setMaxReturns(1);
                        player.setAmountOfReturns(0);
                        flag1 = true;
                    }
                    switchLevels(1,worldAux);
                    if (player.isReset()) {
                        currentButtonsPressed = 0;
//                        worldAux.resetBooleans();
                        flag1 = false;
                        position = worldAux.getPositionMap();
                        player.setPosition(position);
                        player.setReset(false);
                    }
                    break;
                case 2:
                    if (!flag2) {
                        buttons = 1;
                        player.setMaxReturns(1);
                        player.setAmountOfReturns(0);
                        flag2 = true;
                    }
                    switchLevels(4,worldAux);
                    if (player.isReset()) {
                        currentButtonsPressed = 0;
//                        worldAux.resetBooleans();
                        flag2 = false;
                        position = worldAux.getPositionMap();
                        player.setPosition(position);
                        player.setReset(false);
                    }
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
            }
        } else {
            finishLevel();
        }
    }

    @Override
    public void changeMusic() {
        if (musicPlaylist[random].playing()) {
            //System.out.println("No generes random");
        } else {
            random = (int) (Math.random() * 4);
            SwingWorkerMusic swingWM = new SwingWorkerMusic(musicPlaylist[random]);
            swingWM.execute();
        }
    }

    @Override
    public void finishLevel() {

    }

    @Override
    public void loadData() {

    }

    @Override
    public void insertData() {

    }

    public void setMusic(Music[] musicPlayList) {
        this.musicPlaylist = musicPlayList;
    }

    public void switchLevels(int maxButtons, WorldPlat world) {
        int[] position = new int[2];
        if (player.isButtonPressed() && currentButtonsPressed != maxButtons) {
            currentButtonsPressed++;
            player.setButtonPressed();
            world.switchElevators();
        }
        if (player.isTouchingLap()) {
            currentWorld++;
            world.changeMap(state.pathString(currentWorld));
            world.restoreElevators();
            position = world.getPositionMap();
            player.setPosition(position);
            currentButtonsPressed = 0;
        }
    }
}
