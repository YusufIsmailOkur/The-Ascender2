package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_ClosedElevator extends SuperObject{
    
    public OBJ_ClosedElevator(){

        name = "closedelevator";
        craftable = false;

        try {

            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/elevator.png")); 
        } catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
