package UtilLoader;

/**
 *
 * @author Omen
 */

// Interfaz que se encarga de los procesos de Guardar y Cargar la partida
public interface SaveGame{
    
    // Cargar los datos del txt, cada State lo implementa a su manera
    public abstract void loadData();
    // Rellena los datos del txt, cada State lo implementa a su manera.
    public abstract void insertData();

}
