package Entities;

import Entities.Creatures.Player;
import Entities.Creatures.Player_Joan;
import MainG.Handler;
import java.awt.Graphics;
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

    public EntityManager(Handler handler, Player player) {
        this.handler = handler;
        nave = new Player(handler, this, 100, 100);
        entities = new ArrayList<Entity>();
        addEntity(nave);
    }
        
    public EntityManager(Handler handler, Player_Joan player) {
        this.handler = handler;
        this.joan = player;
        entities = new ArrayList<>();
        addEntity(joan);
    }
    

    public void render(Graphics g) {
        for (Entity e : entities) {
            e.render(g);
        }
    }

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

    //GETTERS AND SETTERS
    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Player getPlayer() {
        return nave;
    }
    
    public Player_Joan getJoan(){
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
