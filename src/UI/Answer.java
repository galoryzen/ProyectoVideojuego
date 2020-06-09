/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Tilemaps.Assets;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Clase de las respuestas del quiz
 */
public class Answer extends UIObject{

    boolean correct;
    String information;
    private ClickListener clicker;
    
    /**
     * Constructor de answer
     * @param correct Es la respuesta correcta o no.
     * @param information String de lo que va a decir.
     * @param x Coordenada en X.
     * @param y Coordenada en Y.
     * @param width Ancho.
     * @param height Altura.
     * @param clicker ClickListener.
     */
    public Answer(boolean correct, String information, float x, float y, int width, int height,ClickListener clicker) {
        super(x, y, width, height);
        this.correct=correct;
        this.information = information;
        this.clicker = clicker;
        
    }

    @Override
    public void tick() {
        
    }
    /**
     * Meteodo para reenderizar las respuestas.
     * @param g Graphics2D.
     */
    @Override
    public void render(Graphics g) {
        if(hovering){
            g.drawImage(Assets.AnswerHover, (int) x, (int) (y),width,height, null);
            g.setColor(Color.RED);
        }else{
            g.drawImage(Assets.Answer, (int) x, (int) (y),width,height, null);
            g.setColor(Color.BLACK);
        }
        g.drawString(information,(int) x,(int) y);
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }
    
    public boolean isCorrect(){
        return correct;
    }
    
    public void setNewInformation(String s){
        information=s;
    }

    public void setNewCorrect(Boolean b){
        correct=b;
    }
}
