package Tilemaps;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * La clase ImageLoader es la encargada de cargar las imagenes al programa
 */
public class ImageLoader {
    
    /**
     * @param path Es el path hacia el archivo que se quiere cargar
     * @return Retorna la imagen
     * @throws ImageLoaderException si la imagen no se encuentra en el path definido.
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
