package tile;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import mainn.GamePanel;
import mainn.UtilityTool;

public class TileManager {
    
    GamePanel gp;
    public Tile[] tile;

    public int mapTileNum[][];

    public TileManager(GamePanel gp){
        this.gp = gp;

        tile = new Tile[50];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
    
        getTileImage();
        loadMap("/res/maps/map1.txt");
    }

    public void getTileImage(){

        setup(0, "grass2", false);

        setup(1, "wall4", true);

        setup(2, "water", true);

        setup(3, "wall3", true);

        setup(4, "grass", false);

        setup(5, "dirtPath", false);

        //puzzle room
        setup(6, "greyTile", false);
        setup(7, "table_ground1", true);
        setup(8, "table_ground2", true);
        setup(9, "table_ground3", true);
        setup(10, "table_ground4", true);
        setup(11, "table_ground5", true);
        setup(12, "table_ground6", true);
        setup(13, "bottom", true);
        setup(14, "upper", true);
        setup(15, "random1", true);
        setup(16, "random2", true);
        setup(17, "random3", true);

    }
    public void setup(int index, String imageName, boolean collision){

        UtilityTool uTool = new UtilityTool();

        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath){

        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxScreenCol && row < gp.maxScreenRow){

                String line = br.readLine();

                while (col < gp.maxScreenCol){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch(Exception e){

        }
    }
    public void draw(Graphics2D g2){

        // g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
        // g2.drawImage(tile[1].image, 48, 0, gp.tileSize, gp.tileSize, null);
        // g2.drawImage(tile[2].image, 96, 0, gp.tileSize, gp.tileSize, null);

        int col = 0;
        int row = 0;
        int x = 0;
        int y =0;
        
        while (col < gp.maxScreenCol && row < gp.maxScreenRow){

            int tileNum = mapTileNum[col][row];
            
            g2.drawImage(tile[tileNum].image, x, y,null);
            col++;
            x += gp.tileSize;

            if (col == gp.maxScreenCol){
                col = 0;
                x = 0;
                row ++;
                y += gp.tileSize;
            }
        }
    }
}
