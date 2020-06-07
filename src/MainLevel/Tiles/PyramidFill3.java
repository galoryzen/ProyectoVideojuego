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
public class PyramidFill3 extends TileMainLevel{

    public PyramidFill3(int id) {
        super(Assets.pyramidFill_3,id);
    }
    
    @Override
    public boolean isSolid(){
        return !isSolid;
    }
}