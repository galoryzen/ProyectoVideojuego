package Tilemaps;

import MainG.GamePanel;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Background {

    private BufferedImage image;

    private double x;
    private double y;
    private double dx;
    private double dy;

    private double moveScale;

    public Background(BufferedImage image, double ms) {
        this.image = image;
        moveScale = ms;
    }

    public void setPosition(double x, double y) {
        this.x = x * moveScale % 540;
        this.y = y * moveScale % 360;
    }

    public void setVector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void update() {
        x += dx;
        y += dy;
    }

    public void draw(Graphics2D g) {
        g.drawImage(image, (int) x, (int) y, null);
        if (x < 0) {
            g.drawImage(image, (int) x + 540, (int) y, null);
        } else if (x > 0) {
            g.drawImage(image,(int) x - 540, (int) y, null);

        } else if (y > 0) {
            g.drawImage(image, (int) x, (int) y - 360, null);

        } else if (y < 0) {
            g.drawImage(image, (int) x, (int) y + 360, null);
        }
        if(x <= -540 || x >= 540){
            x = x * moveScale % 540;
        }
        if(y <= -360 || y >= 360){
            y = y * moveScale % 360;
        }
    }

}
