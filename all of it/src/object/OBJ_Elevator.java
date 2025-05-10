package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Elevator extends SuperObject{
    
    public OBJ_Elevator(){

        name = "elevator";
        craftable = false;

        try {

            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/elevator.png")); 
        } catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
