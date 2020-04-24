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
import ThirdMinigame.DialogueLoader;
import ThirdMinigame.World;
import Tilemaps.Assets;
import java.awt.MouseInfo;
import java.util.concurrent.ThreadPoolExecutor;
import tinysound.Music;

public class Level3State extends GameState {

    private Music bgTalkMusic, bgMusic;
    private Background bg;
    private Handler handler;
    private World world;
    private DialogueLoader dialogueLoader;
    private HUD hud;

    private Level3UpManager levelManager;
    public KeyManager teclas;
    private Player nave;
    private EntityManager entityManager;

    ThreadPoolExecutor pool;

    private volatile boolean tutorial = true;
    private boolean ya = true;
    private float volume = 0.3f;

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
        dialogueLoader = new DialogueLoader(handler);
        hud = new HUD(entityManager);
        world = new World(entityManager, handler, levelManager);
        world.setHUD(hud);
        levelManager.setLevel(this);
        levelManager.setWorld(world);
        levelManager.setDialogueLoader(dialogueLoader);
        levelManager.setPool(pool);
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
        if (handler.getKeyManager().test) {
            hud.setPoint(10);
        }
        musicControl();
        bg.update();
        hud.update();
        world.update();
        levelManager.update(hud.getPoint(), hud.getHealth());
        if (handler.getGame().getKeyManager().enter) {
            System.out.println(MouseInfo.getPointerInfo().getLocation());
        }
    }

    @Override
    public void draw(Graphics2D g) {
        // Dibuja el fondo
        if (ya) {
            levelManager.setGraphics(g);
            ya = !ya;
        }
        bg.draw(g);
        levelManager.render();
        if (handler.getKeyManager().test) {
            tutorial = false;
        }
        hud.render(g);
    }

    @Override
    public void musicControl() {
        if (tutorial == false) {
            bgTalkMusic.stop();
            if (bgMusic.done()) {
                bgMusic.play(true, volume);
            }
        }
    }

    public Background getBg() {
        return bg;
    }
}
