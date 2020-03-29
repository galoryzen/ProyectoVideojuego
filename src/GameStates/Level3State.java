package GameStates;

import Audio.AudioLoader;
import Entities.Creatures.Player;
import Entities.EntityManager;
import Tilemaps.Background;
import java.awt.Graphics2D;
import Handlers.KeyManager;
import MainG.Handler;
import ThirdMinigame.TutorialLoader;
import ThirdMinigame.World;
import Tilemaps.Assets;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Level;
import java.util.logging.Logger;
import kuusisto.tinysound.Music;

public class Level3State extends GameState {

    private Music bgTalkMusic, bgMusic;
    private Background bg;
    private Handler handler;
    private World world;
    private TutorialLoader tutorialL;

    public KeyManager teclas;
    Player nave;
    private EntityManager entityManager;

    ThreadPoolExecutor pool;

    private boolean tutorial = true;
    private boolean ya = true;

    public Level3State(GameStateManager gsm, ThreadPoolExecutor pool, Handler handler) {
        super(gsm);
        this.pool = pool;
        this.handler = handler;
        try {
            bg = new Background(Assets.fondoSpaceInvaders, 1);
            bg.setVector(-3, 0);
        } catch (Exception e) {
            System.out.print(e);
        }
        entityManager = new EntityManager(handler, nave);
        world = new World(entityManager, handler);
        tutorialL = new TutorialLoader(handler);
        init();
    }

    @Override
    public void init() {
        bgTalkMusic = AudioLoader.bgTalkMomentSpaceInvaders;
        bgMusic = AudioLoader.bgMusicSpaceInvaders;
        bgTalkMusic.play(true);
    }

    @Override
    public void update() {
        musicControl();
        bg.update();
        world.update(tutorial);
    }

    @Override
    public void draw(Graphics2D g) {
        // Dibuja el fondo
        bg.draw(g);
        world.render(g);
        if (tutorialL.getTutorialTerminator() == true) {
            tutorialL.draw(g);
        } else {
            if (ya) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Level3State.class.getName()).log(Level.SEVERE, null, ex);
                }
                ya = !ya;
                bgTalkMusic.stop();
                bgMusic.setVolume(0.3);
                bgMusic.play(true);
                tutorial = tutorialL.getTutorialTerminator();
                bg.setVector(-6, 0);
            }
        }
    }

    public void musicControl() {

    }
}
