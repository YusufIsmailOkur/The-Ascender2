package mainn;

import entity.NPC_OldMan;
import monster.MON_GreenSlime;
import monster.MON_SkeletonArcher;
import monster.MON_SlimeBoss;
import monster.MON_Zombie;
import object.*;

import weapon.*;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
    int currentFloor = gp.player.currentFloor;

        // gp.obj[0][1] = new OBJ_Key();
        // gp.obj[0][1].x = 5 * gp.tileSize;
        // gp.obj[0][1].y = 5 * gp.tileSize;


        // gp.obj[0][0] = new OBJ_Door();
        // gp.obj[0][0].x = 11 * gp.tileSize;
        // gp.obj[0][0].y = 3 * gp.tileSize;
        gp.obj[0][0] = new OBJ_ClosedElevator();
        gp.obj[0][0].x = 0 * gp.tileSize;
        gp.obj[0][0].y = 7 * gp.tileSize;
        
        gp.obj[0][1] = new OBJ_Elevator();
        gp.obj[0][1].x = 19 * gp.tileSize;
        gp.obj[0][1].y = 7 * gp.tileSize;

        gp.obj[0][2] = new OBJ_Chest(gp, "10");
        gp.obj[0][2].x = 10 * gp.tileSize;
        gp.obj[0][2].y = 7 * gp.tileSize;

        // gp.obj [0] [2] is set in player class

        gp.obj[1][0] = new OBJ_Elevator();
        gp.obj[1][0].x = 19 * gp.tileSize;
        gp.obj[1][0].y = 7 * gp.tileSize;

        gp.obj[1][1] = new OBJ_Key();
        gp.obj[1][1].x = 11 * gp.tileSize;  
        gp.obj[1][1].y = 7  * gp.tileSize;      

        gp.obj[2][0] = new OBJ_Elevator();
        gp.obj[2][0].x = 19 * gp.tileSize;
        gp.obj[2][0].y = 4 * gp.tileSize;

        gp.obj[2][1] = new OBJ_ClosedElevator();
        gp.obj[2][1].x = 0 * gp.tileSize;
        gp.obj[2][1].y = 7 * gp.tileSize;

        gp.obj[2][2] = new OBJ_Key();
        gp.obj[2][2].x = 18 * gp.tileSize;  
        gp.obj[2][2].y = 2  * gp.tileSize;      

        gp.obj[3][0] = new OBJ_ClosedElevator();
        gp.obj[3][0].x = 0 * gp.tileSize;
        gp.obj[3][0].y = 7 * gp.tileSize;

        gp.obj[3][1] =new OBJ_Elevator();
        gp.obj[3][1].x = 19 * gp.tileSize;
        gp.obj[3][1].y = 7 * gp.tileSize;

        gp.obj[4][0] = new OBJ_ClosedElevator();
        gp.obj[4][0].x = 0 * gp.tileSize;
        gp.obj[4][0].y = 7 * gp.tileSize;
        
        gp.obj[4][1] = new OBJ_Elevator();
        gp.obj[4][1].x = 19 * gp.tileSize;
        gp.obj[4][1].y = 7 * gp.tileSize;

        gp.monster[4][2] = new MON_SkeletonArcher(gp);
        gp.monster[4][2].x = 5*gp.tileSize;
        gp.monster[4][2].y = 7*gp.tileSize;
    }
    public void setNPC(){
        int currentFloor = gp.player.currentFloor;

        gp.npc[0][0] = new NPC_OldMan(gp);
        gp.npc[0][0].x = gp.tileSize * 10;
        gp.npc[0][0].y = gp.tileSize * 4;
    }
    public void setMonster(){
        int currentFloor = gp.player.currentFloor;

        // gp.monster[0][0] = new MON_GreenSlime(gp);
        // gp.monster[0][0].x = gp.tileSize * 12;
        // gp.monster[0][0].y = gp.tileSize * 3;
        // gp.monster[0][1] = new MON_GreenSlime(gp);
        // gp.monster[0][1].x = gp.tileSize * 11;
        // gp.monster[0][1].y = gp.tileSize * 3;
        // gp.monster[0][2] = new MON_GreenSlime(gp);
        // gp.monster[0][2].x = gp.tileSize * 10;
        // gp.monster[0][2].y = gp.tileSize * 3;

        // gp.monster[0][3] = new MON_Zombie(gp);
        // gp.monster[0][3].x = gp.tileSize * 12;
        // gp.monster[0][3].y = gp.tileSize * 13;

        gp.monster[2][0] = new MON_GreenSlime(gp);
        gp.monster[2][0].x = gp.tileSize * 1;
        gp.monster[2][0].y = gp.tileSize * 1;
        gp.monster[2][1] = new MON_GreenSlime(gp);
        gp.monster[2][1].x = gp.tileSize * 3;
        gp.monster[2][1].y = gp.tileSize * 1;
        gp.monster[2][2] = new MON_GreenSlime(gp);
        gp.monster[2][2].x = gp.tileSize * 13;
        gp.monster[2][2].y = gp.tileSize * 2;
        gp.monster[2][3] = new MON_GreenSlime(gp);
        gp.monster[2][3].x = gp.tileSize * 8;
        gp.monster[2][3].y = gp.tileSize * 13;
        gp.monster[2][4] = new MON_GreenSlime(gp);
        gp.monster[2][4].x = gp.tileSize * 8;
        gp.monster[2][4].y = gp.tileSize * 9;
        gp.monster[2][5] = new MON_GreenSlime(gp);
        gp.monster[2][5].x = gp.tileSize * 11;
        gp.monster[2][5].y = gp.tileSize * 11;
        gp.monster[2][6] = new MON_GreenSlime(gp);
        gp.monster[2][6].x = gp.tileSize * 11;
        gp.monster[2][6].y = gp.tileSize * 11;
        gp.monster[2][7] = new MON_GreenSlime(gp);
        gp.monster[2][7].x = gp.tileSize * 13;
        gp.monster[2][7].y = gp.tileSize * 11;
        gp.monster[2][8] = new MON_GreenSlime(gp);
        gp.monster[2][8].x = gp.tileSize * 11;
        gp.monster[2][8].y = gp.tileSize * 5;

        gp.monster[3][0] = new MON_SlimeBoss(gp);
        gp.monster[3][0].x = gp.tileSize * 10;
        gp.monster[3][0].y = gp.tileSize * 7;
    }
    public void setWeapon(){
        gp.weapon[0] = new WPN_Sword();
        gp.weapon[0].x = 8 * gp.tileSize;
        gp.weapon[0].y = 8 * gp.tileSize;
    }
}
