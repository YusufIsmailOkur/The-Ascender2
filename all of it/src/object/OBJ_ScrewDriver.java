package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_ScrewDriver extends SuperObject{
    
    public OBJ_ScrewDriver(){

        name = "screwdriver";
        craftable = true;

        try {

            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/screwdriver.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}

