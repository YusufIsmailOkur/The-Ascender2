package object;

import java.io.IOException;

import javax.imageio.ImageIO;
import mainn.GamePanel;

public class OBJ_Statue7 extends SuperObject{

    GamePanel gp;
    String dialouge;
    
    public OBJ_Statue7(GamePanel gp){

        name = "statue7";
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
            sb.append("zljvuk");
            gp.ui.currentDialogue = sb.toString();
        }
        else{
            gp.gameState = gp.dialogueState;
            StringBuilder sb = new StringBuilder();
            sb.append("ahg ljo tbhu ljou ubeosbr");
            gp.ui.currentDialogue = sb.toString();

        }
    }
}
