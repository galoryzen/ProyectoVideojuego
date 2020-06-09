package Tilemaps;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * La clase ImageLoader es la encargada de cargar las imagenes al programa
 */
public class ImageLoader {
    
    /**
     * Carga la imagen dado un path.
     * @param path Path de la imagen.
     * @return La imagen.
     */
    public static BufferedImage loadImage(String path){
        try{
            return ImageIO.read(ImageLoader.class.getResource(path));
        }catch(IOException e){
            System.out.println(e);
            System.exit(1);
        }
        return null;
    }
}
