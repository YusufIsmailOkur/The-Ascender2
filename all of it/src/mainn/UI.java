package mainn;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.sql.Time;
import java.text.DecimalFormat;

import object.OBJ_Key;

public class UI {
    
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    // BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int menuNum= 0;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI (GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        // OBJ_Key key = new OBJ_Key();
        // keyImage = key.image;

    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){

        this.g2 = g2;        
        g2.setFont(arial_40);
        g2.setColor(Color.white);

        // TİTLE STATE
        if (gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        //PLAY STATE
        if (gp.gameState == gp.playState){
            // DO playstate stuff
        }
        // PAUSE STATE
        if (gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
        // DIALOGUE STATE
        if (gp.gameState == gp.dialogueState){
            drawDialogueScreen();
        }
        // MENU STATE
        if (gp.gameState == gp.menuState){
            drawMenuScreen();
        }
    }
    public void drawTitleScreen(){

        g2.setColor(new Color(70,120,180));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);


        // TİTLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 120F));
        String text = "The Ascender";
        int x = getXForCenteredText(text);
        int y = gp.tileSize*4;

        //SHADOW
        g2.setColor(Color.black);
        g2.drawString(text, x+5, y+5);

        // MAIN COLOR
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        //BLUE BOY IMAGE
        x = gp.screenWidth/2 - gp.tileSize*3/2;
        y += gp.tileSize*1.5;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize*3, gp.tileSize*3, null);

        // MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50F));

        text = "New Game";
        x = getXForCenteredText(text);
        y += gp.tileSize*4.5;
        g2.setColor(Color.black);
        g2.drawString(text, x+5, y+5);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        if (commandNum == 0){
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "Load Game";
        x = getXForCenteredText(text);
        y += gp.tileSize*1.3;
        g2.setColor(Color.black);
        g2.drawString(text, x+5, y+5);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        if (commandNum == 1){
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "Exit";
        x = getXForCenteredText(text);
        y += gp.tileSize*1.3;
        g2.setColor(Color.black);
        g2.drawString(text, x+5, y+5);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        if (commandNum == 2){
            g2.drawString(">", x-gp.tileSize, y);
        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 38F));
        text = "Settings";
        x = gp.tileSize*4/5;
        y = gp.tileSize*14;
        g2.setColor(Color.black);
        g2.drawString(text, x+5, y+5);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
    }
    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXForCenteredText(text);

        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }
    public void drawMenuScreen(){
        int x = gp.tileSize * 13/2;
        int y = gp.tileSize * 2;
        int width = gp.screenWidth - (gp.tileSize * 12);
        int height = gp.tileSize * 10;

        drawSubWindow(x, y, width, height);
        
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));

        x += gp.tileSize;
        y += gp.tileSize * 5/3;

        g2.drawString("Return to the Game", x, y);
        if (menuNum == 0){
            g2.drawString(">", x-gp.tileSize/2, y);
        }
        y += 60;
        
        g2.drawString("Fast travel", x, y);
        if (menuNum == 1){
            g2.drawString(">", x-gp.tileSize/2, y);
        }
        y += 60;
        g2.drawString("Leaderboard", x, y);
        if (menuNum == 2){
            g2.drawString(">", x-gp.tileSize/2, y);
        }
        y += 60;
        g2.drawString("Settings", x, y);
        if (menuNum == 3){
            g2.drawString(">", x-gp.tileSize/2, y);
        }
        y += 60;
        g2.drawString("Help", x, y);
        if (menuNum == 4){
            g2.drawString(">", x-gp.tileSize/2, y);
        }
        y += 60;
        g2.drawString("Exit", x, y);
        if (menuNum == 5){
            g2.drawString(">", x-gp.tileSize/2, y);
        }
    }
    public void drawDialogueScreen(){

        //WINDOW
        int x = gp.tileSize * 3;
        int y = gp.tileSize /2;
        int width = gp.screenWidth - (gp.tileSize* 6);
        int height = gp.tileSize * 3;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 23F));

        x += gp.tileSize*2/3;
        y += gp.tileSize*2/3;

        for (String line : currentDialogue.split("\n")){
            
            g2.drawString(line, x, y);
            y += 30;
        }

    }
    public void drawSubWindow(int x,int y,int width,int height){

        Color c = new Color(0,0,0,200);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(4));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);

    }

    public int getXForCenteredText(String text){
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }

}
