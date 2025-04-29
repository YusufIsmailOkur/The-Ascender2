package weapon;
import java.awt.image.*;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import mainn.*;

public class SuperWeapon {
    
    public BufferedImage imageUp, imageDown, imageLeft, imageRight;
    
    // Default image
    public BufferedImage image = imageRight;
    public String name;
    public boolean craftable;
    public boolean collision;
    public int x, y;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    public int attackValue;
    public int life;
    

    public void draw(Graphics2D g2, GamePanel gp){

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
