package mainn;

import entity.NPC_OldMan;
import monster.MON_GreenSlime;
import monster.MON_SkeletonArcher;

import monster.*;
import object.*;
import weapon.*;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){

        //0
        gp.obj[0][0] = new OBJ_ClosedElevator();
        gp.obj[0][0].x = 0 * gp.tileSize;
        gp.obj[0][0].y = 7 * gp.tileSize;
        
        gp.obj[0][1] = new OBJ_Elevator();
        gp.obj[0][1].x = 19 * gp.tileSize;
        gp.obj[0][1].y = 7 * gp.tileSize;

        //1
        gp.obj[1][0] = new OBJ_KeyElevator(gp);
        gp.obj[1][0].x = 19 * gp.tileSize;
        gp.obj[1][0].y = 7 * gp.tileSize;

        gp.obj[1][1] = new OBJ_Elevator();
        gp.obj[1][1].x = 0 * gp.tileSize;
        gp.obj[1][1].y = 7 * gp.tileSize;



        

        //2
        gp.obj[2][0] = new OBJ_Elevator();
        gp.obj[2][0].x = 19 * gp.tileSize;
        gp.obj[2][0].y = 4 * gp.tileSize;

        gp.obj[2][1] = new OBJ_KeyElevator(gp);
        gp.obj[2][1].x = 0 * gp.tileSize;
        gp.obj[2][1].y = 7 * gp.tileSize;
    

        //3    
        gp.obj[3][0] = new OBJ_ClosedElevator();
        gp.obj[3][0].x = 0 * gp.tileSize;
        gp.obj[3][0].y = 7 * gp.tileSize;

        gp.obj[3][1] = new OBJ_OpenedElevator();
        gp.obj[3][1].x = 19 * gp.tileSize;
        gp.obj[3][1].y = 7 * gp.tileSize;

        gp.obj[3][2] = new OBJ_Chest(gp, "10");
        gp.obj[3][2].x = 7 * gp.tileSize;
        gp.obj[3][2].y = 7 * gp.tileSize;
        gp.obj[3][3] = new OBJ_Chest(gp, "health potion");
        gp.obj[3][3].x = 6 * gp.tileSize;
        gp.obj[3][3].y = 7 * gp.tileSize;


        gp.obj[4][0] = new OBJ_ClosedElevator();
        gp.obj[4][0].x = 0 * gp.tileSize;
        gp.obj[4][0].y = 7 * gp.tileSize;

        gp.obj[4][1] = new OBJ_KeyElevator(gp);
        gp.obj[4][1].x = 19 * gp.tileSize;
        gp.obj[4][1].y = 7 * gp.tileSize;


       //5
       gp.obj[5][0] = new OBJ_Elevator();
       gp.obj[5][0].x = 19 * gp.tileSize;
       gp.obj[5][0].y = 4 * gp.tileSize;

       gp.obj[5][1] = new OBJ_ClosedElevator();
       gp.obj[5][1].x = 0 * gp.tileSize;
       gp.obj[5][1].y = 7 * gp.tileSize;

       gp.obj[5][2] = new OBJ_Key();
       gp.obj[5][2].x = 18 * gp.tileSize;  
       gp.obj[5][2].y = 2  * gp.tileSize; 

       //6
       gp.obj[6][0] = new OBJ_KeyElevator(gp);
       gp.obj[6][0].x = 19 * gp.tileSize;
       gp.obj[6][0].y = 7 * gp.tileSize;

       gp.obj[6][1] = new OBJ_ClosedElevator();
       gp.obj[6][1].x = 0 * gp.tileSize;
       gp.obj[6][1].y = 7 * gp.tileSize;
       
       
       
       //7
        gp.obj[7][0] = new OBJ_ClosedElevator();
        gp.obj[7][0].x = 0 * gp.tileSize;
        gp.obj[7][0].y = 7 * gp.tileSize;

        gp.obj[7][1] = new OBJ_Elevator();
        gp.obj[7][1].x = 19 * gp.tileSize;
        gp.obj[7][1].y = 7 * gp.tileSize;

        gp.obj[7][2] = new OBJ_Chest(gp, "10");
        gp.obj[7][2].x = 7 * gp.tileSize;
        gp.obj[7][2].y = 7 * gp.tileSize;
        gp.obj[7][3] = new OBJ_Chest(gp, "health potion");
        gp.obj[7][3].x = 6 * gp.tileSize;
        gp.obj[7][3].y = 7 * gp.tileSize;


        //8
        gp.obj[8][0] = new OBJ_KeyElevator(gp);
        gp.obj[8][0].x = 19 * gp.tileSize;
        gp.obj[8][0].y = 7 * gp.tileSize;

        gp.obj[8][1] = new OBJ_ClosedElevator();
        gp.obj[8][1].x = 0 * gp.tileSize;
        gp.obj[8][1].y = 7 * gp.tileSize;

       //9
       gp.obj[9][0] = new OBJ_ClosedElevator();
       gp.obj[9][0].x = 0 * gp.tileSize;
       gp.obj[9][0].y = 7 * gp.tileSize;

       gp.obj[9][1] = new OBJ_Elevator();
       gp.obj[9][1].x = 19 * gp.tileSize;
       gp.obj[9][1].y = 4* gp.tileSize;

       gp.obj[9][2] = new OBJ_Key();
       gp.obj[9][2].x = 18 * gp.tileSize;
       gp.obj[9][2].y = 7 * gp.tileSize;

       //10
       gp.obj[10][0] = new OBJ_Elevator();
       gp.obj[10][0].x = 19 * gp.tileSize;
       gp.obj[10][0].y = 3 * gp.tileSize;

       gp.obj[10][1] = new OBJ_Key();
       gp.obj[10][1].x = 18 * gp.tileSize;  
       gp.obj[10][1].y = 13  * gp.tileSize;    

       gp.obj[10][2] = new OBJ_Table(gp);
       gp.obj[10][2].x = 9 * gp.tileSize;
       gp.obj[10][2].y = 4 * gp.tileSize;

       gp.obj[10][3] = new OBJ_Statue1(gp);
       gp.obj[10][3].x = 3 * gp.tileSize;
       gp.obj[10][3].y = 10 * gp.tileSize;


       gp.obj[10][4] = new OBJ_Statue2(gp);
       gp.obj[10][4].x = 1 * gp.tileSize;
       gp.obj[10][4].y = 9 * gp.tileSize;


       gp.obj[10][5] = new OBJ_Statue3(gp);
       gp.obj[10][5].x = 3 * gp.tileSize;
       gp.obj[10][5].y = 7 * gp.tileSize;


       gp.obj[10][6] = new OBJ_Statue4(gp);
       gp.obj[10][6].x = 5 * gp.tileSize;
       gp.obj[10][6].y = 5 * gp.tileSize;


       gp.obj[10][7] = new OBJ_Statue5(gp);
       gp.obj[10][7].x = 7 * gp.tileSize;
       gp.obj[10][7].y = 7 * gp.tileSize;


       gp.obj[10][8] = new OBJ_Statue6(gp);
       gp.obj[10][8].x = 9 * gp.tileSize;
       gp.obj[10][8].y = 9 * gp.tileSize;


       gp.obj[10][9] = new OBJ_Statue7(gp);
       gp.obj[10][9].x = 7 * gp.tileSize;
       gp.obj[10][9].y = 10 * gp.tileSize;


       gp.obj[10][10] = new OBJ_Statue8(gp);
       gp.obj[10][10].x = 5 * gp.tileSize;
       gp.obj[10][10].y = 13 * gp.tileSize;

       gp.obj[10][10] = new OBJ_TestDoorClosed("yes");
       gp.obj[10][10].x = 18 * gp.tileSize;
       gp.obj[10][10].y = 12 * gp.tileSize;

       gp.obj[10][12] = new OBJ_TestDoorClosed("athena");
       gp.obj[10][12].x = 18 * gp.tileSize;
       gp.obj[10][12].y = 10 * gp.tileSize;

       gp.obj[10][13] = new OBJ_TestDoorClosed("01548752");
       gp.obj[10][13].x = 18 * gp.tileSize;
       gp.obj[10][13].y = 8 * gp.tileSize;

       gp.obj[10][14] = new OBJ_TestDoorClosed("arachne");
       gp.obj[10][14].x = 18 * gp.tileSize;
       gp.obj[10][14].y = 6 * gp.tileSize;

       gp.obj[10][15] = new OBJ_Chest(gp, "light");
       gp.obj[10][15].x = 17 * gp.tileSize;
       gp.obj[10][15].y = 10 * gp.tileSize;

       gp.obj[10][16] = new OBJ_Chest(gp, "screwdriver");
       gp.obj[10][16].x = 17 * gp.tileSize;
       gp.obj[10][16].y = 9 * gp.tileSize;

       gp.obj[10][17] = new OBJ_Key();
       gp.obj[10][17].x = 16 * gp.tileSize;  
       gp.obj[10][17].y = 13  * gp.tileSize;   
       
       gp.obj[10][18] = new OBJ_ClosedElevator();
       gp.obj[10][18].x = 0 * gp.tileSize;
       gp.obj[10][18].y = 7 * gp.tileSize;

       //11
 
        gp.obj[11][0] = new OBJ_ClosedElevator();
        gp.obj[11][0].x = 0 * gp.tileSize;
        gp.obj[11][0].y = 7 * gp.tileSize;

        gp.obj[11][1] = new OBJ_Elevator();
        gp.obj[11][1].x = 19 * gp.tileSize;
        gp.obj[11][1].y = 7 * gp.tileSize;

        gp.obj[11][2] = new OBJ_Chest(gp, "10");
        gp.obj[11][2].x = 7 * gp.tileSize;
        gp.obj[11][2].y = 7 * gp.tileSize;
        
        gp.obj[11][3] = new OBJ_Chest(gp, "health potion");
        gp.obj[11][3].x = 6 * gp.tileSize;
        gp.obj[11][3].y = 7 * gp.tileSize;

        //12
        gp.obj[11][0] = new OBJ_ClosedElevator();
        gp.obj[11][0].x = 0 * gp.tileSize;
        gp.obj[11][0].y = 7 * gp.tileSize;

        gp.obj[11][1] = new OBJ_KeyElevator(gp);
        gp.obj[11][1].x = 19 * gp.tileSize;
        gp.obj[11][1].y = 7 * gp.tileSize;

        //13
        gp.obj[11][0] = new OBJ_ClosedElevator();
        gp.obj[11][0].x = 0 * gp.tileSize;
        gp.obj[11][0].y = 7 * gp.tileSize;

        gp.obj[11][1] = new OBJ_KeyElevator(gp);
        gp.obj[11][1].x = 19 * gp.tileSize;
        gp.obj[11][1].y = 7 * gp.tileSize;

        //14
        gp.obj[14][0] = new OBJ_ClosedElevator();
        gp.obj[14][0].x = 0 * gp.tileSize;
        gp.obj[14][0].y = 7 * gp.tileSize;

        gp.obj[14][1] = new OBJ_Elevator();
        gp.obj[14][1].x = 19 * gp.tileSize;
        gp.obj[14][1].y = 7 * gp.tileSize;

        gp.obj[14][2] = new OBJ_Chest(gp, "10");
        gp.obj[14][2].x = 7 * gp.tileSize;
        gp.obj[14][2].y = 7 * gp.tileSize;
        
        gp.obj[14][3] = new OBJ_Chest(gp, "health potion");
        gp.obj[14][3].x = 6 * gp.tileSize;
        gp.obj[14][3].y = 7 * gp.tileSize;

        //15
        gp.obj[15][0] = new OBJ_ClosedElevator();
        gp.obj[15][0].x = 0 * gp.tileSize;
        gp.obj[15][0].y = 7 * gp.tileSize;

        gp.obj[15][1] = new OBJ_KeyElevator(gp);
        gp.obj[15][1].x = 19 * gp.tileSize;
        gp.obj[15][1].y = 7 * gp.tileSize;

        //16
        gp.obj[16][0] = new OBJ_ClosedElevator();
        gp.obj[16][0].x = 0 * gp.tileSize;
        gp.obj[16][0].y = 7 * gp.tileSize;

        gp.obj[16][1] = new OBJ_Elevator();
        gp.obj[16][1].x = 19 * gp.tileSize;
        gp.obj[16][1].y = 7 * gp.tileSize;

        gp.obj[16][2] = new OBJ_Chest(gp, "30");
        gp.obj[16][2].x = 7 * gp.tileSize;
        gp.obj[16][2].y = 7 * gp.tileSize;
        
        gp.obj[16][3] = new OBJ_Chest(gp, "health potion");
        gp.obj[16][3].x = 6 * gp.tileSize;
        gp.obj[16][3].y = 7 * gp.tileSize;

        gp.obj[16][4] = new OBJ_Chest(gp, "health potion");
        gp.obj[16][4].x = 5 * gp.tileSize;
        gp.obj[16][4].y = 7 * gp.tileSize;

        //17
        gp.obj[17][0] = new OBJ_ClosedElevator();
        gp.obj[17][0].x = 0 * gp.tileSize;
        gp.obj[17][0].y = 7 * gp.tileSize;

        gp.obj[17][1] = new OBJ_KeyElevator(gp);
        gp.obj[17][1].x = 19 * gp.tileSize;
        gp.obj[17][1].y = 7 * gp.tileSize;

    }
    public void setNPC(){
        gp.npc[0][0] = new NPC_OldMan(gp, 1);
        gp.npc[0][0].x = gp.tileSize * 10;
        gp.npc[0][0].y = gp.tileSize * 4;

        gp.npc[3][0] = new NPC_OldMan(gp , 2);
        gp.npc[3][0].x = gp.tileSize * 10;
        gp.npc[3][0].y = gp.tileSize * 4;
    }
    public void setMonster(){

        //1
        gp.monster[1][0] = new MON_GreenSlime(gp);
        gp.monster[1][0].x = gp.tileSize * 8;
        gp.monster[1][0].y = gp.tileSize * 8;
        gp.monster[1][1] = new MON_GreenSlime(gp);
        gp.monster[1][1].x = gp.tileSize * 8;
        gp.monster[1][1].y = gp.tileSize * 10;
        gp.monster[1][2] = new MON_GreenSlime(gp);
        gp.monster[1][2].x = gp.tileSize * 8;
        gp.monster[1][2].y = gp.tileSize * 12;
        
        //2
        gp.monster[2][0] = new MON_Zombie(gp);
        gp.monster[2][0].x = gp.tileSize * 5;
        gp.monster[2][0].y = gp.tileSize * 4;
        gp.monster[2][1] = new MON_Zombie(gp);
        gp.monster[2][1].x = gp.tileSize * 5;
        gp.monster[2][1].y = gp.tileSize * 15;
        gp.monster[2][2] = new MON_SkeletonArcher(gp);
        gp.monster[2][2].x = gp.tileSize * 14;
        gp.monster[2][2].y = gp.tileSize * 4;
        gp.monster[2][3] = new MON_SkeletonArcher(gp);
        gp.monster[2][3].x = gp.tileSize * 14;
        gp.monster[2][3].y = gp.tileSize * 15;

        //3

        //4
        gp.monster[4][0] = new MON_ZombieBoss(gp);
        gp.monster[4][0].x = gp.tileSize * 13;
        gp.monster[4][0].y = gp.tileSize * 2;
        gp.monster[4][1] = new MON_Zombie(gp);
        gp.monster[4][1].x = gp.tileSize * 8;
        gp.monster[4][1].y = gp.tileSize * 13;
        gp.monster[4][2] = new MON_Zombie(gp);
        gp.monster[4][2].x = gp.tileSize * 8;
        gp.monster[4][2].y = gp.tileSize * 13;

        //5
        gp.monster[5][0] = new MON_GreenSlime(gp);
        gp.monster[5][0].x = gp.tileSize * 1;
        gp.monster[5][0].y = gp.tileSize * 1;
        gp.monster[5][1] = new MON_GreenSlime(gp);
        gp.monster[5][1].x = gp.tileSize * 3;
        gp.monster[5][1].y = gp.tileSize * 1;
        gp.monster[5][2] = new MON_GreenSlime(gp);
        gp.monster[5][2].x = gp.tileSize * 13;
        gp.monster[5][2].y = gp.tileSize * 2;
        gp.monster[5][3] = new MON_GreenSlime(gp);
        gp.monster[5][3].x = gp.tileSize * 8;
        gp.monster[5][3].y = gp.tileSize * 13;
        gp.monster[5][4] = new MON_GreenSlime(gp);
        gp.monster[5][4].x = gp.tileSize * 8;
        gp.monster[5][4].y = gp.tileSize * 9;
        gp.monster[5][5] = new MON_GreenSlime(gp);
        gp.monster[5][5].x = gp.tileSize * 11;
        gp.monster[5][5].y = gp.tileSize * 11;
        gp.monster[5][6] = new MON_GreenSlime(gp);
        gp.monster[5][6].x = gp.tileSize * 11;
        gp.monster[5][6].y = gp.tileSize * 11;
        gp.monster[5][7] = new MON_GreenSlime(gp);
        gp.monster[5][7].x = gp.tileSize * 13;
        gp.monster[5][7].y = gp.tileSize * 11;
        gp.monster[5][8] = new MON_GreenSlime(gp);
        gp.monster[5][8].x = gp.tileSize * 11;
        gp.monster[5][8].y = gp.tileSize * 5;

        //6
        gp.monster[6][0] = new MON_Zombie(gp);
        gp.monster[6][0].x = gp.tileSize * 8;
        gp.monster[6][0].y = gp.tileSize * 7;
        gp.monster[6][1] = new MON_GreenSlime(gp);
        gp.monster[6][1].x = gp.tileSize * 5;
        gp.monster[6][1].y = gp.tileSize * 8;
        gp.monster[6][2] = new MON_SkeletonArcher(gp);
        gp.monster[6][2].x = gp.tileSize * 14;
        gp.monster[6][2].y = gp.tileSize * 7;
        gp.monster[6][3] = new MON_GreenSlime(gp);
        gp.monster[6][3].x = gp.tileSize * 5;
        gp.monster[6][3].y = gp.tileSize * 13;

        //7 (preperation room)

        //8 Slime boss
        gp.monster[8][0] = new MON_SlimeBoss(gp);
        gp.monster[8][0].x = 10 * gp.tileSize;
        gp.monster[8][0].y = 7 * gp.tileSize;

        //9
        gp.monster[9][0] = new MON_Zombie(gp);
        gp.monster[9][0].x = 8 * gp.tileSize;
        gp.monster[9][0].y = 1 * gp.tileSize;
        gp.monster[9][1] = new MON_Zombie(gp);
        gp.monster[9][1].x = 15 * gp.tileSize;
        gp.monster[9][1].y = 5 * gp.tileSize;
        gp.monster[9][2] = new MON_Zombie(gp);
        gp.monster[9][2].x = 11 * gp.tileSize;
        gp.monster[9][2].y = 5 * gp.tileSize;
        gp.monster[9][3] = new MON_Zombie(gp);
        gp.monster[9][3].x = 8 * gp.tileSize;
        gp.monster[9][3].y = 7 * gp.tileSize;
        gp.monster[9][4] = new MON_Zombie(gp);
        gp.monster[9][4].x = 6 * gp.tileSize;
        gp.monster[9][4].y = 11 * gp.tileSize;
        gp.monster[9][5] = new MON_SkeletonArcher(gp);
        gp.monster[9][5].x = 2 * gp.tileSize;
        gp.monster[9][5].y = 13 * gp.tileSize;
        gp.monster[9][6] = new MON_Zombie(gp);
        gp.monster[9][6].x = 6 * gp.tileSize;
        gp.monster[9][6].y = 11 * gp.tileSize;
        gp.monster[9][7] = new MON_Zombie(gp);
        gp.monster[9][7].x = 14 * gp.tileSize;
        gp.monster[9][7].y = 10 * gp.tileSize;
        gp.monster[9][8] = new MON_SkeletonArcher(gp);
        gp.monster[9][8].x = 16 * gp.tileSize;
        gp.monster[9][8].y = 10 * gp.tileSize;
        gp.monster[9][9] = new MON_SkeletonArcher(gp);
        gp.monster[9][9].x = 14 * gp.tileSize;
        gp.monster[9][9].y = 8 * gp.tileSize;

        //10 puzzle room

        //11 preperation room

        //12 skeleton boss
        gp.monster[12][0] = new MON_SkeletonArcher(gp);
        gp.monster[12][0].x = 10 * gp.tileSize;
        gp.monster[12][0].y = 7 * gp.tileSize;

        //13 iskelet slime zombi
        gp.monster[13][0] = new MON_GreenSlime(gp);
        gp.monster[13][0].x = 4*gp.tileSize;
        gp.monster[13][0].y = 7 * gp.tileSize;
        gp.monster[13][1] = new MON_GreenSlime(gp);
        gp.monster[13][1].x = 4*gp.tileSize;
        gp.monster[13][1].y = 14 * gp.tileSize;
        gp.monster[13][2] = new MON_Zombie(gp);
        gp.monster[13][2].x = 8*gp.tileSize;
        gp.monster[13][2].y = 10 * gp.tileSize;
        gp.monster[13][3] = new MON_Zombie(gp);
        gp.monster[13][3].x = 10*gp.tileSize;
        gp.monster[13][3].y = 10 * gp.tileSize;
        gp.monster[13][4] = new MON_SkeletonArcher(gp);
        gp.monster[13][3].x = 14 * gp.tileSize;
        gp.monster[13][3].y = 10 * gp.tileSize;

        //14 preperation room

        //15 Wamuu
        gp.monster[15][0] = new MON_WallSummoner(gp);
        gp.monster[15][0].x = 10 * gp.tileSize;
        gp.monster[15][0].y = 7 * gp.tileSize;

        //16 preparation room

        //17 final boss summoner
        gp.monster[17][0] = new MON_Summoner(gp, 17);
        gp.monster[17][0].x = 10*gp.tileSize; 
        gp.monster[17][0].y = 7*gp.tileSize;
                
    }
    public void setWeapon(){
        // gp.weapon[0] = new WPN_Sword();
        // gp.weapon[0].x = 8 * gp.tileSize;
        // gp.weapon[0].y = 8 * gp.tileSize;
    }
}
