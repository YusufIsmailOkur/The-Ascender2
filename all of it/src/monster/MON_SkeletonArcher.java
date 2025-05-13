package monster;

import entity.Entity;
import entity.Player;
import mainn.GamePanel;
import object.OBJ_Arrow;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;

public class MON_SkeletonArcher extends Entity {

    GamePanel gp;
    Player player;
    boolean arrowCooldown = false;
    int arrowCooldownCounter = 0;

    public MON_SkeletonArcher(GamePanel gp) {
        super(gp);
        this.gp = gp;
        this.player = gp.player;

        direction = "down";
        name = "Skeleton Archer";
        speed = 1;
        maxHealth = 8;
        health = maxHealth;
        damage = 3;
        type = 2;
        projectile = new OBJ_Arrow(gp);

        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/monster/skeleton_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/monster/skeleton_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/monster/skeleton_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/monster/skeleton_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/monster/skeleton_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/monster/skeleton_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/monster/skeleton_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/monster/skeleton_right_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAction() {
        actionLockCounter++;

        int dx = player.x - x;
        int dy = player.y - y;
        int range = gp.tileSize * 5;

        if ((Math.abs(dx) < range && Math.abs(dy) < gp.tileSize) ||
            (Math.abs(dy) < range && Math.abs(dx) < gp.tileSize)) {
            if (!arrowCooldown) {
                if (Math.abs(dx) > Math.abs(dy)) {
                    direction = dx > 0 ? "right" : "left";
                } else {
                    direction = dy > 0 ? "down" : "up";
                }

                projectile = new OBJ_Arrow(gp);
                projectile.set(x, y, direction, true, this);
                gp.projectiles.add(projectile);

                arrowCooldown = true;
                arrowCooldownCounter = 0;
            }
        }

        if (arrowCooldown) {
            arrowCooldownCounter++;
            if (arrowCooldownCounter >= 90) {
                arrowCooldown = false;
                arrowCooldownCounter = 0;
            }
        }

        if (actionLockCounter >= 120) {
            int i = new Random().nextInt(100);
            if (i < 25) direction = "up";
            else if (i < 50) direction = "down";
            else if (i < 75) direction = "left";
            else direction = "right";

            actionLockCounter = 0;
        }
    }

    
}
