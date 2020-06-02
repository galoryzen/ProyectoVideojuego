/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainLevel.Tiles;

import Tilemaps.Animation;
import Tilemaps.Assets;
import java.awt.Graphics;

/**
 *
 * @author Omen
 */
public class PortalTile extends TileMainLevel {

    private Animation animationPortal = new Animation(150, Assets.teleporterTile);

    public PortalTile(int id) {
        super(Assets.teleporterTile[0], id);
    }

    @Override
    public void render(Graphics g, int x, int y) {
        g.drawImage(animationPortal.getCurrentFrame(), x - 40, y - 35, TILEWIDTH * 2, TILEHEIGHT * 2, null);
        animationPortal.update();
    }

    @Override
    public boolean isSolid() {
        return !isSolid;
    }

    @Override
    public boolean makeDamage() {
        return false;
    }

}
