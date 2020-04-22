/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FirstMinigame.Tiles;

import Tilemaps.Assets;
import java.awt.image.BufferedImage;

/**
 *
 * @author German David
 */
public class LibraryTile extends Tile{

    public LibraryTile( int id) {
        super(Assets.library, id);
    }
    
    @Override
    public boolean isSolid(){
        return true;
    }
}
