package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import mainn.GamePanel;
import mainn.WeaponListPanel;
import weapon.WPN_DiamondSword;

public class OBJ_Statue1 extends SuperObject{

    GamePanel gp;
    String dialouge;
    
    public OBJ_Statue1(GamePanel gp){

        name = "statue1";
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
        gp.gameState = gp.dialogueState;
        StringBuilder sb = new StringBuilder();
        sb.append("statue 1 message");
        gp.ui.currentDialogue = sb.toString();
    }
}
