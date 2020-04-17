/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Static;

import Entities.Entity;
import Entities.EntityManager;
import MainG.Handler;
import java.awt.Graphics;

/**
 *
 * @author German David
 */
public abstract class  StaticEntity extends Entity{
    public StaticEntity(Handler handler,EntityManager entityM, float x,float y, int width,int height){
        super(handler,entityM,x,y,width,height);
    }

   
}
