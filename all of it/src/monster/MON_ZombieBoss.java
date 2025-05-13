package monster;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import entity.Player;
import mainn.GamePanel;

public class MON_ZombieBoss extends Entity{

    public GamePanel gp;
    public Player player;
    public MON_ZombieBoss(GamePanel gp) {
        super(gp);
        
        direction = "down";
        speed = 2;
        maxHealth = 130;
        isBoss = true;
        health = maxHealth;
        type = 2;
        name = "Zombie Boss";
        damage = 3;
        this.gp = gp;
        solidArea.x = 12;
        solidArea.y = 60;
        solidArea.width = 42 * 4;
        solidArea.height = 38 * 4;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        this.player = gp.player;

        getMonsterImage();
    }

    public void getMonsterImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/monster/zombie_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/monster/zombie_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/monster/zombie_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/monster/zombie_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/monster/zombie_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/monster/zombie_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/monster/zombie_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/monster/zombie_right_2.png"));

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void setAction(){

        actionLockCounter++;

        if (actionLockCounter == 120){
            String dx = player.x - this.x > 0 ? "right" : "left";
            String dy = player.y - this.y < 0 ? "up" : "down";
            if (Math.abs(player.x - this.x) < Math.abs(player.y - this.y)) {
                direction = dy;
                if (collisionOn){
                    direction = dx;
                }
            }
            else if (Math.abs(player.y - this.y) < Math.abs(player.x - this.x)) {
                direction = dx;
                if (collisionOn){
                    direction = dy;
                }
            }
            actionLockCounter = 0;
        }
    }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up": image = (spriteNumber == 1) ? up1 : up2; break;
            case "down": image = (spriteNumber == 1) ? down1 : down2; break;
            case "left": image = (spriteNumber == 1) ? left1 : left2; break;
            case "right": image = (spriteNumber == 1) ? right1 : right2; break;
        }

        g2.drawImage(image, x, y, gp.tileSize * 4, gp.tileSize * 4, null);
    }
    
}
