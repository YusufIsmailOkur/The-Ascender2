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

public class Player extends Entity{
    
    KeyHandler keyH;
    int standCounter = 0;
    int hasKey;
    public int health = 3;
    public int maxHealth = 5;
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

        setDefaultValues();
        getPlayerImage();
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

    public void update(){

        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true){
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
            }
        }
    }
    public void interactNPC(int i){
        if (i != 999){

            if (gp.keyH.fPressed == true){
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
        gp.keyH.fPressed = false;
    }


    public void draw(Graphics2D g2) {
        
        // g2.setColor(Color.white);
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize);

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
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        // g2.setColor(Color.red);
        // g2.drawRect(x + solidArea.x, y + solidArea.y, solidArea.width, solidArea.height);
    }
}
