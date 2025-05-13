package monster;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import mainn.GamePanel;

public class MON_WallSummoner extends Entity {

    private GamePanel gp;
    private int summonCooldown = 0;
    private boolean preparingWall = false;
    private int prepareCounter = 0;
    private int targetTileX, targetTileY;
    private int prevTileX;
    private int prevTileY;

    public MON_WallSummoner(GamePanel gp) {
        super(gp);
        this.gp = gp;
        direction = "down";
        this.name = "Wamuu";
        this.speed = 0;
        this.maxHealth = 120;
        this.health = maxHealth;
        this.type = 2;
        this.isBoss = true;
        int size = gp.tileSize * 5;
        solidArea.x = 45;
        solidArea.y = 80;
        solidArea.width = size - gp.tileSize * 3 / 2;
        solidArea.height = size - gp.tileSize * 2;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        prevTileX = 8;
        prevTileY = 5;
        this.gp = gp;

        getMonsterImage();
    }

    public void getMonsterImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/monster/wamuu.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/monster/wamuu.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/monster/wamuu.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/monster/wamuu.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/monster/wamuu.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/monster/wamuu.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/monster/wamuu.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/monster/wamuu.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isSafeToReplace(int x, int y) {
        if (x < 0 || x >= gp.maxScreenCol || y < 0 || y >= gp.maxScreenRow) return false;
        int tileNum = gp.tileM.mapTileNum[x][y];
        return tileNum != 1;
    }

    @Override
    public void setAction() {
        if (!preparingWall && summonCooldown == 0) {
            targetTileX = gp.player.x / gp.tileSize;
            targetTileY = gp.player.y / gp.tileSize;

            for (int dx = 0; dx < 3; dx++) {
                for (int dy = 0; dy < 3; dy++) {
                    int x = targetTileX + dx;
                    int y = targetTileY + dy;
                    if (isSafeToReplace(x, y)) {
                        gp.tileM.mapTileNum[x][y] = 18;
                    }
                }
            }

            preparingWall = true;
            prepareCounter = 0;
        }

        if (preparingWall) {
            prepareCounter++;
            if (prepareCounter > 60) {
                for (int dx = 0; dx < 3; dx++) {
                    for (int dy = 0; dy < 3; dy++) {
                        int x = prevTileX + dx;
                        int y = prevTileY + dy;
                        if (isSafeToReplace(x, y)) {
                            gp.tileM.mapTileNum[x][y] = 5;
                        }
                    }
                }

                for (int dx = 0; dx < 3; dx++) {
                    for (int dy = 0; dy < 3; dy++) {
                        int x = targetTileX + dx;
                        int y = targetTileY + dy;
                        if (isSafeToReplace(x, y)) {
                            gp.tileM.mapTileNum[x][y] = 3;
                        }
                    }
                }

                // Damage player if inside lava
                int px = gp.player.x / gp.tileSize;
                int py = gp.player.y / gp.tileSize;
                for (int dx = 0; dx < 3; dx++) {
                    for (int dy = 0; dy < 3; dy++) {
                        if (px == targetTileX + dx && py == targetTileY + dy && !gp.player.invincibility) {
                            gp.player.health -= 5;
                            gp.player.invincibility = true;
                        }
                    }
                }

                // Save this area as previous for next time cleanup
                prevTileX = targetTileX;
                prevTileY = targetTileY;

                preparingWall = false;
                summonCooldown = 120;
            }
        }

        if (summonCooldown > 0) {
            summonCooldown--;
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNumber == 1) {
                    image = up1;
                }
                if (spriteNumber == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNumber == 1) {
                    image = down1;
                }
                if (spriteNumber == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNumber == 1) {
                    image = left1;
                }
                if (spriteNumber == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNumber == 1) {
                    image = right1;
                }
                if (spriteNumber == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, x, y, gp.tileSize * 5/2, gp.tileSize * 5, null);
    }

}