package object;

import java.io.IOException;

import javax.imageio.ImageIO;
import mainn.GamePanel;

public class OBJ_Table extends SuperObject{

    GamePanel gp;
    String dialouge;
    boolean opened = false;

    
    public OBJ_Table(GamePanel gp){

        name = "table";
        this.gp = gp;
        craftable = false;
        collision = true;

        try {

            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/table_ground4.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void interact(){
        gp.gameState = gp.dialogueState;
        if(!opened){
            StringBuilder sb = new StringBuilder();
            sb.append("You took a photo and a compass from drawer.\n");

            sb.append("You obtain the photo.\n");
            sb.append("You obtain the compass.");

            gp.player.objects.add(new OBJ_Photo());
            gp.player.objects.add(new OBJ_Compass());
            gp.ui.currentDialogue = sb.toString();
            opened = true;
        }
    }
}
