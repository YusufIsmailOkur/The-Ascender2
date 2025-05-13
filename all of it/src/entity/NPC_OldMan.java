package entity;
import java.io.IOException;

import javax.imageio.ImageIO;

import mainn.GamePanel;

public class NPC_OldMan extends Entity{
    
    public NPC_OldMan(GamePanel gp, int index){
        super(gp);

        direction = "down";
        speed = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();
        setDialogue(index);
    }
    public void getImage(){

        try{

            up1 = ImageIO.read(getClass().getResourceAsStream("/res/oldManNPC/oldman_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/oldManNPC/oldman_up_1.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/oldManNPC/oldman_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/oldManNPC/oldman_down_1.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/oldManNPC/oldman_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/oldManNPC/oldman_left_1.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/oldManNPC/oldman_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/oldManNPC/oldman_right_1.png"));

        } catch(IOException e){
            e.printStackTrace();
        }
    }
    public void setDialogue(int index){

        if(index == 1){
            dialogues[0] = "I see you've made it here, somehow… \nThe truth you're looking for—it's locked away, high above.";
            dialogues[1] = "But the path is no longer safe. Security puzzles still guard the \nhalls, and failed experiments now roam free.";
            dialogues[2] = "I wish I could help, but my body gave out long ago.";
            dialogues[3] = "Take this key. It opens the first door… and your journey begins.";
        }
        else if(index == 2){
            dialogues[0] = "Be careful for the next floor, a big danger might be present.\nPrepare accordingly...";
        }
    }


    // public void setAction(){

    //     actionLockCounter++;

    //     if (actionLockCounter == 120){
    //         Random random = new Random();
    //         int i = random.nextInt(100) + 1; // pick up number from 1 to 100

    //         if (i <= 25){
    //             direction = "up";
    //         }
    //         if (i > 25 && i <= 50){
    //             direction = "down";
    //         }
    //         if (i > 50 && i <= 75){
    //             direction = "left";
    //         }
    //         if (i > 75 && i <= 100){
    //             direction = "right";
    //         }
    //         actionLockCounter = 0;
    //     }
    // }
    public void speak(){
        super.speak();
    }
}
