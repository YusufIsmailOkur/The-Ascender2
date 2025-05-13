package mainn;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

import entity.Entity;
import entity.Player;
import environment.EnvironmentManager;
import object.*;
import tile.TileManager;
import weapon.*;

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
    EnvironmentManager eManager = new EnvironmentManager(this);

    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public Player player = new Player(this, keyH);
    public Inventory inventory = new Inventory(this);
    public WeaponListPanel weaponList = new WeaponListPanel(this);

    public SuperObject obj[][] = new SuperObject[20][20];
    public Entity npc[][] = new Entity[20][20];
    public Entity monster[][] = new Entity[20][50];
    public ArrayList <Entity> projectiles = new ArrayList<>();
    public SuperWeapon[] weapon = new SuperWeapon[20];
    public String[] allObjects = {"Arrow","boots","chest","key","door","elevator","Fireball", "compass", "letter", "screwdriver"};//Should updated for every new object
    public String[] allWeapons = {"Bow","Sword"}; //Should updated for every new weapon
    public UtilityTool uTool = new UtilityTool();

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
    public final int leaderBoardState=9;
    public final int storyState = 10;
    public final int weaponListState = 11;
    public final int mapState = 12;
    public MapPanel mapPanel = new MapPanel(this);

    static boolean haskilledSlimeBoss = false;

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
        //Weapon List
        weaponList.setBounds(0, 0, screenWidth, screenHeight);
        weaponList.setVisible(false);
        this.add(weaponList);
        //Map Panel
        mapPanel.setBounds(0, 0, screenWidth, screenHeight);
        mapPanel.setVisible(false);
        mapPanel.setFocusable(false);
        setLayout(null);
        this.add(mapPanel);

    }

    public void setupGame(){

        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.setWeapon();
        ui.isSetup = true;
        gameState = titleState;
        playMusic(0);
        eManager.setup(); 
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
            if(monster[17][0] == null){
                player.finishedGame = true;
            }
            int currentFloor = player.currentFloor;
            tileM.loadMap("\"/res/maps/map02.txt\"");
            //npc
            for (int i = 0; i <npc.length; i++){
                if (npc[currentFloor][i] != null){
                    npc[currentFloor][i].update();
                }
            }
            for (int i = 0; i < monster[0].length; i++) {
                if (monster[currentFloor][i] != null) {
                    monster[currentFloor][i].update();
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
        if (gameState == mapState) {
            mapPanel.update();
        }

        if(monster[4][0] == null && haskilledSlimeBoss == false){

            haskilledSlimeBoss = true;
            obj[4][2] = new OBJ_Key();
            obj[4][2].x = 11 * tileSize;
            obj[4][2].y = 7 * tileSize;

        }
    }
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        int currentFloor = player.currentFloor;

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
        //LEADERBOARD SCREEN
        else if (gameState == leaderBoardState)
        {
            LeaderBoard lb = new LeaderBoard(this);
            lb.paintComponent(g);
        }
        //WEAPON LIST
        else if(gameState == weaponListState) {
            weaponList.paintComponent(g);
        }
        //MAP
        else if (gameState == mapState) {
            mapPanel.paintComponent(g);
        }

        // OTHERS
        else {
            // TİLE
        tileM.draw(g2);

        // OBJECT
        for (int i = 0; i < obj[0].length; i++){
            if (obj[currentFloor][i] != null){
                obj[currentFloor][i].draw(g2, this);
            }
        }

        //WEAPON
        for (SuperWeapon wp : weapon) {
            if (wp != null) {
                g2.drawImage(wp.image, wp.x, wp.y, tileSize, tileSize, null);
            }
        }


        //NPC
        for(int i = 0; i < npc[0].length; i++){
            if (npc[currentFloor][i] != null){
                npc[currentFloor][i].draw(g2);
            }
        }

        //MONSTER
        for(int i = 0; i < monster[0].length; i++){
            if (monster[currentFloor][i] != null){
                monster[currentFloor][i].draw(g2);
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

        //Environment
        if (player.currentFloor == 5 || player.currentFloor == 9){
            eManager.draw(g2);
        }

        //UI
        ui.draw(g2);
        
        g2.dispose();
        }
    }

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
    public void askNameAndSetPlayerValues(){
        String name = showInputDialog("Enter name for Load game");
        while(true) {
            if (!checkNameValidity(name)) {
                player.name = name;
                readAndSetPlayerValuesFromFile(name);
                break;
            } else {
                showMessageDialog("Couldnt find your name. Please enter another");
                name = showInputDialog("Enter name for Load game");
            }
        }
    }
    public void writeNameToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("names.txt", true))) {
            writer.println("Start");
            writer.println(player.name);
            writer.println("End");
        } catch (IOException e) {
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
    
    public void writePlayersValuesToFile(String name) {
        ArrayList<String> lines = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(new File("names.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                lines.add(line);
            }
            int startIndex = lines.indexOf(name);
            int endIndex=0;
            for(int i=startIndex;i<lines.size();i++){
                if(lines.get(i).equals("End")){
                    endIndex=i;
                }
            }
            for (int i = startIndex; i <= endIndex; i++) {
                lines.remove(startIndex);
            }
            lines.add(startIndex, name);
            lines.add(++startIndex, String.valueOf(player.currentFloor));
            lines.add(++startIndex, String.valueOf(player.health));
            lines.add(++startIndex, String.valueOf(player.totalTime));
            lines.add(++startIndex, String.valueOf(player.finishedGame));
            int count =0;
            for(int i=0; i<player.weapons.size();i++){
                if(player.weapons.get(i).name.equals("Bow")){
                    count=player.weapons.get(i).life;
                }
            }
            lines.add(++startIndex, String.valueOf(count));
            for(int i=0;i<18;i++){
                lines.add(++startIndex, String.valueOf(player.discoveredFloors[i]));
            }
            lines.add(++startIndex, "Obj");
            for (SuperObject obj : player.objects) {
                lines.add(++startIndex, obj.name);
            }
            lines.add(++startIndex, "Weapons");
            for (SuperWeapon wpn : player.weapons) {
                lines.add(++startIndex, wpn.name);
            }
            lines.add(++startIndex, "End");
            try (PrintWriter writer = new PrintWriter(new File("names.txt"))) {
                for (String line : lines) {
                    writer.println(line);
                }
            }
        } catch (FileNotFoundException e) {
            showMessageDialog("Error");
        }
    }

    public void readAndSetPlayerValuesFromFile(String name) {
        try (Scanner fileScanner = new Scanner(new File("names.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.equals(name)) {
                    player.currentFloor = Integer.parseInt(fileScanner.nextLine());
                    tileM.loadMap("/res/maps/map" + (player.currentFloor + 1) + ".txt");
                    int health = Integer.parseInt(fileScanner.nextLine());
                    if(health<=0){
                        health= health+2;
                    }
                    player.health=health;
                    player.totalTime =Long.parseLong(fileScanner.nextLine());
                    if(fileScanner.nextLine().equals("true")){
                        player.finishedGame=true;
                    }
                    else{
                        player.finishedGame=false;
                    }
                    int arrowCount = (Integer.parseInt(fileScanner.nextLine()));
                    for(int i =0; i<18;i++){
                        String s = fileScanner.nextLine();
                        if (s.equals("true")){
                            player.discoveredFloors[i]=true;
                        }
                        else {
                            player.discoveredFloors[i]=false;
                        }
                    }
                    String s = fileScanner.nextLine();
                    player.objects.clear();
                    while (!s.equals("Weapons")) {
                        switch (s.toLowerCase()) {
                            case "boots":
                                player.objects.add(new OBJ_Boots());
                                break;
                            case "key":
                                player.objects.add(new OBJ_Key());
                                break;
                            case "compass":
                                player.objects.add(new OBJ_Compass());
                                break;
                            case "light":
                                player.objects.add(new OBJ_Light());
                                break;
                            case "health potion":
                                player.objects.add(new OBJ_HealthPotion(this));
                                break;
                            case "photo":
                                player.objects.add(new OBJ_Photo());
                                break;
                            case "letter":
                                player.objects.add(new OBJ_Letter());
                                break;
                            case "lighted letter":
                                player.objects.add(new OBJ_LightedLetter());
                                break;
                            case "screwdriver":
                                player.objects.add(new OBJ_ScrewDriver());
                                break;
                            default:
                                System.out.println("Unknown object: " + s);
                                break;
                        }
                        s= fileScanner.nextLine();
                    }
                    player.weapons.clear();
                    String st = fileScanner.nextLine();
                    while (!st.equals("End")) {
                        if (st.equals("Bow")) {
                            player.weapons.add(new WPN_Bow());
                        } else if (st.equals("Sword")) {
                            player.weapons.add(new WPN_Sword());
                        }
                        else if (st.equals("Diamond Sword")){
                            player.weapons.add(new WPN_DiamondSword());
                        }
                        st= fileScanner.nextLine();
                    }
                    for(int i=0; i<player.weapons.size();i++){
                        if(player.weapons.get(i).name.equals("Bow")){
                            player.weapons.get(i).life=arrowCount;
                        }
                    }
                    break;
                }         
            }
        } catch (FileNotFoundException e) {
            showMessageDialog("Error");
        }
    }
}
