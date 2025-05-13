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

        // gp.obj[0][2] = new OBJ_Chest(gp, "10");
        // gp.obj[0][2].x = 10 * gp.tileSize;
        // gp.obj[0][2].y = 7 * gp.tileSize;

        // gp.obj[0][3] = new OBJ_Chest(gp, "diamond sword");
        // gp.obj[0][3].x = 6 * gp.tileSize;
        // gp.obj[0][3].y = 7 * gp.tileSize;

        // gp.obj[0][4] = new OBJ_Chest(gp, "health potion");
        // gp.obj[0][4].x = 6 * gp.tileSize;
        // gp.obj[0][4].y = 9 * gp.tileSize;

        gp.obj[0][5] = new OBJ_Key();
        gp.obj[0][5].x = 6 * gp.tileSize;
        gp.obj[0][5].y = 6* gp.tileSize;

        // gp.obj [0] [2] is set in player class

        //1
        gp.obj[1][0] = new OBJ_Elevator();
        gp.obj[1][0].x = 19 * gp.tileSize;
        gp.obj[1][0].y = 3 * gp.tileSize;

        gp.obj[1][1] = new OBJ_Key();
        gp.obj[1][1].x = 18 * gp.tileSize;  
        gp.obj[1][1].y = 13  * gp.tileSize;    

        gp.obj[1][2] = new OBJ_Table(gp);
        gp.obj[1][2].x = 9 * gp.tileSize;
        gp.obj[1][2].y = 4 * gp.tileSize;

        gp.obj[1][3] = new OBJ_Statue1(gp);
        gp.obj[1][3].x = 3 * gp.tileSize;
        gp.obj[1][3].y = 11 * gp.tileSize;


        gp.obj[1][4] = new OBJ_Statue2(gp);
        gp.obj[1][4].x = 1 * gp.tileSize;
        gp.obj[1][4].y = 9 * gp.tileSize;


        gp.obj[1][5] = new OBJ_Statue3(gp);
        gp.obj[1][5].x = 3 * gp.tileSize;
        gp.obj[1][5].y = 7 * gp.tileSize;


        gp.obj[1][6] = new OBJ_Statue4(gp);
        gp.obj[1][6].x = 5 * gp.tileSize;
        gp.obj[1][6].y = 5 * gp.tileSize;


        gp.obj[1][7] = new OBJ_Statue5(gp);
        gp.obj[1][7].x = 7 * gp.tileSize;
        gp.obj[1][7].y = 7 * gp.tileSize;


        gp.obj[1][8] = new OBJ_Statue6(gp);
        gp.obj[1][8].x = 9 * gp.tileSize;
        gp.obj[1][8].y = 9 * gp.tileSize;


        gp.obj[1][9] = new OBJ_Statue7(gp);
        gp.obj[1][9].x = 7 * gp.tileSize;
        gp.obj[1][9].y = 11 * gp.tileSize;


        gp.obj[1][10] = new OBJ_Statue8(gp);
        gp.obj[1][10].x = 5 * gp.tileSize;
        gp.obj[1][10].y = 13 * gp.tileSize;

        gp.obj[1][11] = new OBJ_TestDoorClosed("yes");
        gp.obj[1][11].x = 18 * gp.tileSize;
        gp.obj[1][11].y = 12 * gp.tileSize;

        gp.obj[1][12] = new OBJ_TestDoorClosed("321");
        gp.obj[1][12].x = 18 * gp.tileSize;
        gp.obj[1][12].y = 10 * gp.tileSize;

        gp.obj[1][13] = new OBJ_TestDoorClosed("01548752");
        gp.obj[1][13].x = 18 * gp.tileSize;
        gp.obj[1][13].y = 8 * gp.tileSize;

        gp.obj[1][14] = new OBJ_TestDoorClosed("arachne");
        gp.obj[1][14].x = 18 * gp.tileSize;
        gp.obj[1][14].y = 6 * gp.tileSize;

        gp.obj[1][15] = new OBJ_Chest(gp, "screwdriver");
        gp.obj[1][15].x = 17 * gp.tileSize;
        gp.obj[1][15].y = 11 * gp.tileSize;

        gp.obj[1][16] = new OBJ_Chest(gp, "light");
        gp.obj[1][16].x = 17 * gp.tileSize;
        gp.obj[1][16].y = 9 * gp.tileSize;

        gp.obj[1][17] = new OBJ_Key();
        gp.obj[1][17].x = 16 * gp.tileSize;  
        gp.obj[1][17].y = 13  * gp.tileSize;   

        //2
        gp.obj[2][0] = new OBJ_Elevator();
        gp.obj[2][0].x = 19 * gp.tileSize;
        gp.obj[2][0].y = 4 * gp.tileSize;

        gp.obj[2][1] = new OBJ_ClosedElevator();
        gp.obj[2][1].x = 0 * gp.tileSize;
        gp.obj[2][1].y = 7 * gp.tileSize;

        gp.obj[2][2] = new OBJ_Key();
        gp.obj[2][2].x = 18 * gp.tileSize;  

        gp.obj[2][2].y = 2  * gp.tileSize;      

        //3    
        

        gp.obj[3][0] = new OBJ_ClosedElevator();
        gp.obj[3][0].x = 0 * gp.tileSize;
        gp.obj[3][0].y = 7 * gp.tileSize;

        gp.obj[3][1] = new OBJ_Elevator();
        gp.obj[3][1].x = 19 * gp.tileSize;
        gp.obj[3][1].y = 7 * gp.tileSize;


        //4

        gp.obj[3][2] = new OBJ_Chest(gp, "10");
        gp.obj[3][2].x = 7 * gp.tileSize;
        gp.obj[3][2].y = 7 * gp.tileSize;

        gp.obj[3][3] = new OBJ_Chest(gp, "diamond sword");
        gp.obj[3][3].x = 5 * gp.tileSize;
        gp.obj[3][3].y = 7 * gp.tileSize;

        gp.obj[3][4] = new OBJ_Chest(gp, "health potion");
        gp.obj[3][4].x = 6 * gp.tileSize;
        gp.obj[3][4].y = 7 * gp.tileSize;


        gp.obj[4][0] = new OBJ_ClosedElevator();
        gp.obj[4][0].x = 0 * gp.tileSize;
        gp.obj[4][0].y = 7 * gp.tileSize;

        gp.obj[4][1] =new OBJ_Elevator();
        gp.obj[4][1].x = 19 * gp.tileSize;
        gp.obj[4][1].y = 7 * gp.tileSize;


        gp.obj[4][2] = new OBJ_Key();
        gp.obj[4][2].x = 5 * gp.tileSize;
        gp.obj[4][2].y = 5* gp.tileSize;

        //5
        gp.obj[5][0] = new OBJ_ClosedElevator();
        gp.obj[5][0].x = 0 * gp.tileSize;
        gp.obj[5][0].y = 7 * gp.tileSize;

        gp.obj[5][1] = new OBJ_Elevator();
        gp.obj[5][1].x = 19 * gp.tileSize;
        gp.obj[5][1].y = 4* gp.tileSize;

        gp.obj[5][2] = new OBJ_Key();
        gp.obj[5][2].x = 18 * gp.tileSize;
        gp.obj[5][2].y = 7 * gp.tileSize;

    }
    public void setNPC(){
        String[] dialogue;
        int currentFloor = gp.player.currentFloor;

        gp.npc[0][0] = new NPC_OldMan(gp, 1);
        gp.npc[0][0].x = gp.tileSize * 10;
        gp.npc[0][0].y = gp.tileSize * 4;

        gp.npc[3][0] = new NPC_OldMan(gp , 2);
        gp.npc[3][0].x = gp.tileSize * 10;
        gp.npc[3][0].y = gp.tileSize * 4;
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


        //5
        gp.monster[5][0] = new MON_Zombie(gp);
        gp.monster[5][0].x = 8 * gp.tileSize;
        gp.monster[5][0].y = 1 * gp.tileSize;

        gp.monster[5][1] = new MON_Zombie(gp);
        gp.monster[5][1].x = 15 * gp.tileSize;
        gp.monster[5][1].y = 5 * gp.tileSize;

        gp.monster[5][2] = new MON_Zombie(gp);
        gp.monster[5][2].x = 11 * gp.tileSize;
        gp.monster[5][2].y = 5 * gp.tileSize;

        gp.monster[5][3] = new MON_Zombie(gp);
        gp.monster[5][3].x = 8 * gp.tileSize;
        gp.monster[5][3].y = 7 * gp.tileSize;

        gp.monster[5][4] = new MON_Zombie(gp);
        gp.monster[5][4].x = 6 * gp.tileSize;
        gp.monster[5][4].y = 11 * gp.tileSize;

        gp.monster[5][5] = new MON_SkeletonArcher(gp);
        gp.monster[5][5].x = 2 * gp.tileSize;
        gp.monster[5][5].y = 13 * gp.tileSize;

        gp.monster[5][6] = new MON_Zombie(gp);
        gp.monster[5][6].x = 6 * gp.tileSize;
        gp.monster[5][6].y = 11 * gp.tileSize;

        gp.monster[5][7] = new MON_Zombie(gp);
        gp.monster[5][7].x = 14 * gp.tileSize;
        gp.monster[5][7].y = 10 * gp.tileSize;

        gp.monster[5][8] = new MON_SkeletonArcher(gp);
        gp.monster[5][8].x = 16 * gp.tileSize;
        gp.monster[5][8].y = 10 * gp.tileSize;

        gp.monster[5][9] = new MON_SkeletonArcher(gp);
        gp.monster[5][9].x = 14 * gp.tileSize;
        gp.monster[5][9].y = 8 * gp.tileSize;
      
        gp.monster[4][0] = new MON_SlimeBoss(gp);
        gp.monster[4][0].x = gp.tileSize * 10;
        gp.monster[4][0].y = gp.tileSize * 7;

    }
    public void setWeapon(){
        // gp.weapon[0] = new WPN_Sword();
        // gp.weapon[0].x = 8 * gp.tileSize;
        // gp.weapon[0].y = 8 * gp.tileSize;
    }
}
