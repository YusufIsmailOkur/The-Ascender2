package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Key extends SuperObject{
    
    public OBJ_Key(){

        name = "key";
        craftable = false;

        try {

            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/key.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
