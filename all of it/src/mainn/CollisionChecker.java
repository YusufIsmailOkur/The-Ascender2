package mainn;
import entity.*;

public class CollisionChecker {
    
    GamePanel gp;

    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }
    public void checkTile(Entity entity){

        int entityLeftX = entity.x + entity.solidArea.x;
        int entityRightX = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTopY = entity.y + entity.solidArea.y;
        int entityBottomY = entity.y + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftX / gp.tileSize;
        int entityRightCol = entityRightX / gp.tileSize;
        int entityTopRow = entityTopY / gp.tileSize;
        int entityBottomRow = entityBottomY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction){
            case "up":
            entityTopRow = (entityTopY - entity.speed) / gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
            if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                entity.collisionOn = true;
            }
            break;

            case "down":
            entityBottomRow = (entityBottomY + entity.speed) / gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
            if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                entity.collisionOn = true;
            }
            break;

            case "left": 
            entityLeftCol = (entityLeftX - entity.speed) / gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
            if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                entity.collisionOn = true;
            }
            break;

            case "right":
            entityRightCol = (entityRightX + entity.speed) / gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
            if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                entity.collisionOn = true;
            }
            break;
        }

    }
    public int checkObject(Entity entity, boolean player) {

        int index = 999;

        for (int i = 0; i < gp.obj.length; i++){
            if(gp.obj[i] != null){
                //get entity's solid area position
                entity.solidArea.x = entity.x + entity.solidArea.x;
                entity.solidArea.y = entity.y + entity.solidArea.y;

                //get the object's solid area position
                gp.obj[i].solidArea.x = gp.obj[i].x + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].y + gp.obj[i].solidArea.y;

                switch(entity.direction){
                    case "up":
                    entity.solidArea.y -= entity.speed;
                    if (entity.solidArea.intersects(gp.obj[i].solidArea)){
                        if (gp.obj[i].collision == true){
                            entity.collisionOn = true;
                        }
                        if (player == true){
                            index = i;
                        }
                    }
                    break;
                    case "down":
                    entity.solidArea.y += entity.speed;
                    if (entity.solidArea.intersects(gp.obj[i].solidArea)){
                        if (gp.obj[i].collision == true){
                            entity.collisionOn = true;
                        }
                        if (player == true){
                            index = i;
                        }
                    }
                    break;
                    case "left":
                    entity.solidArea.x -= entity.speed;
                    if (entity.solidArea.intersects(gp.obj[i].solidArea)){
                        if (gp.obj[i].collision = true){
                            entity.collisionOn = true;
                        }
                        if (player == true){
                            index = i;
                        }
                    }
                    break;
                    case "right": 
                    entity.solidArea.x += entity.speed;
                    if (entity.solidArea.intersects(gp.obj[i].solidArea)){
                        if (gp.obj[i].collision == true){
                            entity.collisionOn = true;
                        }
                        if (player == true){
                            index = i;
                        }
                    }
                    break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }

        return index;
    }
    // NPC OR MONSTER COLLİSİON
    public int checkEntity(Entity entity, Entity[] target) {
        int index = 999;
    
        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) {
                // Get solid area positions
                entity.solidArea.x = entity.x + entity.solidAreaDefaultX;
                entity.solidArea.y = entity.y + entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].x + target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].y + target[i].solidAreaDefaultY;
    
                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        break;
                }
    
                if (entity.solidArea.intersects(target[i].solidArea)) {
                    entity.collisionOn = true;
                    index = i;
                }
    
                // Reset positions
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }
    
        return index;
    }
    public boolean checkPlayer(Entity entity){

        boolean contact = false;
        // Get solid area positions
        entity.solidArea.x = entity.x + entity.solidAreaDefaultX;
        entity.solidArea.y = entity.y + entity.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.x + gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.y + gp.player.solidAreaDefaultY;

        switch (entity.direction) {
            case "up":
                entity.solidArea.y -= entity.speed;
                break;
            case "down":
                entity.solidArea.y += entity.speed;
                break;
            case "left":
                entity.solidArea.x -= entity.speed;
                break;
            case "right":
                entity.solidArea.x += entity.speed;
                break;
        }

        if (entity.solidArea.intersects(gp.player.solidArea)) {
            entity.collisionOn = true;
            contact = true;
        }
        // Reset positions
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        return contact;
    }
}
