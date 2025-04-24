package mainn;

import entity.NPC_OldMan;
import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;
import weapon.*;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){

        gp.obj[1] = new OBJ_Key();
        gp.obj[1].x = 5 * gp.tileSize;
        gp.obj[1].y = 5 * gp.tileSize;

        gp.obj[0] = new OBJ_Door();
        gp.obj[0].x = 11 * gp.tileSize;
        gp.obj[0].y = 3 * gp.tileSize;
    }
    public void setNPC(){
        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].x = gp.tileSize * 9;
        gp.npc[0].y = gp.tileSize * 2;
    }
    public void setWeapon(){
        gp.weapon[0] = new WPN_Sword();
        gp.weapon[0].x = 8 * gp.tileSize;
        gp.weapon[0].y = 8 * gp.tileSize;
    }
}
