package mainn;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    int previousState = 0; // title state
    static long timeStart;
    long timeEnd;
    static boolean genocideMusic = false;


    public boolean upPressed, downPressed, leftPressed, rightPressed, fPressed, enterPressed, spacePressed, onePressed,
            twoPressed, threePressed;
    GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // Title STATE
        if (gp.gameState == gp.titleState) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 3;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 3) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    gp.askName();
                    gp.gameState = gp.storyState;
                    timeStart = System.currentTimeMillis();
                } else if (gp.ui.commandNum == 1) {
                    gp.askNameAndSetPlayerValues();
                    gp.gameState = gp.playState;
                    timeStart = System.currentTimeMillis();
                } 
                else if(gp.ui.commandNum == 2){
                    gp.gameState = gp.leaderBoardState;
                    
                }
                
                else if (gp.ui.commandNum == 3) {
                    System.exit(0);
                }
            }
        }

        // PLAY STATE
        if (gp.gameState == gp.playState) {
            if (code == KeyEvent.VK_W) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_P) {
                gp.gameState = gp.pauseState;
            }
            if (code == KeyEvent.VK_I) {
                gp.gameState = gp.inventoryState;
                gp.inventory.refreshObjectsAndWeapons();
                gp.inventory.refreshCraftButtons();
                gp.inventory.refreshInteractButtons();
                gp.inventory.setVisible(true);
            }
            if (code == KeyEvent.VK_F) {
                fPressed = true;
            }
            if (code == KeyEvent.VK_M) {
                gp.gameState = gp.menuState;
            }
            if (code == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }
            if (code == KeyEvent.VK_SPACE) {
                spacePressed = true;
            }
            if (code == KeyEvent.VK_1) {
                onePressed = true;
            }
            if (code == KeyEvent.VK_2) {
                twoPressed = true;
            }
            if (code == KeyEvent.VK_3) {
                threePressed = true;
            }
            if (code == KeyEvent.VK_5) {
                gp.gameState = gp.weaponListState;
                gp.weaponList.refreshWeaponList();
                gp.weaponList.setVisible(true);
            }
            if (gp.player.health <= 0) {
                gp.gameState = gp.deathState;
            }
        }
        // exiting story state
        else if (gp.gameState == gp.storyState) {
            if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.playState;
            }
        }
        // PAUSE STATE
        else if (gp.gameState == gp.pauseState) {
            if (code == KeyEvent.VK_P) {
                gp.gameState = gp.playState;
            }
        }
        // DIALOGUE STATE
        else if (gp.gameState == gp.dialogueState) {
            if (code == KeyEvent.VK_F) {
                gp.gameState = gp.playState;
            }
        }
        // MENU STATE
        else if (gp.gameState == gp.menuState) {
            if (code == KeyEvent.VK_W) {
                gp.ui.menuNum--;
                if (gp.ui.menuNum < 0) {
                    gp.ui.menuNum = 4;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.menuNum++;
                if (gp.ui.menuNum > 4) {
                    gp.ui.menuNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER){
                if (gp.ui.menuNum == 0){
                    gp.gameState = gp.playState;
                }
                //  else if (gp.ui.menuNum == 1) {
                //     // Fast TRAVEL
                // } 
                else if (gp.ui.menuNum ==1) {
                    // LeaderBoard
                    gp.gameState = gp.leaderBoardState;
                } else if (gp.ui.menuNum == 2) {
                    // SETTINGS
                    gp.gameState = gp.settingsState;
                } else if (gp.ui.menuNum == 3) {
                    // HELP
                    gp.gameState = gp.helpState;
                } else if (gp.ui.menuNum == 4) {
                    // EXIT
                    // Save Values
                    timeEnd = System.currentTimeMillis();
                    gp.player.totalTime += timeEnd - timeStart;
                    gp.writePlayersValuesToFile(gp.player.name);
                    System.exit(code);
                }

            }
        }
        // INVENTORY STATE
        else if (gp.gameState == gp.inventoryState) {
            if (code == KeyEvent.VK_I || code == KeyEvent.VK_ESCAPE) {
                gp.inventory.refreshObjectsAndWeapons();
                gp.gameState = gp.playState;
                gp.inventory.setVisible(false);
            }
        }
        // HELP STATE
        else if (gp.gameState == gp.helpState) {
            if (code == KeyEvent.VK_ESCAPE) { // exiting to menu state back
                gp.gameState = gp.menuState;
            }
        }
        // SETTINGS STATE
        else if (gp.gameState == gp.settingsState) {
            if (code == KeyEvent.VK_W) {
                gp.ui.settingsNum--;
                if (gp.ui.settingsNum < 0) {
                    gp.ui.settingsNum = 1;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.settingsNum++;
                if (gp.ui.settingsNum > 1) {
                    gp.ui.settingsNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER && gp.ui.settingsNum == 0) {
                // fullscreen
                Main.fullScreen();
            } else if (gp.ui.settingsNum == 1 && code == KeyEvent.VK_D) { // increasing sound
                if (gp.ui.musicLevel < 8) {
                    gp.ui.musicLevel++;
                    gp.getSound().setVolume(gp.ui.musicLevel);
                }
            } else if (gp.ui.settingsNum == 1 && code == KeyEvent.VK_A) { // decreasing sound
                if (gp.ui.musicLevel > 0) {
                    gp.ui.musicLevel--;
                    gp.getSound().setVolume(gp.ui.musicLevel);
                }
            } else if (code == KeyEvent.VK_ESCAPE) { // exiting to menu state back
                gp.gameState = gp.menuState;
            }
        }
        // LEADERBOARD STATE
        else if (gp.gameState == gp.leaderBoardState) {
            if (code == KeyEvent.VK_ESCAPE) { // exiting to menu state back
                gp.gameState = gp.menuState;
            }
        }
        // WEAPONLIST STATE
        else if (gp.gameState == gp.weaponListState) {
            if (code == KeyEvent.VK_ESCAPE) { // exiting to menu state back
                gp.player.weapons = WeaponListPanel.weapons;
                gp.gameState = gp.playState;
                gp.weaponList.setVisible(false);
            }
        } else if (gp.gameState == gp.deathState) {
            if (code == KeyEvent.VK_W) {
                gp.ui.deathScreenNum--;
                if (gp.ui.deathScreenNum < 0) {
                    gp.ui.deathScreenNum = 3;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.deathScreenNum++;
                if (gp.ui.deathScreenNum > 3) {
                    gp.ui.deathScreenNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.deathScreenNum == 0) {
                    gp.player.health += 1;
                    gp.gameState = gp.playState;
                } else if (gp.ui.deathScreenNum == 1) {
                    // new game no SAVING AND RESET THE ENTİRE GAME
                } else if (gp.ui.deathScreenNum == 2) {
                    gp.gameState = gp.titleState;
                    // DO SAVING AND PUT THE CORRECT MUSIC
                    gp.writePlayersValuesToFile(gp.player.name);

                } else if (gp.ui.deathScreenNum == 3) {
                    // EXIT
                    System.exit(code);
                }
            }
        }
        if (code == KeyEvent.VK_Q) {
            if (gp.gameState == gp.playState) {
                gp.gameState = gp.mapState;
                gp.mapPanel.setVisible(true);
                gp.mapPanel.setFocusable(true);
                gp.setComponentZOrder(gp.mapPanel, 0);
                gp.mapPanel.requestFocusInWindow();
                gp.mapPanel.repaint();
            } else if (gp.gameState == gp.mapState) {
                gp.mapPanel.setVisible(false);
                gp.gameState = gp.playState;
                gp.requestFocusInWindow();
            }
        }
        avoidMusicRepeat();
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_F) {
            fPressed = false;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = false;
        }
        if (code == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
        if (code == KeyEvent.VK_1) {
            onePressed = false;
        }
        if (code == KeyEvent.VK_2) {
            twoPressed = false;
        }
        if (code == KeyEvent.VK_3) {
            threePressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public void avoidMusicRepeat(){
        if (previousState != gp.gameState){ //if gameState changes by pressing a key, it changes the music
            if (previousState == gp.titleState){ // if previousstate was titlestate
                playMusic();
            }
            
        }
        if (gp.player.previousCurrentFloor != gp.player.currentFloor){
            playMusic();
            gp.player.previousCurrentFloor = gp.player.currentFloor;
        }
    }

    public void playMusic(){
        stopMusic();
        if (gp.gameState == gp.titleState){
            playMusic(0);
        }
        else if (gp.player.currentFloor == 10){
            playMusic(3);
        }
        else if (gp.player.currentFloor == 4){
            playMusic(2);
        }
        else if (gp.player.currentFloor == 15){
            playMusic(4);
        }
        else{
            playMusic(1);
        }
        previousState = gp.gameState;
    }

    public void playMusic(int i) {
        gp.getSound().setFile(i);
        gp.getSound().play();
        gp.getSound().loop();
    }

    public void stopMusic() {
        gp.getSound().stop();
    }

    public void playSoundEffect(int i) {
        gp.getSound().setFile(i);
        gp.getSound().play();
    }
}
