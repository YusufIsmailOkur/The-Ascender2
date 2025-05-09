package mainn;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;
import weapon.SuperWeapon;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.util.Scanner;

import static javax.swing.JOptionPane.*;

public class GamePanel extends JPanel implements Runnable{
    
    final int originalTileSize = 16; // 16x16 charachter
    final int scale = 3; // it will print it larger on the screen

    final public int tileSize = originalTileSize * scale; //48*48 tile

    public final int maxScreenCol = 20;    // creating the screen col
    public final int maxScreenRow = 15;
    public final int screenWidth = tileSize* maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize* maxScreenRow; // 576 pixels

    //sound
    Sound sound = new Sound();

    // FPS
    int FPS = 60;

    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);

    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public Player player = new Player(this, keyH);
    public Inventory inventory = new Inventory(this);

    public SuperObject obj[] = new SuperObject[10];
    public Entity npc[] = new Entity[10];
    public Entity monster[] = new Entity[10];
    public ArrayList <Entity> projectiles = new ArrayList<>();
    public SuperWeapon[] weapon = new SuperWeapon[10];


    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int menuState = 4;
    public final int settingsState = 5;
    public final int helpState = 6;
    public final int deathState = 7;
    public final int inventoryState = 8;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // rendering performance
        this.addKeyListener(keyH);
        this.setFocusable(true);

        //INventory
        inventory.setBounds(0, 0, screenWidth, screenHeight);
        inventory.setVisible(false);
        this.add(inventory);
    }

    public void setupGame(){

        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.setWeapon();
        gameState = titleState;
        playMusic(0);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        while (gameThread != null){

            double drawInterval = 1000000000 / FPS;
            double nextDrawTime = System.nanoTime() + drawInterval;
            // update information such as character position
            update();

            // draw the screen with the updated information
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if(remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);   

                nextDrawTime += drawInterval;
                
            } catch (InterruptedException e) {}
        }
    }

    public void update(){

        if (gameState == playState){
            //player
            player.update();
            //npc
            for (int i = 0; i <npc.length; i++){
                if (npc[i] != null){
                    npc[i].update();
                }
            }
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    monster[i].update();
                }
            }
            for (int i = 0; i < projectiles.size(); i++) {
                if (projectiles.get(i) != null) {
                    projectiles.get(i).update();
                }
                if(projectiles.get(i).alive == false){
                    projectiles.remove(i);
                }
            }
        }
        if (gameState == pauseState){

        }
    }
    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        // TİTLE SCREEN
        if (gameState == titleState){
            ui.draw(g2);
        }
        //INVENTORY SCREEN
        else if (gameState == inventoryState)
        {
            inventory.paintComponent(g);
        }
        // OTHERS
        else {
            // TİLE
        tileM.draw(g2);

        // OBJECT
        for (int i = 0; i < obj.length; i++){
            if (obj[i] != null){
                obj[i].draw(g2, this);
            }
        }

        //WEAPON
        for (SuperWeapon wp : weapon) {
            if (wp != null) {
                g2.drawImage(wp.image, wp.x, wp.y, tileSize, tileSize, null);
            }
        }


        //NPC
        for(int i = 0; i < npc.length; i++){
            if (npc[i] != null){
                npc[i].draw(g2);
            }
        }

        //MONSTER
        for(int i = 0; i < monster.length; i++){
            if (monster[i] != null){
                monster[i].draw(g2);
            }
        }

        //PROJECTILE
        for(int i = 0; i < projectiles.size(); i++){
            if(projectiles.get(i) != null && projectiles.get(i).alive == true){
                projectiles.get(i).draw(g2);
            }
        }

        //PLAYER
        player.draw(g2);
        ui.draw(g2);
        
        g2.dispose();
        }
    }

    // public static Sound getSound() {
    //     return sound;
    // }

    public Sound getSound() {
        return sound;
    }

    public void playMusic(int i){
        sound.setFile(i);
        sound.play();
        sound.loop();
    }
    public void stopMusic(){
        sound.stop();

    }
    public void askName(){
        String name = showInputDialog("What is your name?");
        while(true) {
            if (checkNameValidity(name)) {
                player.name = name;
                writeNameToFile();
                break;
            } else {
                showMessageDialog("Your name already exists. Please enter another");
                name = showInputDialog("What is your name?");
            }
        }
    }
    public void writeNameToFile() {
        try (PrintWriter writer = new PrintWriter(new File("names.txt"))) {
            if (player.name!=null&& !player.name.isEmpty()) {
                writer.println(player.name);
                showMessageDialog("Your name succesfully saved");
            } else {
                showMessageDialog("Error: Name is null");
            }
        } catch (FileNotFoundException e) {
            showMessageDialog("Error");
        }
    }

    private void showMessageDialog(String s) {
        JOptionPane.showMessageDialog(this, s);
    }
    //Looks all names and if its same returns false if its first time returns true
    private boolean checkNameValidity(String name) {
        try (Scanner fileScanner = new Scanner(new File("names.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (!line.isEmpty() && name.equals(line)) {
                    return false;
                }
            }
            return true;
        } catch (FileNotFoundException e) {
            System.err.println("names.txt not found; assuming first run.");
            return true;
        }
    }
}
