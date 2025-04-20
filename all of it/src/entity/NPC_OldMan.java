package entity;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import mainn.GamePanel;

public class NPC_OldMan extends Entity{
    
    public NPC_OldMan(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 1;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();
        setDialogue();
    }
    public void getImage(){

        try{

            up1 = ImageIO.read(getClass().getResourceAsStream("/res/oldManNPC/oldman_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/oldManNPC/oldman_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/oldManNPC/oldman_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/oldManNPC/oldman_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/oldManNPC/oldman_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/oldManNPC/oldman_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/oldManNPC/oldman_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/oldManNPC/oldman_right_2.png"));

        } catch(IOException e){
            e.printStackTrace();
        }
    }
    public void setDialogue(){

        dialogues[0] = "Hello, Adventurer!";
        dialogues[1] = "So what is your purpose here, Adventurer.\nTo find the Treasure?";
        dialogues[2] = "Well i would've loved to help you... \nBut you know things get hard at this age.";
        dialogues[3] = "Good luck on your adventure, bizzare man. \nUntil next time...";
    }


    public void setAction(){

        actionLockCounter++;

        if (actionLockCounter == 120){
            Random random = new Random();
            int i = random.nextInt(100) + 1; // pick up number from 1 to 100

            if (i <= 25){
                direction = "up";
            }
            if (i > 25 && i <= 50){
                direction = "down";
            }
            if (i > 50 && i <= 75){
                direction = "left";
            }
            if (i > 75 && i <= 100){
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }
    public void speak(){
        super.speak();
    }
}
