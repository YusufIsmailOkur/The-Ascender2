package object;
import java.awt.image.*;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import mainn.*;

public class SuperObject {
    
    public boolean interactable = false;
    public boolean usable = false;
    public BufferedImage image;
    public String name;
    public boolean collision;
    public int x, y;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    public boolean craftable;
    public boolean opened;

    public void draw(Graphics2D g2, GamePanel gp){

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
    @Override
    public String toString(){
        return "Object";
    }

    public void interact(){}

    public boolean isInteractable() {
        return interactable;
    }

}
