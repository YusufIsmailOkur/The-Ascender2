package weapon;

import java.io.IOException;
import javax.imageio.ImageIO;

public class WPN_DiamondSword extends SuperWeapon {

    public WPN_DiamondSword() {

        name = "Diamond Sword";
        attackValue = 6;
        collision = true;
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 48;
        solidArea.height = 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        life = Integer.MAX_VALUE;

        try {
            imageRight = ImageIO.read(getClass().getResourceAsStream("/res/weapons/sword_s.png")
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}