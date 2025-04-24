package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import mainn.GamePanel;
import mainn.KeyHandler;
import java.util.ArrayList;
import weapon.*;
import object.*;

public class Player extends Entity{
     
    KeyHandler keyH;
    int standCounter = 0;
    int hasKey;
    public int health = 3;
    public int maxHealth = 5;
    public boolean attacking = false;
    public ArrayList<SuperObject> objects = new ArrayList<>();
    public ArrayList<SuperWeapon> weapons = new ArrayList<>();
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackRight1, attackRight2, attackLeft1, attackLeft2;
    public SuperWeapon currentWeapon;
    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp); 
        this.keyH = keyH;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 30;

        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }
    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 5;
        direction = "down";
        hasKey = 0;
    }

    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_right_2.png"));

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void getPlayerAttackImage(){
        try{
            attackUp1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_attack_up_1.png"));
            attackUp2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_attack_up_2.png"));
            attackDown1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_attack_down_1.png"));
            attackDown2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_attack_down_2.png"));
            attackRight1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_attack_right_1.png"));
            attackRight2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_attack_right_2.png"));
            attackLeft1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_attack_left_1.png"));
            attackLeft2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_attack_left_2.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){

        if(attacking){
            attackAnimation();
            return;
        }

        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true){
            if (keyH.upPressed == true){
                direction = "up";
            }
            else if (keyH.downPressed == true){
                direction = "down";
            }
            else if (keyH.leftPressed == true){
                direction = "left";
            }
            else if (keyH.rightPressed == true){
                direction = "right";
            }

            // check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //check object collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            //CHECK ENTİTY COLLİSİON 
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

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
        } else {
            standCounter++;

            if (standCounter == 20){
                spriteNumber = 1;
                standCounter = 0;
            }
        }
    }

    public void pickUpObject(int i){
        if (i != 999){
            String objectName =gp.obj[i].name;

            switch(objectName){
                case "key":
                hasKey++;
                gp.obj[i]= null;
                gp.ui.showMessage("You got a key !!");
                break;

                case "door":
                if (hasKey > 0){
                    gp.obj[i] = null;
                    hasKey--;
                    gp.ui.showMessage("You opened the Door !!");
                } else {
                    gp.ui.showMessage("You need a key!");
                }

                break;
                case "boots":
                speed++;
                gp.obj[i] = null;
                gp.ui.showMessage("You just speed UP!");
                break;
                case "chest":
                gp.ui.gameFinished = true;
                break;
                 case "sword": // ** YENİ: kılıcı yerden al **
                WPN_Sword sword = new WPN_Sword();
                weapons.add(sword);
                currentWeapon = sword;
                gp.ui.showMessage("You picked up a sword!");
                gp.obj[i] = null;
                break;
            }
        }
    }

    public void attackAnimation(){

        spriteCounter++;

        if(spriteCounter < 5){
            spriteNumber = 1;
        }
        if(spriteCounter >= 5 && spriteCounter <= 25){
            spriteNumber = 2;

            int currentX = this.x;
            int currentY = this.y;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

           /*  switch(direction){
                case "up": y -= attackArea.height; break;
                case "down": y += attackArea.height; break;
                case "left": x -= attackArea.width; break;
                case "right": x += attackArea.width; break;
            } */

            //solidArea.height = attackArea.height;
            //solidArea.width = attackArea.width;
            
            //int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            //damageMonster(monsterIndex);
        }
        if(spriteCounter > 25){
            spriteNumber = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void pickUpWeapon(int i) {
        if (i != 999) {
            SuperWeapon wp = gp.weapon[i];
            weapons.add(wp);        
            currentWeapon = wp;          
            gp.weapon[i] = null;         
            gp.ui.showMessage("You picked up a " + wp.name + "!");
        }
    }
    
    public void interactNPC(int i){
        if (i != 999){

            if (gp.keyH.fPressed == true){
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
            gp.keyH.fPressed = false;
        }

        else if (gp.keyH.enterPressed == true){
            attacking = true;
        }

    }
    public void damageMonster(int monsterIndex){
        if(monsterIndex != 999){

        }
    }


    public void draw(Graphics2D g2) {
        
        // g2.setColor(Color.white);
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize);

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
            g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
            g2.setColor(Color.red);
            g2.drawRect(x + solidArea.x, y + solidArea.y, solidArea.width, solidArea.height);
        }
        else{

            if(direction.equals("up") ||direction.equals("down")){
                if(direction.equals("up")){
                    g2.drawImage(image, x, y - gp.tileSize, gp.tileSize, gp.tileSize * 2, null);
                }
                else{
                    g2.drawImage(image, x, y, gp.tileSize, gp.tileSize * 2, null);
                }
                g2.setColor(Color.red);
                g2.drawRect(x + solidArea.x, y + solidArea.y, solidArea.width, solidArea.height);
            }

            else{
                if(direction.equals("left")){
                    g2.drawImage(image, x - gp.tileSize, y, gp.tileSize * 2, gp.tileSize, null);
                }
                else{
                    g2.drawImage(image, x, y, gp.tileSize * 2, gp.tileSize, null);
                }
                g2.setColor(Color.red);
                g2.drawRect(x + solidArea.x, y + solidArea.y, solidArea.width, solidArea.height);
            }
        }

         
        // Draw current weapon
        if (currentWeapon != null) {
            BufferedImage wImg = null;
            int wx = x, wy = y;
            switch (direction) {
                case "up":
                    wImg = currentWeapon.imageUp;
                    wy = y - gp.tileSize;
                    break;
                case "down":
                    wImg = currentWeapon.imageDown;
                    wy = y + gp.tileSize;
                    break;
                case "left":
                    wImg = currentWeapon.imageLeft;
                    wx = x - gp.tileSize;
                    break;
                case "right":
                    wImg = currentWeapon.imageRight;
                    wx = x + gp.tileSize;
                    break;
            }
            g2.drawImage(wImg, wx, wy, gp.tileSize, gp.tileSize, null);
        }


        // Collasion
        g2.setColor(Color.red);
        g2.drawRect(x + solidArea.x, y + solidArea.y, solidArea.width, solidArea.height);
    }

    public void setCurrentWeapon(SuperWeapon w){
        currentWeapon = w;
    }
}

