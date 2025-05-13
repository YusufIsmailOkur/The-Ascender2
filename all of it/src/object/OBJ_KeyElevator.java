package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_KeyElevator extends SuperObject{
    
    public OBJ_KeyElevator(){

        name = "keyelevator";
        craftable = false;

        try {

            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/elevator.png")); 
        } catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
