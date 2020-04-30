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

    private boolean ya = true;
    private float volume = 0.3f;

    public Level3State(GameStateManager gsm, Handler handler) {
        super(gsm);
        this.handler = handler;
        try {
            bg = new Background(Assets.fondoSpaceInvaders, 1);
            bg.setVector(-3f, 0f);
        } catch (Exception e) {
            System.out.print(e);
        }
        entityManager = new EntityManager(handler, nave);
        dialogueLoader = new DialogueLoader(handler);
        world = new World(entityManager, handler);
        hud = new HUD(entityManager);
        this.levelManager = new Level3UpManager(this, hud, world, dialogueLoader);
        world.setHUD(hud);
        world.setLevelUpManager(levelManager);
        bgTalkMusic = AudioLoader.bgTalkMomentSpaceInvaders;
        bgMusic = AudioLoader.bgMusicSpaceInvaders;
    }

    @Override
    public void init() {
        bgTalkMusic.play(true, 0.5f);
    }

    @Override
    public void update() {
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
        hud.render(g);
    }

    @Override
    public void musicControl() {
        if (levelManager.getPhase() == -1 && !bgMusic.playing()) {
            bgTalkMusic.stop();
            bgMusic.play(true, volume);
        }
    }

    public Background getBg() {
        return bg;
    }

}
