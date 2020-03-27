package GameStates;

import Audio.AudioClip;
import Audio.AudioPlayer;
import Entities.Creatures.Player;
import Entities.EntityManager;
import Handlers.ThreadPool;
import Tilemaps.Background;
import java.awt.Graphics2D;
import Handlers.KeyManager;
import MainG.Handler;
import ThirdMinigame.World;
import Tilemaps.Assets;
import java.awt.image.BufferedImage;

public class Level3State extends GameState {

    private Background bg;
    private AudioPlayer bgMusicSpaceTalk;
    private Handler handler;
    private World world;

    public KeyManager teclas;
    Player nave;
    private EntityManager entityManager;

    ThreadPool pool;

    public Level3State(GameStateManager gsm, ThreadPool pool, Handler handler) {
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
        world = new World(entityManager,handler);
        init();
    }

    @Override
    public void init() {
        bgMusicSpaceTalk = new AudioPlayer(AudioClip.bgMusicTalk, -15);
        pool.runTask(bgMusicSpaceTalk);
    }

    @Override
    public void update() {
        musicControl();
        bg.update();
        world.update();
    }

    @Override
    public void draw(Graphics2D g) {
        // Dibuja el fondo
        bg.draw(g);

        // Crear Personaje
        world.render(g);
    }
    
    // Jorge 
    // Generar asteroides aleatorios en pantalla
    public void generarAsteroides() {

    }

    public void musicControl() {

    }
}
