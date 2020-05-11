package SecondMinigame;

import GameStates.Level2State;
import GameStates.LevelUpManager;
import Tilemaps.Background;
import java.awt.Graphics2D;

public class Level2UpManager extends LevelUpManager {

    private boolean flag1 = false, flag2 = false, flag3 = false, endMinigame = false, oneTime = false;
    private int phase = 0;
    private Graphics2D g;
    private Level2State state;
    private HUD hud;
    private WorldSpace world;

    public Level2UpManager(Level2State state, HUD hud, WorldSpace world, DialogueLoader dialogueLoader) {
        this.state = state;
        this.hud = hud;
        this.world = world;
        this.dialogueLoader = dialogueLoader;
    }

    public void levelUpManager(int points, int health) {
        if (!endMinigame) {
            if (phase == 0) {
                world.setGenerateEnemys(false);
            }
            if (points < 10 && !dialogueLoader.getDialogueMark() && phase == 0) {
                phase = -1;
                moveFasterBackground(state.getBg());
            }
            if (points >= 10 && !flag1) {
                phase = 1;
                // Con esto da inicio a la generacion de los primeros enemigos
                dialogueLoader.setDialogueMark();
                moveFasterBackground(state.getBg());
                flag1 = !flag1;
                world.clearScreenEntities();
                world.setGenerateEnemys(false);
            } else if (points >= 30 && !flag2) {
                // Con esto se da inicio a la generacion de los segundos enemigos
                phase = 2;
                dialogueLoader.setDialogueMark();
                moveFasterBackground(state.getBg());
                flag2 = !flag2;
                world.clearScreenEntities();
                world.generateEnemys();
            } else if (points >= 50 && !flag3) {
                //Generacion del boss y solo quedan asteorides
                phase = 3;
                dialogueLoader.setDialogueMark();
                moveFasterBackground(state.getBg());
                flag3 = !flag3;
                world.clearScreenEntities();
                world.generateEnemys();
            }
        } else {
            finishLevel();
        }
    }

    public void update(int points, int health) {
        levelUpManager(points, health);
    }

    public void render() {
        if (dialogueLoader.getDialogueMark()) {
            dialogueLoader.render(this.g);
        } else {
            world.setGenerateEnemys(true);
        }
        world.render(this.g);
    }

    @Override
    public void changeMusic() {

    }

    @Override
    public void finishLevel() {
    }

    public void moveFasterBackground(Background bg) {
        bg.setVector(bg.getDx() - 2.0d, 0);
    }

    public void setLevel(Level2State state) {
        this.state = state;
    }

    public int getPhase() {
        return this.phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public void setGraphics(Graphics2D g) {
        this.g = g;
    }

    @Override
    public void levelUpManager() {

    }
}
