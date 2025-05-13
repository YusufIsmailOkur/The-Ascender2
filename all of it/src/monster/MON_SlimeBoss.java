package monster;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import entity.Entity;
import entity.Player;
import entity.Projectile;
import mainn.GamePanel;
import object.OBJ_Elevator;
import object.OBJ_Rock;

public class MON_SlimeBoss extends Entity{
    public Player player;
    public GamePanel gp;
    boolean rockCooldown = false;
    int rockCooldownCounter = 0;
    boolean isSpawned = false;

    public MON_SlimeBoss(GamePanel gp) {
        super(gp);
        
        direction = "down";
        name = "Slime Boss";
        speed = 1;
        maxHealth = 100;
        health = maxHealth;
        type = 2;
        isBoss = true;
        damage = 2;
        projectile = new OBJ_Rock(gp);

        int size  = gp.tileSize * 5;
        solidArea.x = 45;
        solidArea.y = 80;
        solidArea.width = size - gp.tileSize * 3/2;
        solidArea.height = size - gp.tileSize * 2;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        this.gp = gp;
        this.player = gp.player;

        getMonsterImage();
    }

    public void getMonsterImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/monster/redslime_down_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/monster/redslime_down_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/monster/redslime_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/monster/redslime_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/monster/redslime_down_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/monster/redslime_down_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/monster/redslime_down_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/monster/redslime_down_2.png"));

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

        if((Math.abs(x - gp.player.x) < 100 || Math.abs(y - gp.player.y) < 100) && !rockCooldown){
            projectile = new OBJ_Rock(gp);
            if(Math.abs(x - gp.player.x) < 100){
                if(gp.player.y > y){
                    projectile.direction = "down";
                }
                else{
                    projectile.direction = "up";
                }
            }
            else if(Math.abs(y - gp.player.y) < 100){
                if(gp.player.x > x){
                    projectile.direction = "right";
                }
                else{
                    projectile.direction = "left";
                }
            }
            projectile.set(x + gp.tileSize * 2,y + gp.tileSize * 2, direction, true, this);
            gp.projectiles.add(projectile);
            rockCooldown = true;
            rockCooldownCounter = 0;
        }
        if(rockCooldown == true){
            if(rockCooldownCounter < 120){
                rockCooldownCounter++;
            }
            else{
                rockCooldownCounter = 0;
                rockCooldown = false;
            }
        }

        if(!isSpawned && health <= 50){
            Random rand = new Random();
            gp.monster[3][1] = new MON_GreenSlime(gp);
            gp.monster[3][1].x = gp.tileSize * rand.nextInt(18);
            gp.monster[3][1].y = gp.tileSize * rand.nextInt(12);
            gp.monster[3][2] = new MON_GreenSlime(gp);
            gp.monster[3][2].x = gp.tileSize * rand.nextInt(18);
            gp.monster[3][2].y = gp.tileSize * rand.nextInt(12);
            gp.monster[3][3] = new MON_GreenSlime(gp);
            gp.monster[3][3].x = gp.tileSize * rand.nextInt(18);
            gp.monster[3][3].y = gp.tileSize * rand.nextInt(12);

            isSpawned = true;
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
            break;
            case "down":
            if(spriteNumber == 1){
                image = down1;
            }
            if (spriteNumber == 2){
                image = down2;
            }
            break;
            case "left":
            if(spriteNumber == 1){
                image = left1;
            }
            if (spriteNumber == 2){
                image = left2;
            }
            break;
            case "right":
            if(spriteNumber == 1){
                image = right1;
            }
            if (spriteNumber == 2){
                image = right2;
            }
            break;
        }
        g2.drawImage(image, x, y, gp.tileSize * 5, gp.tileSize * 5, null);
        }
    
}
