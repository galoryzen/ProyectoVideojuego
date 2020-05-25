/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainLevel.Tiles;

import Tilemaps.Assets;

/**
 *
 * @author Omen
 */
public class LapTile extends TileMainLevel{
    
    public LapTile(int id){
        super(Assets.empty, id);
    }
    
    @Override
    public boolean isSolid() {
        return !isSolid;
    }
    
    @Override
    public boolean isVisible(){
        return !isVisible;
    }
}
