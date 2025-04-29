package entity;

import mainn.GamePanel;

public class Projectile extends Entity {

    public String name;
    public int life;
    public int maxLife;
    public int attack;
    public Entity user;

    public Projectile(GamePanel gp){
        super(gp);
    }

    public void set(int worldX, int worldY, String direction, boolean alive, Entity user){
        this.x = worldX;
        this.y = worldY;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.life = this.maxLife;
    }

    public void update(){

        if(user.equals(gp.player)){
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            if(monsterIndex != 999){
                gp.player.damageMonster(monsterIndex);
                this.alive = false;
            }
        }
        switch (direction) {
            case "up" : y -= speed; break;
            case "down" : y += speed; break;
            case "left" : x -= speed; break;
            case "right" : x += speed; break;
        }

        life--;
        if(life <= 0){
            this.alive = false;
        }

        spriteCounter++;
        if(spriteCounter > 12){
            if(spriteNumber == 1){
                spriteNumber = 2;
            }
            else if(spriteNumber == 2){
                spriteNumber = 1;
            }
        }
    }
}
