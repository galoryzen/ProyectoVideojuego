/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameStates;

import MainG.Handler;
import MainG.Window;
import Tilemaps.Assets;
import UI.ClickListener;
import UI.UIImageButton;
import UI.UIManager;
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
    UIManager manager;

    public PauseState(GameStateManager gsm, Handler handler) {
        super(gsm);
        this.gsm = gsm;
        this.handler = handler;
        manager= new UIManager(handler);
        manager.addUIObject(new UIImageButton(100f, 100f, 100, 100, Assets.Boss, new ClickListener() {
            @Override
            public void onClick() {
                gsm.reloadState(0);
            }
        }));
    }

    @Override
    public void init() {
        Window.mouse.setUIManager(manager);
        timePassed = System.currentTimeMillis();
    }

    @Override
    public void update() {
        System.out.println("Running");
        manager.tick();
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
        manager.render(g);
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
