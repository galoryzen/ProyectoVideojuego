package MainLevel;

import java.awt.image.BufferedImage;

public class Character {

    public BufferedImage Sprite;
    public int pos_x;
    public int pos_y;

    public Character(BufferedImage Spirte, int pos_x, int pos_y) {
        this.Sprite = Sprite;
        this.pos_x = pos_x;
        this.pos_y = pos_y;
    }
}
