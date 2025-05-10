package object;

import java.io.IOException;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.*;

import javax.imageio.ImageIO;

import entity.Projectile;
import mainn.GamePanel;

public class OBJ_Rock extends Projectile{

    GamePanel gp;

    public OBJ_Rock(GamePanel gp){
        super(gp);
        this.gp = gp;

        name = "Fireball";
        speed = 8;
        maxLife = 400;
        life = maxLife;
        attack = 2;

        getImage();
        
    }

    public void getImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/projectiles/rock_down_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/projectiles/rock_down_1.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/projectiles/rock_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/projectiles/rock_down_1.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/projectiles/rock_down_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/projectiles/rock_down_1.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/projectiles/rock_down_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/projectiles/rock_down_1.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;
        switch (direction){
            case "up":
            if(spriteNumber == 1){
                image = up1;
            }
            if (spriteNumber == 2){
                image = up2;
            }
            g2.drawImage(image, x, y, gp.tileSize * 2, gp.tileSize * 2, null);
            break;
            case "down":
            if(spriteNumber == 1){
                image = down1;
            }
            if (spriteNumber == 2){
                image = down2;
            }
            g2.drawImage(image, x, y, gp.tileSize * 2, gp.tileSize * 2, null);
            break;
            case "left":
            if(spriteNumber == 1){
                image = left1;
            }
            if (spriteNumber == 2){
                image = left2;
            }
            g2.drawImage(image, x, y, gp.tileSize * 2, gp.tileSize * 2, null);
            break;
            case "right":
            if(spriteNumber == 1){
                image = right1;
            }
            if (spriteNumber == 2){
                image = right2;
            }
            g2.drawImage(image, x, y, gp.tileSize * 2, gp.tileSize * 2, null);
            break;
        }
        if (invincibility) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}