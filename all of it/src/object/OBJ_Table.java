package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import mainn.GamePanel;
import mainn.WeaponListPanel;
import weapon.WPN_DiamondSword;

public class OBJ_Table extends SuperObject{

    GamePanel gp;
    String loot;
    String dialouge;
    boolean opened = false;

    
    public OBJ_Table(GamePanel gp, String loot){

        name = "table";
        this.gp = gp;
        craftable = false;
        this.loot = loot;
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
            sb.append("You took a " + loot + " from drawer");

            sb.append("You obtain the " + loot);
            gp.player.objects.add(new OBJ_Compass());
            gp.ui.currentDialogue = sb.toString();
            opened = true;
        }
    }
}
