package ThirdMinigame;

import java.awt.image.BufferedImage;

public abstract class Character {
    
    private BufferedImage sprite;
    private int height;
    private int width;
    private boolean isVisible;
    private float x;
    private float y;
    
    public Character(BufferedImage sprite, int height, int width, boolean isVisible, float x, float y){
        this.sprite = sprite;
        this.height = height;
        this.width = width;
        this.isVisible = isVisible;
        this.x = x;
        this.y = y;
    }
    
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

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
