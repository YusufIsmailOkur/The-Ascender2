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
import object.OBJ_Arrow;
import object.OBJ_Elevator;
import object.OBJ_Rock;

public class MON_SkeletonBoss extends Entity{
    public Player player;
    public GamePanel gp;
    boolean isSpawned = false;
    public int attackCooldown = 0;
    public boolean canAttack = true;
    public boolean isStuck = false;
    public int stuckCounter = 0;
    int prevX, prevY;
    boolean previousDirectionSet = false;

    public MON_SkeletonBoss(GamePanel gp) {
        super(gp);
        
        direction = "down";
        name = "Skeleton Lord";
        speed = 1;
        attacking = false;
        maxHealth = 300;
        health = maxHealth;
        type = 2;
        isBoss = true;
        damage = 2;
        int size  = gp.tileSize * 5;
        solidArea.x = 20
        ;
        solidArea.y = 4;
        solidArea.width = size - 48*2;
        solidArea.height = size - 48;
        attackArea.width = 170;
        attackArea.height = 170;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        motion1_duration = 25;
        motion2_duration = 50;
        this.gp = gp;
        this.player = gp.player;

        getMonsterImage();
        getAttackImage();
    }

    public void getMonsterImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/monster/skeletonlord_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/monster/skeletonlord_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/monster/skeletonlord_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/monster/skeletonlord_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/monster/skeletonlord_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/monster/skeletonlord_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/monster/skeletonlord_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/monster/skeletonlord_right_2.png"));

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void getAttackImage(){

        try{
            attackUp1 = ImageIO.read(getClass().getResourceAsStream("/res/monster/skeletonlord_attack_up_1.png"));
            attackUp2 = ImageIO.read(getClass().getResourceAsStream("/res/monster/skeletonlord_attack_up_2.png"));
            attackDown1 = ImageIO.read(getClass().getResourceAsStream("/res/monster/skeletonlord_attack_down_1.png"));
            attackDown2 = ImageIO.read(getClass().getResourceAsStream("/res/monster/skeletonlord_attack_down_2.png"));
            attackRight1 = ImageIO.read(getClass().getResourceAsStream("/res/monster/skeletonlord_attack_right_1.png"));
            attackRight2 = ImageIO.read(getClass().getResourceAsStream("/res/monster/skeletonlord_attack_right_2.png"));
            attackLeft1 = ImageIO.read(getClass().getResourceAsStream("/res/monster/skeletonlord_attack_left_1.png"));
            attackLeft2 = ImageIO.read(getClass().getResourceAsStream("/res/monster/skeletonlord_attack_left_2.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void setAction(){
        actionLockCounter++;

        int dx = player.x - (x + up1.getWidth() / 2);
        int dy = player.y - (y + up1.getHeight() / 2);
        int range = gp.tileSize * 10;

        if ((Math.abs(dx) < range && Math.abs(dy) < gp.tileSize) ||
            (Math.abs(dy) < range && Math.abs(dx) < gp.tileSize)) {
                if (Math.abs(dx) > Math.abs(dy)) {
                    direction = dx > 0 ? "right" : "left";
                } else {
                    direction = dy > 0 ? "down" : "up";
                }
            
            if(canAttack){
                attacking = true;
                attack();
            } 

        }
        if(!canAttack){
            if(attackCooldown < 120){
                attackCooldown++;
            }
            else{
                canAttack = true;
                attackCooldown = 0;
            }
            
        }
        else{
            attacking = false;

            if (actionLockCounter >= 120) {
                int i = new Random().nextInt(100);
                if (i < 25) direction = "up";
                else if (i < 50) direction = "down";
                else if (i < 75) direction = "left";
                else direction = "right";
    
                actionLockCounter = 0;
            }
        }


    }

    public void update(){
        setAction();
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        boolean contact = gp.cChecker.checkPlayer(this);

        if (contact && this.type == 2) {
            damagePlayer(damage);
        }
        //if collision is on player cant move
        if (collisionOn == false){
            switch (direction){
            case "up":
            y -= speed;
            break;
            case "down":
            y += speed;
            break;
            case "left":
            x -= speed;
            break;
            case "right":
            x += speed;
            break;
            }
        }
        
        spriteCounter++;
        if(spriteCounter > 10){
            if (spriteNumber == 1){
                spriteNumber = 2;
            } else if (spriteNumber == 2){
                spriteNumber = 1;
            }
            spriteCounter = 0;
        } 

        if (invincibility) {
            iFrames++;
            if (iFrames > 30) {
                invincibility = false;
                iFrames = 0;
            }
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;
        switch (direction){
            case "up":
            if(attacking == false){
                if(spriteNumber == 1){
                    image = up1;
                }
                if (spriteNumber == 2){
                    image = up2;
                }
            }
            if(attacking == true){
                if(spriteNumber == 1){
                    image = attackUp1;
                }
                if(spriteNumber == 2){
                    image = attackUp2;
                }
            }
            break;
            case "down":
            if(attacking == false){
                if(spriteNumber == 1){
                    image = down1;
                }
                if (spriteNumber == 2){
                    image = down2;
                }
            }
            if(attacking == true){
                if(spriteNumber == 1){
                    image = attackDown1;
                }
                if(spriteNumber == 2){
                    image = attackDown2;
                }
            }
            break;
            case "left":
            if(attacking == false){
                if(spriteNumber == 1){
                    image = left1;
                }
                if (spriteNumber == 2){
                    image = left2;
                }
            }
            if(attacking == true){
                if(spriteNumber == 1){
                    image = attackLeft1;
                }
                if(spriteNumber == 2){
                    image = attackLeft2;
                }
            }
            break;
            case "right":
            if(attacking == false){
                if(spriteNumber == 1){
                    image = right1;
                }
                if (spriteNumber == 2){
                    image = right2;
                }
            }
            if(attacking == true){
                if(spriteNumber == 1){
                    image = attackRight1;
                }
                if(spriteNumber == 2){
                    image = attackRight2;
                }
            }
            break;
        }
        if(attacking == false){

            g2.drawImage(image, x, y, gp.tileSize * 5, gp.tileSize * 5, null);            // g2.setColor(Color.red);
            // g2.drawRect(x + solidArea.x, y + solidArea.y, solidArea.width, solidArea.height);
        }
        else{

            if(direction.equals("up") ||direction.equals("down")){
                if(direction.equals("up")){
                    g2.drawImage(image, x, y - gp.tileSize * 5, gp.tileSize * 5, gp.tileSize * 10, null);
                }
                else{

                    g2.drawImage(image, x, y, gp.tileSize * 5, gp.tileSize * 10, null);
                }
                // g2.setColor(Color.red);
                // g2.drawRect(x + solidArea.x, y + solidArea.y, solidArea.width, solidArea.height);
            }

            else{
                if(direction.equals("left")){

                    g2.drawImage(image, x - gp.tileSize * 5, y, gp.tileSize * 10, gp.tileSize * 5, null);
                }
                else{

                    g2.drawImage(image, x, y, gp.tileSize * 10, gp.tileSize * 5, null);
                }
                // g2.setColor(Color.red);
                // g2.drawRect(x + solidArea.x, y + solidArea.y, solidArea.width, solidArea.height);
            }
        }
    }

    public void attack(){

        if(spriteCounter < 5){
            spriteNumber = 1;
        }
        if(spriteCounter >= 5 && spriteCounter <= 25){
            spriteNumber = 2;

            int currentX = x;
            int currentY = y;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            switch (direction) {
                case "up":
                    y -= attackArea.height;
                    break;
                case "down":
                    y += attackArea.height;
                    break;
                case "left":
                    x -= attackArea.width;
                    break;

                case "right":
                    x += attackArea.width;
                    break;
            
                default:
                    break;
            }

            solidArea.height = attackArea.height;
            solidArea.width = attackArea.width;

            boolean isNearPlayer = gp.cChecker.checkPlayer(this);
            if(isNearPlayer)
                damagePlayer(damage);
            x = currentX;
            y = currentY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if(spriteCounter > 25){
            spriteNumber = 1;
            spriteCounter = 0;
            attacking = false;
            canAttack = false;
        }
    }
}