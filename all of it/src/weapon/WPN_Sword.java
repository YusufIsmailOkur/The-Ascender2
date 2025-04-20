package weapon;

import java.io.IOException;
import javax.imageio.ImageIO;

public class WPN_Sword extends SuperWeapon {

    public WPN_Sword() {

        name = "Sword";
        attackValue = 1;
        collision = true;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/weapons/sword.png"));
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
        
