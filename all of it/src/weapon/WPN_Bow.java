package weapon;

import java.io.IOException;

import javax.imageio.ImageIO;

public class WPN_Bow extends SuperWeapon{
    

    public WPN_Bow() {

        name = "Bow";
        attackValue = 1;
        collision = true;
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 48;
        solidArea.height = 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        life = 20; // default arrow count

        try {
            imageRight = ImageIO.read(getClass().getResourceAsStream("/res/weapons/SwordRight.png")
            );
            imageLeft  = ImageIO.read(
                getClass().getResourceAsStream("/res/weapons/SwordLeft.png")
            );
            imageUp    = ImageIO.read(
                getClass().getResourceAsStream("/res/weapons/SwordUp.png")
            );
            imageDown  = ImageIO.read(
                getClass().getResourceAsStream("/res/weapons/SwordDown.png")
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }


}
