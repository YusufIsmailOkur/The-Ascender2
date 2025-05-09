package mainn;

import entity.NPC_OldMan;
import monster.MON_GreenSlime;


import object.*;

import weapon.*;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
    int currentFloor = gp.player.currentFloor;

        gp.obj[0][1] = new OBJ_Key();
        gp.obj[0][1].x = 5 * gp.tileSize;
        gp.obj[0][1].y = 5 * gp.tileSize;


        gp.obj[0][0] = new OBJ_Door();
        gp.obj[0][0].x = 11 * gp.tileSize;
        gp.obj[0][0].y = 3 * gp.tileSize;
        gp.obj[0][2] = new OBJ_Elevator();
        gp.obj[0][2].x = 17 * gp.tileSize;
        gp.obj[0][2].y = 8 * gp.tileSize;

        
    }
    public void setNPC(){
        int currentFloor = gp.player.currentFloor;

        gp.npc[0][0] = new NPC_OldMan(gp);
        gp.npc[0][0].x = gp.tileSize * 9;
        gp.npc[0][0].y = gp.tileSize * 2;
    }
    public void setMonster(){
        int currentFloor = gp.player.currentFloor;

        gp.monster[currentFloor][0] = new MON_GreenSlime(gp);
        gp.monster[currentFloor][0].x = gp.tileSize * 12;
        gp.monster[currentFloor][0].y = gp.tileSize * 10;
        gp.monster[currentFloor][1] = new MON_GreenSlime(gp);
        gp.monster[currentFloor][1].x = gp.tileSize * 11;
        gp.monster[currentFloor][1].y = gp.tileSize * 11;
        gp.monster[currentFloor][2] = new MON_GreenSlime(gp);
        gp.monster[currentFloor][2].x = gp.tileSize * 10;
        gp.monster[currentFloor][2].y = gp.tileSize * 12;
        
    }
    public void setWeapon(){
        gp.weapon[0] = new WPN_Sword();
        gp.weapon[0].x = 8 * gp.tileSize;
        gp.weapon[0].y = 8 * gp.tileSize;
    }
}
