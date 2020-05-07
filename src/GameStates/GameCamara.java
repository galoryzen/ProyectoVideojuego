package GameStates;

import Entities.Entity;
import FirstMinigame.Tiles.Tile;
import FirstMinigame.WorldGenerator.WorldLibrary;

import MainG.Handler;

/**
 *
 * @author German David
 */

public class GameCamara {
    
    private Handler handler;
    
    //El desplazamiento adicional de la cámara
    private float xOffset,yOffset;
    
    public GameCamara(Handler handler,float xOffset,float yOffset){
        this.handler=handler;
        this.xOffset=xOffset;
        this.yOffset=yOffset;
    }
    
    //Para que solo se vea la parte del mapa con Tiles
    public void checkBlankSpace(){
        WorldLibrary world = (WorldLibrary) handler.getWorld();
        if(xOffset<0){
            xOffset=0;
         }else if(xOffset>world.getWidth()*Tile.TILEWIDTH - handler.getWidth()){
            xOffset=world.getWidth()*Tile.TILEWIDTH - handler.getWidth();
        }
        if(yOffset<0){
            yOffset=0;
        }else if(yOffset>world.getHeight()*Tile.TILEHEIGHT - handler.getHeight()){
            yOffset=world.getHeight()*Tile.TILEHEIGHT - handler.getHeight();
        }
        
    }
    
    //centrar la cámara en el personaje
    public void centerOnEntity(Entity e){
        xOffset=e.getX() - handler.getWidth()/2 + e.getWidth()/2;
        yOffset=e.getY() - handler.getHeight()/2+ e.getHeight()/2;
        checkBlankSpace();
    }
    
    //Mover la cámara
    public void move(float xAmt,float yAmt){
        xOffset+=xAmt;
        yOffset+=yAmt;
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
