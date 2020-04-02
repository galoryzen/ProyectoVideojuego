package GameStates;

import Audio.AudioLoader;
import Entities.Creatures.Player;
import Entities.EntityManager;
import Tilemaps.Background;
import java.awt.Graphics2D;
import Handlers.KeyManager;
import MainG.Handler;
import ThirdMinigame.HUD;
import ThirdMinigame.TutorialLoader;
import ThirdMinigame.World;
import Tilemaps.Assets;
import java.util.concurrent.ThreadPoolExecutor;
import kuusisto.tinysound.Music;

public class Level3State extends GameState {

    private Music bgTalkMusic, bgMusic;
    private Background bg;
    private Handler handler;
    private World world;
    private TutorialLoader tutorialL;
    private HUD hud;

    public KeyManager teclas;
    private Player nave;
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
        hud = new HUD(entityManager);
        world.setHUD(hud);
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
        hud.update();
    }

    @Override
    public void draw(Graphics2D g) {
        // Dibuja el fondo
        bg.draw(g);
        world.render(g);
        hud.render(g);
        if (handler.getKeyManager().test) {
            tutorial = false;
        }
        if (tutorialL.getTutorialTerminator()) {
            tutorialL.draw(g);
        } else {
            if (ya) {
                ya = !ya;
                bgTalkMusic.stop();
                bgMusic.setVolume(0.3);
                bgMusic.play(true);
                tutorial = false;
                bg.setVector(-12, 0);
            }
        }
    }

    public void musicControl() {

    }
}
