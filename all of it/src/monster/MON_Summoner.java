package monster;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import entity.Player;
import java.util.Random;
import mainn.GamePanel;

public class MON_Summoner extends Entity{

    public Random rand = new Random();
    public GamePanel gp;
    public Player player;
    public int usedFloor;
    public boolean isImmune = false;
    public boolean[] phases = new boolean[10];
    public MON_Summoner(GamePanel gp, int usedFloor) {
        super(gp);
        
        direction = "down";
        name = "Summoner";
        speed = 0;
        maxHealth = 500;
        this.gp = gp;
        isBoss = true;
        health = maxHealth;
        type = 2;
        this.usedFloor = usedFloor;
        damage = 1;

        solidArea.x = 48;
        solidArea.y = 64;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        this.player = gp.player;

        for(int i = 0; i < 10; i++){
            phases[i] = false;
        }

        getMonsterImage();
    }

    public void getMonsterImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/monster/summoner.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/monster/summoner.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/monster/summoner.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/monster/summoner.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/monster/summoner.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/monster/summoner.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/monster/summoner.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/monster/summoner.png"));

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void setAction(){
        if(health <= 450 && phases[0] == false){
            gp.monster[usedFloor][1] = new MON_GreenSlime(gp);
            gp.monster[usedFloor][1].x = gp.tileSize * rand.nextInt(2,18);
            gp.monster[usedFloor][1].y = gp.tileSize * rand.nextInt(2,12);
            gp.monster[usedFloor][2] = new MON_GreenSlime(gp);
            gp.monster[usedFloor][2].x = gp.tileSize * rand.nextInt(2,18);
            gp.monster[usedFloor][2].y = gp.tileSize * rand.nextInt(2,12);
            gp.monster[usedFloor][3] = new MON_GreenSlime(gp);
            gp.monster[usedFloor][3].x = gp.tileSize * rand.nextInt(2,18);
            gp.monster[usedFloor][3].y = gp.tileSize * rand.nextInt(2,12);

            phases[0] = true;
        }
        else if(health <= 400 && phases[1] == false){
            gp.monster[usedFloor][4] = new MON_GreenSlime(gp);
            gp.monster[usedFloor][4].x = gp.tileSize * rand.nextInt(2,18);
            gp.monster[usedFloor][4].y = gp.tileSize * rand.nextInt(2,12);
            gp.monster[usedFloor][5] = new MON_GreenSlime(gp);
            gp.monster[usedFloor][5].x = gp.tileSize * rand.nextInt(2,18);
            gp.monster[usedFloor][5].y = gp.tileSize * rand.nextInt(2,12);
            gp.monster[usedFloor][6] = new MON_GreenSlime(gp);
            gp.monster[usedFloor][6].x = gp.tileSize * rand.nextInt(2,18);
            gp.monster[usedFloor][6].y = gp.tileSize * rand.nextInt(2,12);
            gp.monster[usedFloor][7] = new MON_Zombie(gp);
            gp.monster[usedFloor][7].x = gp.tileSize * rand.nextInt(2,18);
            gp.monster[usedFloor][7].y = gp.tileSize * rand.nextInt(2,12);
            phases[1] = true;
        }
        else if(health <= 350&& phases[2] == false){
            gp.monster[usedFloor][8] = new MON_Zombie(gp);
            gp.monster[usedFloor][8].x = gp.tileSize * rand.nextInt(2,18);
            gp.monster[usedFloor][8].y = gp.tileSize * rand.nextInt(2,12);
            gp.monster[usedFloor][9] = new MON_SkeletonArcher(gp);
            gp.monster[usedFloor][9].x = gp.tileSize * rand.nextInt(2,18);
            gp.monster[usedFloor][9].y = gp.tileSize * rand.nextInt(2,12);
            gp.monster[usedFloor][10] = new MON_SkeletonArcher(gp);
            gp.monster[usedFloor][10].x = gp.tileSize * rand.nextInt(2,18);
            gp.monster[usedFloor][10].y = gp.tileSize * rand.nextInt(2,12);
            gp.monster[usedFloor][11] = new MON_GreenSlime(gp);
            gp.monster[usedFloor][11].x = gp.tileSize * rand.nextInt(2,18);
            gp.monster[usedFloor][11].y = gp.tileSize * rand.nextInt(2,12);
            gp.monster[usedFloor][12] = new MON_Zombie(gp);
            gp.monster[usedFloor][12].x = gp.tileSize * rand.nextInt(2,18);
            gp.monster[usedFloor][12].y = gp.tileSize * rand.nextInt(2,12);

            phases[2] = true;
        }
        else if(health <= 300 && phases[3] == false){
            gp.monster[usedFloor][13] = new MON_Zombie(gp);
            gp.monster[usedFloor][13].x = gp.tileSize * rand.nextInt(2,18);
            gp.monster[usedFloor][13].y = gp.tileSize * rand.nextInt(2,12);
            gp.monster[usedFloor][14] = new MON_SkeletonArcher(gp);
            gp.monster[usedFloor][14].x = gp.tileSize * rand.nextInt(2,18);
            gp.monster[usedFloor][14].y = gp.tileSize * rand.nextInt(2,12);
            gp.monster[usedFloor][15] = new MON_SkeletonArcher(gp);
            gp.monster[usedFloor][15].x = gp.tileSize * rand.nextInt(2,18);
            gp.monster[usedFloor][15].y = gp.tileSize * rand.nextInt(2,12);
            gp.monster[usedFloor][16] = new MON_GreenSlime(gp);
            gp.monster[usedFloor][16].x = gp.tileSize * rand.nextInt(2,18);
            gp.monster[usedFloor][16].y = gp.tileSize * rand.nextInt(2,12);
            gp.monster[usedFloor][17] = new MON_Zombie(gp);
            gp.monster[usedFloor][17].x = gp.tileSize * rand.nextInt(2,18);
            gp.monster[usedFloor][17].y = gp.tileSize * rand.nextInt(2,12);
            
            phases[3] = true;
        }
        else if(health <= 250&& phases[4] == false){
            gp.monster[usedFloor][18] = new MON_Zombie(gp);
            gp.monster[usedFloor][18].x = gp.tileSize * rand.nextInt(2,18);
            gp.monster[usedFloor][18].y = gp.tileSize * rand.nextInt(2,12);
            gp.monster[usedFloor][19] = new MON_SkeletonArcher(gp);
            gp.monster[usedFloor][19].x = gp.tileSize * rand.nextInt(2,18);
            gp.monster[usedFloor][19].y = gp.tileSize * rand.nextInt(2,12);
            gp.monster[usedFloor][20] = new MON_SkeletonArcher(gp);
            gp.monster[usedFloor][20].x = gp.tileSize * rand.nextInt(2,18);
            gp.monster[usedFloor][20].y = gp.tileSize * rand.nextInt(2,12);
            gp.monster[usedFloor][21] = new MON_GreenSlime(gp);
            gp.monster[usedFloor][21].x = gp.tileSize * rand.nextInt(2,18);
            gp.monster[usedFloor][21].y = gp.tileSize * rand.nextInt(2,12);
            gp.monster[usedFloor][22] = new MON_Zombie(gp);
            gp.monster[usedFloor][22].x = gp.tileSize * rand.nextInt(2,18);
            gp.monster[usedFloor][22].y = gp.tileSize * rand.nextInt(2,12);
            
            phases[4] = true;
        }
        else if(health <= 200&& phases[5] == false){
            gp.monster[usedFloor][22] = new MON_Zombie(gp);
            gp.monster[usedFloor][22].x = gp.tileSize * rand.nextInt(2,18);
            gp.monster[usedFloor][22].y = gp.tileSize * rand.nextInt(2,12);
            gp.monster[usedFloor][23] = new MON_SkeletonArcher(gp);
            gp.monster[usedFloor][23].x = gp.tileSize * rand.nextInt(2,18);
            gp.monster[usedFloor][23].y = gp.tileSize * rand.nextInt(2,12);
            gp.monster[usedFloor][24] = new MON_SkeletonArcher(gp);
            gp.monster[usedFloor][24].x = gp.tileSize * rand.nextInt(2,18);
            gp.monster[usedFloor][24].y = gp.tileSize * rand.nextInt(2,12);
            gp.monster[usedFloor][25] = new MON_GreenSlime(gp);
            gp.monster[usedFloor][25].x = gp.tileSize * rand.nextInt(2,18);
            gp.monster[usedFloor][25].y = gp.tileSize * rand.nextInt(2,12);
            gp.monster[usedFloor][26] = new MON_Zombie(gp);
            gp.monster[usedFloor][26].x = gp.tileSize * rand.nextInt(2,18);
            gp.monster[usedFloor][26].y = gp.tileSize * rand.nextInt(2,12);
            gp.monster[usedFloor][27] = new MON_Zombie(gp);
            gp.monster[usedFloor][27].x = gp.tileSize * rand.nextInt(2,18);
            gp.monster[usedFloor][27].y = gp.tileSize * rand.nextInt(2,12);
        
            phases[5] = true;
        }
        else if(health <= 150&& phases[6] == false){
            gp.monster[usedFloor][28] = new MON_SkeletonArcher(gp);
            gp.monster[usedFloor][28].x = gp.tileSize * rand.nextInt(2,18);
            gp.monster[usedFloor][28].y = gp.tileSize * rand.nextInt(2,12);
            gp.monster[usedFloor][29] = new MON_SkeletonArcher(gp);
            gp.monster[usedFloor][29].x = gp.tileSize * rand.nextInt(2,18);
            gp.monster[usedFloor][29].y = gp.tileSize * rand.nextInt(2,12);
            gp.monster[usedFloor][30] = new MON_GreenSlime(gp);
            gp.monster[usedFloor][30].x = gp.tileSize * rand.nextInt(2,18);
            gp.monster[usedFloor][30].y = gp.tileSize * rand.nextInt(2,12);
            gp.monster[usedFloor][31] = new MON_Zombie(gp);
            gp.monster[usedFloor][31].x = gp.tileSize * rand.nextInt(2,18);
            gp.monster[usedFloor][31].y = gp.tileSize * rand.nextInt(2,12);
            gp.monster[usedFloor][32] = new MON_Zombie(gp);
            gp.monster[usedFloor][32].x = gp.tileSize * rand.nextInt(2,18);
            gp.monster[usedFloor][32].y = gp.tileSize * rand.nextInt(2,12);
            
            phases[6] = true;
        }
        else if(health <= 100&& phases[7] == false){
            gp.monster[usedFloor][33] = new MON_SlimeBoss(gp);
            gp.monster[usedFloor][33].x = gp.tileSize * rand.nextInt(2,18);
            gp.monster[usedFloor][33].y = gp.tileSize * rand.nextInt(2,12);
            gp.monster[usedFloor][34] = new MON_GreenSlime(gp);
            gp.monster[usedFloor][34].x = gp.tileSize * rand.nextInt(2,18);
            gp.monster[usedFloor][34].y = gp.tileSize * rand.nextInt(2,12);
            gp.monster[usedFloor][35] = new MON_GreenSlime(gp);
            gp.monster[usedFloor][35].x = gp.tileSize * rand.nextInt(2,18);
            gp.monster[usedFloor][35].y = gp.tileSize * rand.nextInt(2,12);

            phases[7] = true;
        }
        else if(health <= 50&& phases[8] == false){
            gp.monster[usedFloor][36] = new MON_Zombie(gp);
            gp.monster[usedFloor][36].x = gp.tileSize * rand.nextInt(2,18);
            gp.monster[usedFloor][36].y = gp.tileSize * rand.nextInt(2,12);
            gp.monster[usedFloor][37] = new MON_Zombie(gp);
            gp.monster[usedFloor][37].x = gp.tileSize * rand.nextInt(2,18);
            gp.monster[usedFloor][37].y = gp.tileSize * rand.nextInt(2,12);
            gp.monster[usedFloor][38] = new MON_Zombie(gp);
            gp.monster[usedFloor][38].x = gp.tileSize * rand.nextInt(2,18);
            gp.monster[usedFloor][38].y = gp.tileSize * rand.nextInt(2,12);
            gp.monster[usedFloor][39] = new MON_ZombieBoss(gp);
            gp.monster[usedFloor][39].x = gp.tileSize * rand.nextInt(2,18);
            gp.monster[usedFloor][39].y = gp.tileSize * rand.nextInt(2,12);

            phases[8] = true;
        }
        else if(health <= 0 && phases[9] == false){
            gp.monster[usedFloor][40] = new MON_SkeletonArcher(gp);
            gp.monster[usedFloor][40].x = gp.tileSize * rand.nextInt(2,18);
            gp.monster[usedFloor][40].y = gp.tileSize * rand.nextInt(2,12);
            gp.monster[usedFloor][41] = new MON_SkeletonArcher(gp);
            gp.monster[usedFloor][41].x = gp.tileSize * rand.nextInt(2,18);
            gp.monster[usedFloor][41].y = gp.tileSize * rand.nextInt(2,12);
            gp.monster[usedFloor][42] = new MON_SkeletonArcherBoss(gp);
            gp.monster[usedFloor][42].x = gp.tileSize * rand.nextInt(2,18);
            gp.monster[usedFloor][42].y = gp.tileSize * rand.nextInt(2,12);

            phases[9] = true;
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
        g2.drawImage(image, x, y, gp.tileSize * 3, gp.tileSize * 3, null);
        }
}
