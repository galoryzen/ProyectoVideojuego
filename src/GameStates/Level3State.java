package GameStates;

import Tilemaps.Background;
import java.awt.Graphics2D;
import Handlers.detectorTeclas;
import Tilemaps.Assets;

public class Level3State extends GameState implements Mechanics{

    Background bg;
    public detectorTeclas teclas;
    
    public Level3State(GameStateManager gsm){
        super(gsm);
        try{
            bg = new Background(Assets.fondoSpaceInvaders,1);
        }catch(Exception e){
            System.out.print(e);
        }
    }
    
    @Override
    public void init() {

    }

    @Override
    public void update() {
        handleInput();
        
    }

    @Override
    public void draw(Graphics2D g) {
        // Dibuja el fondo
        bg.draw(g);
        
        // Cre
    }

    @Override
    // ALEX E ISAAC
    // Mecanicas de cada minijuego
    public void handleInput() {
        // Se mueve arriba
       if(teclas.UP.esPresionada){   
       
       }
       // Se mueve abajo
       if(teclas.DOWN.esPresionada){
       
       }
       // Se mueve a la derecha
       if(teclas.RIGHT.esPresionada){
       
       }
       // Se mueve a la izquierda
       if(teclas.LEFT.esPresionada){
       
       }
       // Dispara balas
       if(teclas.TEST.esPresionada){
       
       }
    }
    
    // Jorge 
    // Generar asteroides aleatorios en pantalla
    public void generarAsteroides(){
    
    }

    @Override
    public void detectarColisiones() {
    }
}
