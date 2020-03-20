package ThirdMinigame;

import java.awt.image.BufferedImage;

public abstract class Character {
    
    private BufferedImage sprite;
    private int height;
    private int width;
    private boolean isVisible;

    public BufferedImage getSprite() {
        return sprite;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean isIsVisible() {
        return isVisible;
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

}
