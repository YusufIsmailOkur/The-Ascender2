package mainn;

import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import entity.*;
import java.awt.*;

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
    public int menuNum = 0;
    public int settingsNum = 0;
    public int deathScreenNum = 0;
    public int musicLevel = 8;
    public boolean isSetup = false; 

    int storyCharIndex = 0;
    int storyCounter = 0;
    boolean storyFinished = false;
    int waitAfterStory = 120; // 2 saniye (60 FPS üzerinden)
    String[] storyLines = {
        "They said it was for progress... for a better world.",
        "But the experiments went too far—tampering with nature, rewriting the laws of physics.",
        "Now the sun scorches the earth, and oceans boil into the sky.",
        "The world has burned under the weight of ambition.",
        "You awaken in one of the last surviving research facilities… alone, surrounded by silence.",
        "Something draws you upward.",
        "Answers lie above… or maybe, monsters born of them."
    };

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        // OBJ_Key key = new OBJ_Key();
        // keyImage = key.image;

    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.white);

        // TİTLE STATE
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();

        }
        // PLAY STATE
        if (gp.gameState == gp.playState) {
            drawHealthBar();
            drawManaBar();
            drawWeaponSlots();
            if(isSetup && gp.player.currentFloor != 5 && gp.player.currentFloor != 9){
                drawMonsterHealthBar();
            }
        }
        // PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
        // DIALOGUE STATE
        if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
        }
        // MENU STATE
        if (gp.gameState == gp.menuState) {
            drawMenuScreen();
        }
        if (gp.gameState == gp.helpState) {
            drawHelpScreen();
        }
        if (gp.gameState == gp.settingsState) {
            drawSettingsScreen();
        }
        if (gp.gameState == gp.deathState){
            drawDeathScreen();
        }
        if (gp.gameState == gp.storyState){
            drawStoryScreen(g2);
        }
    }

    public void drawTitleScreen() {

        g2.setColor(new Color(70, 120, 180));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // TİTLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 120F));
        String text = "The Ascender";
        int x = getXForCenteredText(text);
        int y = gp.tileSize * 4;

        // SHADOW
        g2.setColor(Color.black);
        g2.drawString(text, x + 5, y + 5);

        // MAIN COLOR
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        // BLUE BOY IMAGE
        x = gp.screenWidth / 2 - gp.tileSize * 3 / 2;
        y += gp.tileSize * 1.5;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize * 3, gp.tileSize * 3, null);

        // MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50F));

        text = "New Game";
        x = getXForCenteredText(text);
        y += gp.tileSize * 4.5;
        g2.setColor(Color.black);
        g2.drawString(text, x + 5, y + 5);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }
        

        text = "Load Game";
        x = getXForCenteredText(text);
        y += gp.tileSize * 1.3;
        g2.setColor(Color.black);
        g2.drawString(text, x + 5, y + 5);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "Leaderboard";
        x = getXForCenteredText(text);
        y += gp.tileSize * 1.3;
        g2.setColor(Color.black);
        g2.drawString(text, x + 5, y + 5);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "Exit Game";
        x = getXForCenteredText(text);
        y += gp.tileSize * 1.3;
        g2.setColor(Color.black);
        g2.drawString(text, x + 5, y + 5);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        if (commandNum == 3) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        

        // g2.setFont(g2.getFont().deriveFont(Font.BOLD, 38F));
        // text = "Settings";
        // x = gp.tileSize * 4 / 5;
        // y = gp.tileSize * 14;
        // g2.setColor(Color.black);
        // g2.drawString(text, x + 5, y + 5);
        // g2.setColor(Color.white);
        // g2.drawString(text, x, y);
    }

    public void drawStoryScreen(Graphics2D g2) {
        // Arka planı tamamen siyah yap
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
    
        // Yazı ayarları
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 22F));
    
        int x = 50;
        int y = 80; // üstten biraz erken başlasın
        int lineSpacing = 35;
    
        for (int i = 0; i < storyLines.length; i++) {
            String fullLine = storyLines[i];
    
            if (i < storyCharIndex) {
                g2.setColor(i == storyLines.length - 1 ? Color.RED : Color.WHITE);
                g2.drawString(fullLine, x, y + i * lineSpacing);
            } else if (i == storyCharIndex) {
                int charsToShow = storyCounter / 3;
                if (charsToShow > fullLine.length()) charsToShow = fullLine.length();
    
                String partial = fullLine.substring(0, charsToShow);
                g2.setColor(i == storyLines.length - 1 ? Color.RED : Color.WHITE);
                g2.drawString(partial, x, y + i * lineSpacing);
    
                if (charsToShow == fullLine.length()) {
                    storyCharIndex++;
                    storyCounter = 0;
                    if (storyCharIndex == storyLines.length) {
                        storyFinished = true;
                    }
                }
                break;
            }
        }
    
        if (!storyFinished) {
            storyCounter++;
        } else {
            waitAfterStory--;
            if (waitAfterStory <= 0) {
                gp.gameState = gp.playState;
            }
        }
    }
    


    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXForCenteredText(text);

        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
    }

    public void drawMenuScreen() {
        int x = gp.tileSize * 13 / 2;
        int y = gp.tileSize * 2;
        int width = gp.screenWidth - (gp.tileSize * 12);
        int height = gp.tileSize * 10;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));

        x += gp.tileSize;
        y += gp.tileSize * 5 / 3;

        g2.drawString("Return to the Game", x, y);
        if (menuNum == 0) {
            g2.drawString(">", x - gp.tileSize / 2, y);
        }
        y += 60;

        // g2.drawString("Fast travel", x, y);
        // if (menuNum == 1) {
        //     g2.drawString(">", x - gp.tileSize / 2, y);
        // }
        // y += 60;
        g2.drawString("Leaderboard", x, y);
        if (menuNum == 1) {
            g2.drawString(">", x - gp.tileSize / 2, y);
        }
        y += 60;
        g2.drawString("Settings", x, y);
        if (menuNum == 2) {
            g2.drawString(">", x - gp.tileSize / 2, y);
        }
        y += 60;
        g2.drawString("Help", x, y);
        if (menuNum == 3) {
            g2.drawString(">", x - gp.tileSize / 2, y);
        }
        y += 60;
        g2.drawString("Exit Game", x, y);
        if (menuNum == 4) {
            g2.drawString(">", x - gp.tileSize / 2, y);
        }
    }

    private void drawHelpScreen() { // add keys and controls. Type "press esc to go back to menu"
        int x = gp.tileSize * 13 / 2;
        int y = gp.tileSize * 2;
        int width = gp.screenWidth - (gp.tileSize * 12);
        int height = gp.tileSize * 10;

        drawSubWindow(x, y, width, height);

        x += gp.tileSize;
        y += gp.tileSize * 5 / 3;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));

        g2.drawString("Attack:  Enter", x, y);
        y += 60;
        g2.drawString("FireBall:   Space", x, y);
        y +=60;
        g2.drawString("Movement:   WASD", x, y);
        y += 60;
        g2.drawString("Interact:   F", x, y);
        y += 60;
        g2.drawString("Fast Travel:   Q", x, y);
        y += 60;
        g2.drawString("Menu:   M", x, y);
        y += 60;
        g2.drawString("Inventory:   I", x, y);
    }

    private void drawSettingsScreen() {
        int x = gp.tileSize * 13 / 2;
        int y = gp.tileSize * 2;
        int width = gp.screenWidth - (gp.tileSize * 12);
        int height = gp.tileSize * 10;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));

        x += gp.tileSize;
        y += gp.tileSize * 5 / 3;

        g2.drawString("Full Screen", x, y);
        if (settingsNum == 0) {
            g2.drawString(">", x - gp.tileSize / 2, y);
            g2.setColor(Color.WHITE);
            g2.drawRect(x + 170, y - 25, 30, 30);
            if (Main.getIsFullScreen()){ //if it is fullscreen
                g2.drawLine(x + 170, y - 25, x + 200, y + 5);
                g2.drawLine(x + 200, y - 25, x + 170, y + 5);
            }
        }

        y += 60;

        g2.drawString("Game Sound", x, y);
        if (settingsNum == 1) {
            g2.drawString(">", x - gp.tileSize / 2, y + 40);
        }

        y += 20;
        x += 10;
        g2.setColor(Color.ORANGE);
        for (int i = musicLevel; i > 0; i--) { // printing orange squares (which level is sound)
            g2.fillRect(x, y, 20, 20);
            x += 30;
        }
        g2.setColor(Color.GRAY);
        for (int i = 8 - musicLevel; i > 0; i--) { // gray squares
            g2.fillRect(x, y, 20, 20);
            x += 30;
        }
    }

    public void drawMonsterHealthBar(){
        for(int i = 0; i < gp.monster[gp.player.currentFloor].length; i++){
            Entity monster = gp.monster[gp.player.currentFloor][i];

            if(monster != null ){
                if(monster.isBoss == false){

                    g2.setColor(new Color(35,35,35));
                    g2.fillRect(monster.x - 1, monster.y - 16, gp.tileSize + 2, 12);

                    g2.setColor(new Color(255,0,30));
                    g2.fillRect(monster.x, monster.y - 15, gp.tileSize * monster.health / monster.maxHealth, 10);
                }
                else if (monster.isBoss == true){
                    int x = gp.screenWidth / 2 - gp.tileSize * 4;
                    int y = gp.tileSize * 12;
    
                    g2.setColor(new Color(35,35,35));
                    g2.fillRect(x - 1, y - 1, gp.tileSize * 8 + 2, 22);
                    
                    g2.setColor(new Color(255,0,30));
                    g2.fillRect(x, y, gp.tileSize * 8 * monster.health / monster.maxHealth, 20);
    
                    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24f));
                    g2.setColor(Color.WHITE);
                    g2.drawString(monster.name, x + 4, y - 10);
                }

            }

            
        }

        
    }


    public void drawDialogueScreen() {

        // WINDOW
        int x = gp.tileSize * 3;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 6);
        int height = gp.tileSize * 3;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 23F));

        x += gp.tileSize * 2 / 3;
        y += gp.tileSize * 2 / 3;

        for (String line : currentDialogue.split("\n")) {

            g2.drawString(line, x, y);
            y += 30;
        }

    }
    public void drawDeathScreen(){
        int x = gp.tileSize * 13 / 2;
        int y = gp.tileSize * 2;
        int width = gp.screenWidth - (gp.tileSize * 12);
        int height = gp.tileSize * 10;

        drawSubWindow(x, y, width, height);


        x += gp.tileSize;
        y += gp.tileSize * 5 / 3;

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50F));
        g2.drawString("You Died", x, y);
        y += 60;

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));

        g2.drawString("Keep Going", x, y);
        if (deathScreenNum == 0) {
            g2.drawString(">", x - gp.tileSize / 2, y);
        }
        y += 60;

        g2.drawString("Start a new Game", x, y);
        if (deathScreenNum == 1) {
            g2.drawString(">", x - gp.tileSize / 2, y);
        }
        y += 60;
        g2.drawString("Go back to title screen", x, y);
        if (deathScreenNum == 2) {
            g2.drawString(">", x - gp.tileSize / 2, y);
        }
        y += 60;
        g2.drawString("Exit Game", x, y);
        if (deathScreenNum == 3) {
            g2.drawString(">", x - gp.tileSize / 2, y);
        }
    }

    public void drawSubWindow(int x, int y, int width, int height) {

        Color c = new Color(0, 0, 0, 200);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(4));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);

    }

    // DRAW HEALTH BAR ON TOP LEFT SIDE OF THE SCREEN
    public void drawHealthBar() {
        // HEALTH BAR FRAME
        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int width = gp.tileSize * 4;
        int height = gp.tileSize / 2;

        g2.setColor(new Color(35, 35, 35));
        g2.fillRect(x, y, width, height);

        // CURRENT HEALTH
        g2.setColor(new Color(255, 0, 30));
        g2.fillRect(x + 1, y + 1, (int) ((width - 2) * (double) gp.player.health / gp.player.maxHealth), height - 2);

        // MAX HEALTH
        g2.setColor(new Color(0, 0, 0));
        g2.drawRect(x + 1, y + 1, width - 2, height - 2);

    }

    // DRAW MANA BAR ON TOP LEFT SIDE OF THE SCREEN
    public void drawManaBar() {
        // MANA BAR FRAME
        int x = gp.tileSize / 2 + gp.tileSize * 4 + 4;
        int y = gp.tileSize / 2;
        int width = gp.tileSize * 4;
        int height = gp.tileSize / 2;

        g2.setColor(new Color(35, 35, 35));
        g2.fillRect(x, y, width, height);

        // CURRENT MANA
        g2.setColor(Color.BLUE);
        g2.fillRect(x + 1, y + 1, (int) ((width - 2) * (double) gp.player.mana / gp.player.MAX_MANA), height - 2);

        // MAX MANA
        g2.setColor(new Color(0, 0, 0));
        g2.drawRect(x + 1, y + 1, width - 2, height - 2);

    }

     // DRAW 5 SLOTS FOR WEAPONS (4 weapon slots + 1 add slot)
    public void drawWeaponSlots() {
        int slotCount = 5;
        int slotWidth = gp.tileSize * 3/2;
        int slotHeight = gp.tileSize * 3/2;
        int totalWidth = slotWidth * slotCount;
        int xStart = (gp.screenWidth - totalWidth) / 2;
        int y = gp.screenHeight - gp.tileSize - (gp.tileSize / 2);
        drawSubWindow(xStart, y, totalWidth, slotHeight);

        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(2));

        for (int i = 0; i < slotCount; i++) {
            int slotX = xStart + slotWidth * i;
            if (i > 0) {
                g2.drawLine(slotX, y, slotX, y + slotHeight);
            }

            if (i < 4) {
                if (gp.player.weapons.size() > i && gp.player.weapons.get(i) != null) {
                    if(!gp.player.weapons.get(i).name.equals("Bow")) {
                        BufferedImage img = gp.player.weapons.get(i).imageRight;
                        g2.drawImage(img, slotX + 6, y +6, slotWidth - 15, slotHeight- 15, null);
                    } else {
                        // Draw the bow image
                        BufferedImage img = gp.player.weapons.get(i).imageRight;
                        g2.drawImage(img, slotX + 6, y +6, slotWidth - 15, slotHeight- 15, null);
                        //Write arrow count under the bow image
                        g2.setFont(new Font("Arial", Font.PLAIN, 16));
                        g2.setColor(Color.white);
                        String arrowCount = String.valueOf(gp.player.weapons.get(i).life);
                        FontMetrics fm = g2.getFontMetrics();
                        int textWidth = fm.stringWidth(arrowCount);
                        int textX = slotX + (slotWidth - textWidth) / 2-25;
                        int textY = y - 15 + slotHeight + fm.getAscent() / 2 - 4;
                        g2.drawString("Arrow: "+arrowCount, textX, textY);

                    }
                }
            }
            // Fifth slot: plus sign for adding a new weapon
            else {
                g2.setFont(new Font("Arial", Font.BOLD, slotWidth / 2));
                FontMetrics fm = g2.getFontMetrics();
                int textWidth = fm.stringWidth("+");
                int textX = slotX + (slotWidth - textWidth) / 2;
                int textY = y + (slotHeight + fm.getAscent()) / 2 - 4;
                g2.drawString("+", textX, textY);
            }
        }
    }

    public int getXForCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }

}
