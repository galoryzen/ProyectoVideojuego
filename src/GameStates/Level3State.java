package GameStates;

import Audio.AudioLoader;
import Entities.Creatures.Player;
import Entities.EntityManager;
import Tilemaps.Background;
import java.awt.Graphics2D;
import Handlers.KeyManager;
import MainG.Handler;
import ThirdMinigame.HUD;
import ThirdMinigame.Level3UpManager;
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
    
    private Level3UpManager levelManager;
    public KeyManager teclas;
    private Player nave;
    private EntityManager entityManager;

    ThreadPoolExecutor pool;

    private boolean tutorial = true;
    private boolean ya = true;

    public Level3State(GameStateManager gsm, ThreadPoolExecutor pool, Handler handler, Level3UpManager manager) {
        super(gsm);
        this.pool = pool;
        this.handler = handler;
        this.levelManager = manager;
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
        levelManager.setLevel(this);
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
        levelManager.levelUpManager(hud.getPoint(), hud.getHealth());
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
                bg.setVector(-5, 0);
            }
        }
    }

    @Override
    public void musicControl() {

    }

    public Background getBg() {
        return bg;
    }
}
