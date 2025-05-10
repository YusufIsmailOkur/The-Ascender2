package weapon;

import java.io.IOException;
import javax.imageio.ImageIO;

public class WPN_Sword extends SuperWeapon {

    public WPN_Sword() {

        name = "Sword";
        attackValue = 2;
        collision = true;
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 48;
        solidArea.height = 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        life = Integer.MAX_VALUE;

        try {
            imageRight = ImageIO.read(getClass().getResourceAsStream("/res/weapons/SwordRight.png")
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}