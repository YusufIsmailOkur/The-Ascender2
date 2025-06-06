package object;

import java.io.IOException;
import javax.imageio.ImageIO;
import mainn.GamePanel;
import weapon.WPN_DiamondSword;

public class OBJ_Chest extends SuperObject{

    GamePanel gp;
    String loot;
    String dialouge;
    boolean opened = false;

    
    public OBJ_Chest(GamePanel gp, String loot){

        name = "chest";
        this.gp = gp;
        craftable = false;
        this.loot = loot;
        collision = true;

        try {

            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/chest.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void interact(){
        gp.gameState = gp.dialogueState;
        if(!opened){
            StringBuilder sb = new StringBuilder();
            sb.append("You open the chest and find a " + loot + "!\n");

            if(loot.equalsIgnoreCase("diamond sword")){
                gp.player.weapons.add(new WPN_DiamondSword());
                sb.append("You obtain the " + loot + "!");
            }
            else if(loot.equalsIgnoreCase("screwdriver")){
                gp.player.objects.add(new OBJ_ScrewDriver());
                sb.append("You obtain the " + loot + "!");
            }
            else if(loot.equalsIgnoreCase("light")){
                gp.player.objects.add(new OBJ_Light());
                sb.append("You obtain the " + loot + "!");
            }
            else if(loot.equalsIgnoreCase("health potion")){
                gp.player.objects.add(new OBJ_HealthPotion(gp));
                sb.append("You obtain the " + loot + "!");
            }
            else{
                if(loot.equalsIgnoreCase("key")){
                    gp.player.objects.add(new OBJ_Key());
                    sb.append("You obtain the " + loot + "!");
                }
                else{
                    int arrowCount = Integer.parseInt(loot);
                    gp.player.bow.life += arrowCount;
                    sb.append("You obtained " + loot + " arrows!");
                }
            }
            try {

                image = ImageIO.read(getClass().getResourceAsStream("/res/objects/chest_opened.png"));
            } catch(IOException e){
                e.printStackTrace();
            }
            gp.ui.currentDialogue = sb.toString();
            opened = true;
        }
        else{
            gp.ui.currentDialogue = "It's empty!";
        }
    }
}
