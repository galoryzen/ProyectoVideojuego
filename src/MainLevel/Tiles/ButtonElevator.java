/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainLevel.Tiles;

/**
 *
 * @author Omen
 */
public class ButtonElevator extends ElevatorTile {

    public ButtonElevator(int id) {
        super(id);
        isSolid = false;
        isVisible = true;
    }

    @Override
    public boolean isVisible() {
        return !isVisible;
    }

    @Override
    public boolean isSolid() {
        return isSolid;
    }

    public void reset() {
        isSolid = false;
        isVisible = true;
    }

}
