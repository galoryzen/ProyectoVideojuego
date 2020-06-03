/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainLevel.Tiles;

import Tilemaps.Assets;
import java.awt.image.BufferedImage;

/**
 *
 * @author Omen
 */
public class FloatingTile extends TileMainLevel{

    public FloatingTile(int id) {
        super(Assets.platTiles, id);
    }
    
    @Override
    public boolean isSolid(){
        return !isSolid;
    }
      
}
