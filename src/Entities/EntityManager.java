package Entities;

import Entities.Creatures.MainPlayer;
import Entities.Creatures.Player;
import Entities.Creatures.Player_Joan;
import GameStates.GameState;
import MainG.Handler;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author German David
 */
public class EntityManager {

    private Handler handler;
    private Player nave;
    private Player_Joan joan;
    private MainPlayer mainC;
    private ArrayList<Entity> entities;
    private int Score = 0;

    //Comparador
    private Comparator<Entity> renderSorter = new Comparator<Entity>() {
        public int compare(Entity a, Entity b) {
            if (a.getY() + a.getHeight() < b.getY() + b.height) {
                return -1;
            }
            return 1;
        }
    };
    
    /**
     * Constructor de la clase Entity Manager.
     * @param handler Handler del EntityManager.
     * @param state El State del Entity Manager
     */
    public EntityManager(Handler handler, GameState state) {
        this.handler = handler;
        entities = new ArrayList<Entity>();
        Entity player;
        if (state.getTag().equals("Level 3")) {
            nave = new Player(handler, this, 100, 100);
            player = nave;
        } else if (state.getTag().equals("Level 2")) {
            joan = new Player_Joan(handler, this, 100, 100);
            player = joan;
        } else {
            mainC = new MainPlayer(handler, this, 0, 450);
            player = mainC;
        }
        addEntity(player);
    }
    
    /**
     * Constructor que solo recibe Handler.
     * @param handler Handler del entity manager.
     */
    public EntityManager(Handler handler) {
        this.handler = handler;
        entities = new ArrayList<Entity>();
    }
    
    /**
     * Metodo que renderiza la entidad.
     * @param g graficos
     */
    public void render(Graphics2D g) {
        for (Entity e : entities) {
            e.render(g);
        }
    }
    
    /**
     * Metodo que actualiza todas las entidades del videojuego.
     */
    public void update() {
        for (int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
            e.update();
            if (!e.isActive() || e.getX() < -e.getWidth() - 20 || e.getX() > 1080 || e.getY() > 730 || e.getY() < -e.getHeight()) {
                System.out.println("" + e);
                entities.remove(e);
            }
        }
        entities.sort(renderSorter);
    }

    //GETTERS AND SETTERS
    public int getScore() {
        return Score;
    }

    public void setScore(int Score) {
        this.Score = Score + this.Score;
    }

    public void addEntity(Entity e) {
        entities.add(e);
    }

    public void removeEntity(Entity e) {
        entities.remove(e);
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Player getPlayer() {
        return nave;
    }

    public Player_Joan getJoan() {
        return joan;
    }

    public void setPlayer(Player player) {
        this.nave = player;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

}
