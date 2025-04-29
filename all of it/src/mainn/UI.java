package mainn;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.sql.Time;
import java.text.DecimalFormat;
import java.util.ArrayList;
import weapon.*;

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
    public int menuNum = 0;
    public int settingsNum = 0;
    public int musicLevel = 8;

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

        text = "Exit Game";
        x = getXForCenteredText(text);
        y += gp.tileSize * 1.3;
        g2.setColor(Color.black);
        g2.drawString(text, x + 5, y + 5);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 38F));
        text = "Settings";
        x = gp.tileSize * 4 / 5;
        y = gp.tileSize * 14;
        g2.setColor(Color.black);
        g2.drawString(text, x + 5, y + 5);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
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

        g2.drawString("Fast travel", x, y);
        if (menuNum == 1) {
            g2.drawString(">", x - gp.tileSize / 2, y);
        }
        y += 60;
        g2.drawString("Leaderboard", x, y);
        if (menuNum == 2) {
            g2.drawString(">", x - gp.tileSize / 2, y);
        }
        y += 60;
        g2.drawString("Settings", x, y);
        if (menuNum == 3) {
            g2.drawString(">", x - gp.tileSize / 2, y);
        }
        y += 60;
        g2.drawString("Help", x, y);
        if (menuNum == 4) {
            g2.drawString(">", x - gp.tileSize / 2, y);
        }
        y += 60;
        g2.drawString("Exit Game", x, y);
        if (menuNum == 5) {
            g2.drawString(">", x - gp.tileSize / 2, y);
        }
    }

    private void drawHelpScreen() { // add keys and controls. Type "press esc to go back to menu"
        int x = gp.tileSize * 13 / 2;
        int y = gp.tileSize * 2;
        int width = gp.screenWidth - (gp.tileSize * 12);
        int height = gp.tileSize * 10;

        drawSubWindow(x, y, width, height);

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

    // DRAW 4 SLOTS FOR WEAPONS
    public void drawWeaponSlots() {
        int width = gp.tileSize * 4;
        int height = gp.tileSize;
        int x = (gp.screenWidth - width) / 2;
        int y = gp.screenHeight - gp.tileSize - (gp.tileSize / 2);

        // Background panel
        drawSubWindow(x, y, width, height);

        // Draw the current weapon (first in the list as example)
        ArrayList<SuperWeapon> weapons = gp.player.weapons;
        if (!weapons.isEmpty() && weapons.get(0) != null) {
            int imgX = x + (width - gp.tileSize) / 2;
            int imgY = y;
            g2.drawImage(weapons.get(0).image, imgX, imgY, gp.tileSize, gp.tileSize, null);
        }

        // Draw separators between slots
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(2));
        for (int i = 1; i < 4; i++) {
            int lineX = x + (gp.tileSize * i);
            g2.drawLine(lineX, y, lineX, y + height);
        }
    }

    public int getXForCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }

}