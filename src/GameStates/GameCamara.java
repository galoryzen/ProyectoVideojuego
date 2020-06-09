package GameStates;

import Entities.Creatures.MainPlayer;
import Entities.Entity;
import Tilemaps.Tile;
import FirstMinigame.WorldGenerator.WorldLibrary;

import MainG.Handler;
import MainLevel.Tiles.TileMainLevel;
import MainLevel.WorldGenerator.WorldPlat;

/**
 * Clase de la camara del LibraryGame.
 * @author German David
 */
public class GameCamara {

    private Handler handler;

    //El desplazamiento adicional de la cámara
    private float xOffset, yOffset;
    
    /**
     * Constructor de la camara.
     * @param handler Handler.
     * @param xOffset El offset en X.
     * @param yOffset El offset en Y.
     */
    public GameCamara(Handler handler, float xOffset, float yOffset) {
        this.handler = handler;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    /**
     * Metodo para que solo se muestre la parte del mapa con Tiles.
     * @param e La entidad que va a revisar.
     */
    public void checkBlankSpace(Entity e) {
        if(GameStateManager.currentState==2){
            WorldLibrary world;
            int tileH, tileW;
            world = (WorldLibrary) handler.getWorld();
            tileW = Tile.TILEWIDTH;
            tileH = Tile.TILEHEIGHT;
            if (xOffset < 0) {
                xOffset = 0;
            } else if (xOffset > world.getWidth() * tileW - handler.getWidth()) {
                xOffset = world.getWidth() * tileW - handler.getWidth();
            }
            if (yOffset < 0) {
                yOffset = 0;
            } else if (yOffset > world.getHeight() * tileH - handler.getHeight()) {
                yOffset = world.getHeight() * tileH - handler.getHeight();
            }
        }
    }

    /**
     * Centra la camara en la entidad..
     * @param e Entidad en la que se va a centrar.
     */
    public void centerOnEntity(Entity e) {
        xOffset = e.getX() - handler.getWidth() / 2 + e.getWidth() / 2;
        yOffset = e.getY() - handler.getHeight() / 2 + e.getHeight() / 2;
        checkBlankSpace(e);
    }

    /**
     * Movimiento de la camara.
     * @param xAmt Valor de lo que se mueve en X.
     * @param yAmt Valor de lo que se mueve en Y.
     */
    public void move(float xAmt, float yAmt) {
        xOffset += xAmt;
        yOffset += yAmt;
    }

//Getters y setters   
    public float getxOffset() {
        return xOffset;
    }

    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }

    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }

}
