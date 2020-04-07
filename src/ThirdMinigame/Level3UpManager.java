package ThirdMinigame;

import GameStates.Level3State;
import GameStates.LevelUpManager;
import Tilemaps.Background;

public class Level3UpManager extends LevelUpManager {

    private Level3State state;
    private boolean flag1 = false, flag2 = false, flag3 = false, endMinigame = false;

    public void levelUpManager() {

    }

    public void levelUpManager(int points, int health) {
        if (!endMinigame) {
            if (points >= 5 && !flag1) {
                // Con esto da inicio a la generacion de los primeros enemigos
                System.out.println("LEVEL UP");
                moveFasterBackground(state.getBg());
                flag1 = !flag1;
            } else if (points >= 30 && !flag2) {
                // Con esto se da inicio a la generacion de los segundos enemigos
                System.out.println("LEVEL UP 2");
                moveFasterBackground(state.getBg());
                flag2 = !flag2;
            } else if (points >= 50 && !flag3) {
                //Generacion del boss y solo quedan asteorides
                moveFasterBackground(state.getBg());
                flag3 = !flag3;
            }
        }

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
}
