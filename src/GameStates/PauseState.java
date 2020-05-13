/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameStates;

import MainG.Handler;
import MainG.Window;
import UtilLoader.SaveGame;
import java.awt.Graphics2D;

/**
 *
 * @author Omen
 */
public class PauseState extends GameState implements SaveGame {

    private GameStateManager gsm;
    private Handler handler;
    private long timePassed, timeDeltaTime;

    public PauseState(GameStateManager gsm, Handler handler) {
        super(gsm);
        this.gsm = gsm;
        this.handler = handler;
    }

    @Override
    public void init() {
        timePassed = System.currentTimeMillis();
    }

    @Override
    public void update() {
        System.out.println("Running");
        timeDeltaTime = System.currentTimeMillis() - timePassed;
        if (Window.keyManager.pause && timeDeltaTime > 1000) {
            gsm.reloadState(3);
            System.out.println("Reanuda");
        }
        if (Window.keyManager.down) {
            insertData();
        }
    }

    @Override
    public void draw(Graphics2D g) {

    }

    @Override
    public void musicControl() {

    }

    @Override
    public World getWorld() {
        return null;
    }

    @Override

    public void loadData() {

    }

    @Override
    public void insertData() {
        gsm.getGameStates()[gsm.getPreviousState()].getInsertData();
        gsm.setState(0);
    }

    @Override
    public void getInsertData() {
        return;
    }

    @Override
    public void getLoadData() {
    }
}
