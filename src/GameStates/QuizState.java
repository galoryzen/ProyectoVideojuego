/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameStates;

import MainG.Window;
import Tilemaps.Assets;
import UI.Answer;
import UI.ClickListener;
import UI.UIManager;
import UI.UIObject;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author German David
 */
public class QuizState extends GameState{
    UIManager manager;
    Answer a,b,c,d;
    Answer ans;
    boolean answered;
    int i;
    long wait=5000,lastTime=0,actual=0;
    
    public QuizState(GameStateManager gsm) {
        super(gsm);
        i=1;
        answered= false;
        manager= new UIManager(handler);
        Window.mouse.setUIManager(manager);
         a= new Answer(true,"Correcta",500,300,100,100,new ClickListener() {
                @Override
                public void onClick() {
                    if(a.isCorrect()){
                        System.out.println("True");
                    }else{
                        System.out.println("False");
                    }
                    answered=true;
                    ans=a;
                }
            });
                    manager.addUIObject(a);
                    
                    b= new Answer(false,"Falsa",500,410,100,100,new ClickListener() {
                @Override
                public void onClick() {
                    if(b.isCorrect()){
                        System.out.println("True");
                    }else{
                        System.out.println("False");
                    }
                   answered=true;
                   ans=b;
                }
            });
                    manager.addUIObject(b);
                    c= new Answer(false,"Falsa",500,510,100,100,new ClickListener() {
                @Override
                public void onClick() {
                    if(c.isCorrect()){
                        System.out.println("True");
                    }else{
                        System.out.println("False");
                    }
                        ans=c;
                        answered=true;
                }
            });
                    manager.addUIObject(c);
                    d= new Answer(false,"Falsa",500,610,100,100,new ClickListener() {
                @Override
                public void onClick() {
                    if(d.isCorrect()){
                        System.out.println("True");
                    }else{
                        System.out.println("False");
                    }
                    ans=d;
                   answered=true;
                }
            });
                    manager.addUIObject(d);
               
                    
    }
    
    
    
    
    @Override
    public void init() {
    }

    @Override
    public void update() {
            if(!answered){
                actual=System.currentTimeMillis();
                lastTime=0;
            switch(i){
                
                case 1:
                    
                   break;
                case 2:
                    a.setNewInformation("Level 2");
                    a.setNewCorrect(false);
                    b.setNewCorrect(true);
                    b.setNewInformation("Correcta");
                    break;
                case 3:
                    a.setNewInformation("Level 3");
                    b.setNewCorrect(false);
                    b.setNewInformation("falsa");
                    c.setNewCorrect(true);
                    c.setNewInformation("Correcta");
                    break;
                case 4:
                    a.setNewInformation("Level 4");
                    c.setNewCorrect(false);
                    c.setNewInformation("Falsa");
                    b.setNewCorrect(true);
                    b.setNewInformation("Correcta");
                    break;
                case 5:
                    a.setNewInformation("Level 2");
                    b.setNewCorrect(false);
                    b.setNewInformation("Falsa");
                    d.setNewCorrect(true);
                    d.setNewInformation("Correcta");
                    break;
                default:
                    break;
            }
            getInput();
            }else{
                lastTime+=System.currentTimeMillis()-actual;
                actual=System.currentTimeMillis();
                
                if(lastTime>=wait){
                    i++;
                    answered=false;
                }
                
            }
            
            manager.tick();
        }
    
    public void getInput(){
        if(Window.keyManager.a){
            a.onClick();
        }else if(Window.keyManager.b){
            b.onClick();
        }else if( Window.keyManager.c){
            c.onClick();
        }else if(Window.keyManager.d){
            d.onClick();
        }
    }
    
    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.gray);
        g.fillRect(0, 0, 1080, 720);
        
     if(!answered){  
         
        manager.render(g);
        //Diferentes preguntas
        switch(i){
            case 1:
                g.drawImage(Assets.enemy, 400,20 , null);
                break;
            case 2:
                g.drawImage(Assets.floor, 400,20 , null);
                break;
            case 3:
                g.drawImage(Assets.CursorSpace, 400,20 , null);
                break;
            case 4:
                g.drawImage(Assets.LaserAlien, 400,20 , null);
                break;
            case 5:
                g.drawImage(Assets.astronautTalker, 400,20 , null);
                break;
            default:
                
                break;
        }
           
    }else{
            g.setColor(Color.WHITE);
            g.fillRect(100, 100, 100, 100);
            g.setColor(Color.BLACK);
            g.drawString(""+ans.isCorrect(), 100, 100);
        }
    }

    @Override
    public void musicControl() {
        
    }

    @Override
    public World getWorld() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getInsertData() {
        
    }

    @Override
    public void getLoadData() {
        
    }


    
}
