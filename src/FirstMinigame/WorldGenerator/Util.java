package FirstMinigame.WorldGenerator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author German David
 */

public class Util {
    
    public static String loadFileAsString(String path){
        
        StringBuilder builder = new StringBuilder();
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            //current line
            String line;
            //while the txt isnt over
            while((line = br.readLine())!= null)
                
                builder.append(line+"\n");
            //close the file
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
    
    public static int parseInt(String number){
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
