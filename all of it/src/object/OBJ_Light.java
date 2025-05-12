package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Light extends SuperObject{
    
    public OBJ_Light(){

        name = "light";
        craftable = true;

        try {

            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/light.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}

