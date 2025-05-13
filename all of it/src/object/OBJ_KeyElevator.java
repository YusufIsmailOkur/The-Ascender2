package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import mainn.GamePanel;

public class OBJ_KeyElevator extends SuperObject{

    public GamePanel gp;
    public boolean hasGeneratedKey = false;
    
    public OBJ_KeyElevator(GamePanel gp){

        name = "keyelevator";
        craftable = false;
        this.gp = gp;

        try {

            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/elevator.png")); 
        } catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }

    public void interact(){
        boolean generateKey = true;
        for(int i = 0; i < gp.monster[gp.player.currentFloor].length && generateKey; i++){
            if(gp.monster[gp.player.currentFloor][i] != null){
                generateKey = false;
            }
        }
        if(generateKey && !hasGeneratedKey){
            gp.gameState = gp.dialogueState;
            
            gp.ui.currentDialogue = "A key has spawned!";
            hasGeneratedKey = true;
        }
        else if(hasGeneratedKey == true){

        }
        else{
            gp.gameState = gp.dialogueState;
            gp.ui.currentDialogue = "There are still monsters nearby!";
        }
    }
}
