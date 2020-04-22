package ThirdMinigame;

import Tilemaps.Assets;
import java.awt.Graphics2D;
import Entities.EntityManager;
import java.awt.Color;

public class HUD {

    private static int points = 0;
    private static int health;
    private EntityManager manager;

    public HUD(EntityManager manager) {
        this.manager = manager;
    }

    public void render(Graphics2D g) {
        g.drawImage(Assets.asteroids, 10, 10, 46, 35, null);
        switch (health) {
            case 15:
                g.drawImage(Assets.vida, 450, 10, 38, 45, null);
                g.drawImage(Assets.vida, 420, 10, 38, 45, null);
                g.drawImage(Assets.vida, 390, 10, 38, 45, null);
                break;
            case 10:
                g.drawImage(Assets.vida, 420, 10, 38, 45, null);
                g.drawImage(Assets.vida, 390, 10, 38, 45, null);
                break;
            case 5:
                g.drawImage(Assets.vida, 390, 10, 38, 45, null);
                break;
            default:
                break;
        }
        g.setColor(Color.yellow);
        g.drawString(Integer.toString(points), 65, 30);

    }

    public static int getHealth() {
        return health;
    }

    public void update() {
        health = manager.getPlayer().getHealth();
        points = getPoint();
    }

    public int getPoint() {
        return points;
    }

    public void setPoint(int point) {
        this.points = point;
    }
}