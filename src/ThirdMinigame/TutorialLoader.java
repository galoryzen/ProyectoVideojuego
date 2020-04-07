package ThirdMinigame;

import MainG.Handler;
import Tilemaps.Assets;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class TutorialLoader{

    private Handler handler;
            
    private boolean tutorialMark = true;
    
    public static int tutorialState = 1; 
    public static int timeAnimation = 1;
    
    Font textFont = new Font("pixelart", Font.PLAIN, 13);
    
    public TutorialLoader(Handler handler){
        this.handler = handler;
    }
    
    
    
    public void draw(Graphics2D g) {
        timeAnimation++;
        g.setColor(Color.red);
        g.fillRect(0, 230, 1080, 180);
        g.setFont(textFont);
        g.drawImage(Assets.astronautTalker, -30, 230, 131, 110, null);
        g.setColor(Color.black);
        switch (tutorialState) {
            case 1: // Explicas el contexto
                g.drawString("Bienvenida Astronauta", 90, 250);
                g.drawString("Ahora mismo eres Katherine Johnson,estas a bordo del Apolo ", 90, 270);
                g.drawString("Los hombres a bordos del Apolo 11 se han enfermado", 90, 290);
                g.drawString("Entonces tu mision, es estar al mando del manejo de la nave y cambiar", 90, 310);
                g.drawString("la historia", 90, 330);
                if (timeAnimation >= 2500) {
                    tutorialState = 2;
                    timeAnimation = 0;
                }
                break;
            case 2:
                g.drawString("¿Como que no sabes quien eres?", 90, 260);
                g.drawString("Eres la mujer que mando al Apolo 11 a la Luna en el año 1969", 90, 280);
                g.drawString("Es tu momento de cambiar la historia, si ganas aqui, reescribes la historia", 90, 300);
                g.drawString("Ahora te explico que tienes que hacer Katherine", 90, 320);
                if (timeAnimation >= 2500) {
                    tutorialState = 3;
                    timeAnimation = 0;
                }
                break;
            case 3:
                g.drawString("Tu mision es llegar a la Luna, pero no la tienes facil,", 90, 260);
                g.drawString("Debes esquivar los asteroides y acabar con los Aliens", 90, 280);
                g.drawString("Si... es un secreto de Estado, pero si existen los Aliens", 90, 300);
                g.drawString("Ahora te enseño a tripular la nave", 90, 320);
                if (timeAnimation >= 2500) {
                    tutorialState = 4;
                    timeAnimation = 0;
                }
                break;
            case 4:
                g.drawString("Con las Teclas AWSD te encargas de dirigir la nave, intenta moverte", 90, 270);
                g.drawString("Yo te espero...", 90, 290);
                if (handler.getKeyManager().down == true || handler.getKeyManager().up == true
                        || handler.getKeyManager().right == true || handler.getKeyManager().left == true) {
                    tutorialState = 5;
                }
                break;
            case 5:
                g.drawString("Excelente Katherine, ahora te enseñaremos a usar el Rayo Laser", 90, 270);
                g.drawString("Simplemente presiona la Q, y ya dispara, y preparate para tu viaje", 90, 290);
                if (handler.getKeyManager().space == true) {
                    tutorialState = 6;
                }
                break;
            default:
                tutorialMark = false;
        }
    }
    
    public boolean getTutorialTerminator(){
        return tutorialMark;
    }
    
    public void setTutorialTerminator(){
        tutorialMark = true;
    }
}
