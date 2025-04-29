package monster;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import entity.Player;
import mainn.GamePanel;

public class MON_GreenSlime extends Entity{

    public int maxHealth;
    public String name;
    public Player player;
    public MON_GreenSlime(GamePanel gp) {
        super(gp);
        
        direction = "down";
        name = "Green Slime";
        speed = 1;
        maxHealth = 5;
        health = maxHealth;
        type = 2;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        this.player = gp.player;

        getMonsterImage();
    }

    public void getMonsterImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/monster/greenslime_down_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/monster/greenslime_down_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/monster/greenslime_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/monster/greenslime_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/monster/greenslime_down_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/monster/greenslime_down_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/monster/greenslime_down_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/monster/greenslime_down_2.png"));

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
                }
            else if (Math.abs(player.y - this.y) < Math.abs(player.x - this.x)) {
                direction = dx;
            }
            actionLockCounter = 0;
        }
    }
}
