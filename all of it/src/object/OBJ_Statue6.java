package object;

import java.io.IOException;

import javax.imageio.ImageIO;
import mainn.GamePanel;

public class OBJ_Statue6 extends SuperObject{

    GamePanel gp;
    String dialouge;
    
    public OBJ_Statue6(GamePanel gp){

        name = "statue6";
        this.gp = gp;
        craftable = false;
        collision = true;

        try {

            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/statue (2).png"));
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void interact(){
        if(!OBJ_TestDoorClosed.isChanged()){
            
            gp.gameState = gp.dialogueState;
            StringBuilder sb = new StringBuilder();
            sb.append("lpnoao");
            gp.ui.currentDialogue = sb.toString();
        }
    }
}
