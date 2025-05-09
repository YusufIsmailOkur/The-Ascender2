package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import mainn.GamePanel;
import mainn.KeyHandler;
import object.OBJ_Arrow;
import object.OBJ_Fireball;
import object.SuperObject;
import weapon.SuperWeapon;
import weapon.WPN_Bow;
import weapon.WPN_Sword;

public class Player extends Entity{
     
    KeyHandler keyH;
    int standCounter = 0;
    int hasKey;
    public int health = 3;
    public int maxHealth = 5;
    public int mana = 2000;
    public final int MAX_MANA = 2000;
    public boolean attacking = false;
    public boolean bowCooldown = false;
    public boolean fireballCooldown = false;
    public int fireballCooldownCount = 0;
    public int bowCooldownCount = 0;
    public ArrayList<SuperObject> objects = new ArrayList<>();
    public ArrayList<SuperWeapon> weapons = new ArrayList<>();
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackRight1, attackRight2, attackLeft1, attackLeft2;
    public SuperWeapon currentWeapon;

    public int currentFloor = 0;


    public String name;


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

        weapons.add(new WPN_Sword());
        weapons.add(new WPN_Bow());
        currentWeapon = weapons.get(0);

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

    public void update(){

        if(attacking && currentWeapon.name.equalsIgnoreCase("Sword")){
            meleeAttackAnimation();
            return;
        }

        else if(attacking && currentWeapon.name.equalsIgnoreCase("Bow")){

            if(currentWeapon.life > 0  && !bowCooldown){
                OBJ_Arrow arrow = new OBJ_Arrow(gp);
                arrow.set(x, y , direction, true, this);
                gp.projectiles.add(arrow);
                currentWeapon.life--;
                bowCooldown = true;
            }
            attacking = false;
        }
        
        if(bowCooldown){
            if(bowCooldownCount < 120){
                bowCooldownCount++;
            }
            else{
                bowCooldownCount = 0;
                bowCooldown = false;
            }
        }

        if(keyH.spacePressed == true && mana > 100 && !fireballCooldown){
            mana -= 400;
            fireballCooldown = true;
            fireball();
        }
        else if(fireballCooldown && fireballCooldownCount < 200){
            fireballCooldownCount++;
        }

        else if(fireballCooldown && fireballCooldownCount == 200){
            fireballCooldown = false;
            fireballCooldownCount = 0;
        }
        if(mana != MAX_MANA){
            mana++;
        }

        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true || 
            keyH.enterPressed == true || keyH.onePressed == true || keyH.twoPressed == true){
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
            if (keyH.enterPressed) {
                attacking = true;
            }
            if (keyH.onePressed){
                currentWeapon = weapons.get(0);
            }
            else if(keyH.twoPressed){
                currentWeapon = weapons.get(1);
            }


            // check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //check object collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            //CHECK ENTİTY COLLİSİON 
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc[currentFloor]);
            interactNPC(npcIndex);

            //CHECK MONSTER COLLISION
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster[currentFloor]);
            contactDamage(monsterIndex);


            //if collision is on player cant move
            if (collisionOn == false && keyH.enterPressed == false && keyH.onePressed == false && keyH.twoPressed == false){
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

            if(keyH.enterPressed == false && keyH.onePressed == false && keyH.twoPressed == false){
                spriteCounter++;
                if(spriteCounter > 10){
                    if (spriteNumber == 1){
                        spriteNumber = 2;
                    } else if (spriteNumber == 2){
                        spriteNumber = 1;
                    }
                    spriteCounter = 0;
                }
            }
        } else {
            standCounter++;

            if (standCounter == 20){
                spriteNumber = 1;
                standCounter = 0;
            }
        }
        
        if (invincibility) {
            iFrames++;
            if (iFrames > 60) {
                invincibility = false;
                iFrames = 0;
            }
        }
    }

    public void pickUpObject(int i){
        if (i != 999){
            String objectName =gp.obj[currentFloor][i].name;

            switch(objectName){
                case "key":
                hasKey++;
                objects.add(gp.obj[currentFloor][i]);
                gp.obj[currentFloor][i]= null;
                gp.ui.showMessage("You got a key !!");
                break;

                case "door":
                if (hasKey > 0){
                    gp.obj[currentFloor][i] = null;
                    hasKey--;
                    for(SuperObject obj : objects){
                         if(obj.name.equals("Key")){
                              objects.remove(obj);
                        }
                    }
                    gp.ui.showMessage("You opened the Door !!");
                } else {
                    gp.ui.showMessage("You need a key!");
                }

                break;
                case "boots":
                speed++;
                gp.obj[currentFloor][i] = null;
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
                gp.obj[currentFloor][i] = null;
                break;
                case "elevator":
                currentFloor++;
                gp.tileM.loadMap("/res/maps/map" + (currentFloor+1) + ".txt");
                x = 1*gp.tileSize;
                y = 7*gp.tileSize;
                break;
            }
        }
    }

    public void meleeAttackAnimation(){

        spriteCounter++;

        if(spriteCounter < 5){
            spriteNumber = 1;
        }
        if(spriteCounter >= 5 && spriteCounter <= 25){
            spriteNumber = 2;
            solidArea.height = attackArea.height;
            solidArea.width = attackArea.width;

            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster[currentFloor]);
            damageMonster(monsterIndex);

            
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

    public void fireball(){
        OBJ_Fireball fireball = new OBJ_Fireball(gp);
        fireball.set(x, y , direction, true, this);
        gp.projectiles.add(fireball);
    }
    
    public void interactNPC(int i){
        if (i != 999){

            if (gp.keyH.fPressed == true){
                gp.gameState = gp.dialogueState;
                gp.npc[currentFloor][i].speak();
            }
            gp.keyH.fPressed = false;
        }
        else if (gp.keyH.enterPressed == true){
            attacking = true;
        }
    }

    public void contactDamage(int i){
        
        if (i != 999) {
            if (invincibility == false) {
                health -= 1;
                invincibility = true;
            }
        }
    }

    public void damageMonster(int monsterIndex){
        if(monsterIndex != 999){  
            if (!gp.monster[currentFloor][monsterIndex].invincibility) {
                gp.monster[currentFloor][monsterIndex].health--;
                gp.monster[currentFloor][monsterIndex].invincibility = true;

                if (gp.monster[currentFloor][monsterIndex].health <= 0) {
                    gp.monster[currentFloor][monsterIndex] = null;
                }
            }  
        }
    }

    public void draw(Graphics2D g2) {
        
        // g2.setColor(Color.white);
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;
        if (name != null) {
            g2.setFont(new Font("Arial", Font.BOLD, 14));
            g2.setColor(Color.WHITE);
            FontMetrics fm = g2.getFontMetrics();
            int textWidth = fm.stringWidth(name);
            int textX = x + (gp.tileSize - textWidth) / 2;
            int textY = y - 6;

            g2.setColor(Color.BLACK);
            g2.drawString(name, textX + 1, textY + 1);
            g2.setColor(Color.WHITE);
            g2.drawString(name, textX, textY);


        }
        switch (direction){
            case "up":
            if(attacking == false || currentWeapon.name.equalsIgnoreCase("bow")){
                if(spriteNumber == 1){
                    image = up1;
                }
                if (spriteNumber == 2){
                    image = up2;
                }
            }
            if(attacking == true && currentWeapon.name.equalsIgnoreCase("sword")){
                if(spriteNumber == 1){
                    image = attackUp1;
                }
                if(spriteNumber == 2){
                    image = attackUp2;
                }
            }
            break;
            case "down":
            if(attacking == false || currentWeapon.name.equalsIgnoreCase("bow")){
                if(spriteNumber == 1){
                    image = down1;
                }
                if (spriteNumber == 2){
                    image = down2;
                }
            }
            if(attacking == true && currentWeapon.name.equalsIgnoreCase("sword")){
                if(spriteNumber == 1){
                    image = attackDown1;
                }
                if(spriteNumber == 2){
                    image = attackDown2;
                }
            }
            break;
            case "left":
            if(attacking == false || currentWeapon.name.equalsIgnoreCase("bow")){
                if(spriteNumber == 1){
                    image = left1;
                }
                if (spriteNumber == 2){
                    image = left2;
                }
            }
            if(attacking == true && currentWeapon.name.equalsIgnoreCase("sword")){
                if(spriteNumber == 1){
                    image = attackLeft1;
                }
                if(spriteNumber == 2){
                    image = attackLeft2;
                }
            }
            break;
            case "right":
            if(attacking == false || currentWeapon.name.equalsIgnoreCase("bow")){
                if(spriteNumber == 1){
                    image = right1;
                }
                if (spriteNumber == 2){
                    image = right2;
                }
            }
            if(attacking == true && currentWeapon.name.equalsIgnoreCase("sword")){
                if(spriteNumber == 1){
                    image = attackRight1;
                }
                if(spriteNumber == 2){
                    image = attackRight2;
                }
            }
            break;
        }
        if(attacking == false || currentWeapon.name.equalsIgnoreCase("Bow")){
            if (invincibility) {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
            }
            g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            g2.setColor(Color.red);
            g2.drawRect(x + solidArea.x, y + solidArea.y, solidArea.width, solidArea.height);
        }
        else{

            if(direction.equals("up") ||direction.equals("down")){
                if(direction.equals("up")){
                    if (invincibility) {
                        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
                    }
                    g2.drawImage(image, x, y - gp.tileSize, gp.tileSize, gp.tileSize * 2, null);
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                }
                else{
                    if (invincibility) {
                        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
                    }
                    g2.drawImage(image, x, y, gp.tileSize, gp.tileSize * 2, null);
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                }
                g2.setColor(Color.red);
                g2.drawRect(x + solidArea.x, y + solidArea.y, solidArea.width, solidArea.height);
            }

            else{
                if(direction.equals("left")){
                    if (invincibility) {
                        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
                    }
                    g2.drawImage(image, x - gp.tileSize, y, gp.tileSize * 2, gp.tileSize, null);
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                }
                else{
                    if (invincibility) {
                        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
                    }
                    g2.drawImage(image, x, y, gp.tileSize * 2, gp.tileSize, null);
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                }
                g2.setColor(Color.red);
                g2.drawRect(x + solidArea.x, y + solidArea.y, solidArea.width, solidArea.height);
            }
        }
         
        // Draw current weapon
        // if (currentWeapon != null) {
        //     BufferedImage wImg = null;
        //     int wx = x, wy = y;
        //     switch (direction) {
        //         case "up":
        //             wImg = currentWeapon.imageUp;
        //             wy = y - gp.tileSize;
        //             break;
        //         case "down":
        //             wImg = currentWeapon.imageDown;
        //             wy = y + gp.tileSize;
        //             break;
        //         case "left":
        //             wImg = currentWeapon.imageLeft;
        //             wx = x - gp.tileSize;
        //             break;
        //         case "right":
        //             wImg = currentWeapon.imageRight;
        //             wx = x + gp.tileSize;
        //             break;
        //     }
        //     g2.drawImage(wImg, wx, wy, gp.tileSize, gp.tileSize, null);
        // }


        // Collasion
        g2.setColor(Color.red);
        g2.drawRect(x + solidArea.x, y + solidArea.y, solidArea.width, solidArea.height);
    }
}

