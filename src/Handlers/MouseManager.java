/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import UI.UIManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author German David
 */
public class MouseManager implements MouseListener, MouseMotionListener{

    private boolean leftPressed,rightPressed;
    private int mouseX,mouseY;
    private UIManager uiManager;
    
    public MouseManager() {
    }
    
    public void setUIManager(UIManager uiManager){
        this.uiManager=uiManager;
    }
    
    //Getters
    
    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }
        
    
    //Implemented methods
    @Override
    public void mouseClicked(MouseEvent me) {
        
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if(me.getButton()== MouseEvent.BUTTON1)
            leftPressed= true;
        else if(me.getButton()==MouseEvent.BUTTON3)
            rightPressed=true;
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        if(me.getButton()== MouseEvent.BUTTON1)
            leftPressed= false;
        else if(me.getButton()==MouseEvent.BUTTON3)
            rightPressed=false;
        
        //Pasar mouseEvents a los UI
        if(uiManager!= null){
            uiManager.onMouseRelease(me);
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {     
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void mouseDragged(MouseEvent me) {
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        mouseX=me.getX();
        mouseY=me.getY();
        
        if(uiManager != null)
            uiManager.onMouseMove(me);
    }
    
}
