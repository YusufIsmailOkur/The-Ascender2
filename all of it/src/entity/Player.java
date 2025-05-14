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
import object.OBJ_Key;
import object.OBJ_OpenedElevator;
import object.SuperObject;
import weapon.SuperWeapon;
import weapon.WPN_Bow;
import weapon.WPN_Sword;

public class Player extends Entity{
     
    KeyHandler keyH;
    int standCounter = 0;
    public long totalTime;
    int hasKey;
    public int mana = 3000;
    public final int MAX_MANA = 3000;
    public boolean bowCooldown = false;
    public boolean fireballCooldown = false;
    public int fireballCooldownCount = 0;
    public int bowCooldownCount = 0;
    public int maxInventorySize = 8;
    public ArrayList<SuperObject> objects = new ArrayList<>();
    public ArrayList<SuperWeapon> weapons = new ArrayList<>();
    public SuperWeapon currentWeapon;
    public WPN_Bow bow;
    public boolean[] discoveredFloors = new boolean[18];
    public boolean tookKeyFromNPC = false;
    public int previousCurrentFloor = -1;



    public int currentFloor = 0;


    public String name;
    public boolean finishedGame = false;


    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp); 
        this.keyH = keyH;
        attacking = false;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 30;

        finishedGame = false;
        health = 20;
        maxHealth = 20;

        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        discoveredFloors[0]=true;
    }
    public void setDefaultValues(){
        x = 1 * gp.tileSize;
        y = 7 * gp.tileSize;
        speed = 5;
        direction = "down";
        hasKey = 0;

        weapons.add(new WPN_Sword());
        bow = new WPN_Bow();
        weapons.add(bow);
        currentWeapon = weapons.get(0);

    }

    public void getPlayerAttackImage(){
        if(currentWeapon.name.equalsIgnoreCase("sword")){
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
        else if(currentWeapon.name.equalsIgnoreCase("Diamond Sword")){
            try{
                attackUp1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_attack_up_1_s.png"));
                attackUp2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_attack_up_2_s.png"));
                attackDown1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_attack_down_1_s.png"));
                attackDown2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_attack_down_2_s.png"));
                attackRight1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_attack_right_1_s.png"));
                attackRight2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_attack_right_2_s.png"));
                attackLeft1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_attack_left_1_s.png"));
                attackLeft2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_attack_left_2_s.png"));
            }
            catch(IOException e){
                e.printStackTrace();
            }
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

        if(attacking && (currentWeapon.name.equalsIgnoreCase("Sword") || currentWeapon.name.equalsIgnoreCase("diamond sword"))){
            getPlayerAttackImage();
            meleeAttackAnimation();
            if (invincibility) {
                iFrames++;
                if (iFrames > 60) {
                    invincibility = false;
                    iFrames = 0;
                }
            }
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
            mana -= 600;
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
            keyH.enterPressed == true || keyH.onePressed == true || keyH.twoPressed == true || keyH.fPressed == true || keyH.threePressed == true){
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
            else if(keyH.threePressed){
                if(weapons.size() >= 3){
                    currentWeapon = weapons.get(2);
                    getPlayerAttackImage();
                }
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
            if (collisionOn == false && keyH.enterPressed == false && keyH.onePressed == false && keyH.twoPressed == false && keyH.fPressed == false && keyH.threePressed == false){
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

            if(keyH.enterPressed == false && keyH.onePressed == false && keyH.twoPressed == false && keyH.fPressed == false && keyH.threePressed == false){
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
                boolean continueIfa = true;
                for(int f = 0; f < gp.monster[gp.player.currentFloor].length && continueIfa; f++){
                    if(gp.monster[gp.player.currentFloor][f] != null){
                        continueIfa = false;
                    }
                }
                if (continueIfa){
                    gp.gameState = gp.endStoryState;
                    finishedGame = true;
                }else{
                    gp.gameState = gp.dialogueState;
                    gp.ui.currentDialogue = "There are still monsters nearby!";
                }
                break;
                
                case "boots":
                speed++;
                gp.obj[currentFloor][i] = null;
                gp.ui.showMessage("You just speed UP!");
                break;

                case "chest":
                if(keyH.fPressed == true){
                    gp.obj[currentFloor][i].interact();
                } break;

                case "testdoorclosed":
                if(keyH.fPressed == true){
                    gp.obj[currentFloor][i].interact();
                    if(gp.obj[currentFloor][i].opened){
                        gp.obj[currentFloor][i] = null;
                    }
                }
                keyH.fPressed = false; 
                break;

                case "table":
                if(keyH.fPressed == true){
                    gp.obj[currentFloor][i].interact();
                } break;

                case "statue1":
                if(keyH.fPressed == true){
                    gp.obj[currentFloor][i].interact();
                }break; case "statue2":
                if(keyH.fPressed == true){
                    gp.obj[currentFloor][i].interact();
                }break; case "statue3":
                if(keyH.fPressed == true){
                    gp.obj[currentFloor][i].interact();
                }break; case "statue4":
                if(keyH.fPressed == true){
                    gp.obj[currentFloor][i].interact();
                }break; case "statue5":
                if(keyH.fPressed == true){
                    gp.obj[currentFloor][i].interact();
                }break; case "statue6":
                if(keyH.fPressed == true){
                    gp.obj[currentFloor][i].interact();
                }break; case "statue7":
                if(keyH.fPressed == true){
                    gp.obj[currentFloor][i].interact();
                }break; case "statue8":
                if(keyH.fPressed == true){
                    gp.obj[currentFloor][i].interact();
                } break;
                

                case "sword": // ** YENİ: kılıcı yerden al **
                WPN_Sword sword = new WPN_Sword();
                weapons.add(sword);
                currentWeapon = sword;
                gp.ui.showMessage("You picked up a sword!");
                gp.obj[currentFloor][i] = null;
                break;

                case "elevator":
                if (hasKey > 0){
                    int xs = gp.obj[currentFloor][i].x;
                    int ys = gp.obj[currentFloor][i].y;
                    gp.obj[currentFloor][i] = null;
                    gp.obj[currentFloor][i] = new OBJ_OpenedElevator();
                    gp.obj[currentFloor][i].x = xs;
                    gp.obj[currentFloor][i].y = ys;
                     
                    for(SuperObject obj : objects){
                        if(obj.name.equals("key")){
                            objects.remove(obj);
                            break;
                        }
                    }

                    discoveredFloors[currentFloor+1] = true;
                    currentFloor++;
                    if (currentFloor == 4 || currentFloor == 10 || currentFloor == 15){
                        gp.keyH.avoidMusicRepeat();
                    }
                    gp.tileM.loadMap("/res/maps/map" + (currentFloor+1) + ".txt");
                    x = 1*gp.tileSize;
                    y = 7*gp.tileSize;
                    hasKey--;
                }
                break;

                case "openedelevator":
                discoveredFloors[currentFloor+1] = true;
                currentFloor++;
                if (currentFloor == 4 || currentFloor == 10 || currentFloor == 15){
                        gp.keyH.avoidMusicRepeat();
                }
                gp.tileM.loadMap("/res/maps/map" + (currentFloor+1) + ".txt");
                x = 1*gp.tileSize;
                y = 7*gp.tileSize;

                case "closedelevator":
                
                break;

                case "keyelevator":
                boolean continueIf = true;
                for(int f = 0; f < gp.monster[gp.player.currentFloor].length && continueIf; f++){
                    if(gp.monster[gp.player.currentFloor][f] != null){
                        continueIf = false;
                    }
                }
                if(continueIf){
                    int xs = gp.obj[currentFloor][i].x;
                    int ys = gp.obj[currentFloor][i].y;
                    gp.obj[currentFloor][i] = null;
                    gp.obj[currentFloor][i] = new OBJ_OpenedElevator();
                    gp.obj[currentFloor][i].x = xs;
                    gp.obj[currentFloor][i].y = ys;
                     
                    for(SuperObject obj : objects){
                        if(obj.name.equals("key")){
                            objects.remove(obj);
                            break;
                        }
                    }

                    discoveredFloors[currentFloor+1] = true;
                    currentFloor++;
                    if (currentFloor == 4 || currentFloor == 10 || currentFloor == 15){
                        gp.keyH.avoidMusicRepeat();
                    }

                    gp.tileM.loadMap("/res/maps/map" + (currentFloor+1) + ".txt");
                    x = 1*gp.tileSize;
                    y = 7*gp.tileSize;
                }
                else{
                    gp.gameState = gp.dialogueState;
                    gp.ui.currentDialogue = "There are still monsters nearby!";
                }
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

            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster[currentFloor]);
            damageMonster(monsterIndex, currentWeapon.attackValue);

            x = currentX;
            y = currentY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
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
            if (i == 0 && gp.npc[currentFloor][i] instanceof NPC_OldMan && gp.npc[currentFloor][i].hasfinishedTalking && !tookKeyFromNPC){
                gp.obj[currentFloor][3] = new OBJ_Key();
                gp.obj[currentFloor][3].x = gp.npc[currentFloor][i].x + gp.tileSize;
                gp.obj[currentFloor][3].y = gp.npc[currentFloor][i].y + gp.tileSize;
                tookKeyFromNPC = true;
            }
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

    public void damageMonster(int monsterIndex, int damage){
        if(monsterIndex != 999){  
            if (!gp.monster[currentFloor][monsterIndex].invincibility) {
                gp.monster[currentFloor][monsterIndex].health -= damage;
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
            if(attacking == true && (currentWeapon.name.equalsIgnoreCase("sword") || currentWeapon.name.equalsIgnoreCase("diamond sword"))){
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
            if(attacking == true && (currentWeapon.name.equalsIgnoreCase("sword") || currentWeapon.name.equalsIgnoreCase("diamond sword"))){
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
            if(attacking == true && (currentWeapon.name.equalsIgnoreCase("sword") || currentWeapon.name.equalsIgnoreCase("diamond sword"))){
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
            if(attacking == true && (currentWeapon.name.equalsIgnoreCase("sword") || currentWeapon.name.equalsIgnoreCase("diamond sword"))){
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
            // g2.setColor(Color.red);
            // g2.drawRect(x + solidArea.x, y + solidArea.y, solidArea.width, solidArea.height);
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
                // g2.setColor(Color.red);
                // g2.drawRect(x + solidArea.x, y + solidArea.y, solidArea.width, solidArea.height);
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
                // g2.setColor(Color.red);
                // g2.drawRect(x + solidArea.x, y + solidArea.y, solidArea.width, solidArea.height);
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
        // g2.setColor(Color.red);
        // g2.drawRect(x + solidArea.x, y + solidArea.y, solidArea.width, solidArea.height);
    }
}

