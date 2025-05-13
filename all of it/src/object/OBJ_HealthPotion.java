package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import mainn.GamePanel;

public class OBJ_HealthPotion extends SuperObject {

    boolean isUsed = false;
    GamePanel gp;
    String dialouge;

    public OBJ_HealthPotion(GamePanel gp){
        usable = true;
        this.gp = gp;
        
        name = "Health Potion";
        craftable = false;

        try {

            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/health_potion.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }

    public void interact(){
        if(gp.player.health <= 10){
            gp.player.health += 10;
            dialouge +=  "You used a health potion.\nYou restored 10 health!";
        }
        else{
            gp.player.health = 20;
            dialouge = "You used a health potion.\nYour health is maxed out!";
        }
        isUsed = true;
    }
}
