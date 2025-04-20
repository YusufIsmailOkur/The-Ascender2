package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.*;

import mainn.GamePanel;
public class Entity {
    
    GamePanel gp;
    public int x,y;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNumber = 1;
    public Rectangle solidArea = new Rectangle(0,0,48,48);

    public int solidAreaDefaultX, solidAreaDefaultY;

    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    String dialogues[] = new String[20];
    int dialogueIndex = 0;

    public int health=5;
    public boolean alive = true;

    public Entity(GamePanel gp){
        this.gp = gp;
    }

    public void setAction() {}
    public void speak() {
        if (dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch(gp.player.direction){
            case "up":
            direction = "down";
            break;
            case "down":
            direction = "up";
            break;
            case "left": 
            direction = "right";
            break;
            case "right":
            direction = "left";
            break;
        }
    }
    public void update(){
        setAction();
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkPlayer(this);
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
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
