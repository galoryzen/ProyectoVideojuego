package ThirdMinigame;

import GameStates.Level3State;
import GameStates.LevelUpManager;
import Tilemaps.Background;
import java.awt.Graphics2D;

public class Level3UpManager extends LevelUpManager {

    private boolean flag1 = false, flag2 = false, flag3 = false, endMinigame = false, oneTime = false;
    private int phase = 0;
    private Graphics2D g;
    private Level3State state;
    private HUD hud;
    private World world;

    public Level3UpManager(Level3State state, HUD hud, World world, DialogueLoader dialogueLoader) {
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
                moveFasterBackground(state.getBg());
                world.clearScreenEntities();
                dialogueLoader.setDialogueMark();
                flag3 = !flag3;
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

    public void setLevel(Level3State state) {
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
