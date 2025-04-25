package mainn;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.plaf.multi.MultiScrollBarUI;

public class KeyHandler implements KeyListener {
    int previousState = 0; //title state

    public boolean upPressed, downPressed, leftPressed, rightPressed, fPressed, enterPressed;
    GamePanel gp;
    public KeyHandler (GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // Title STATE
        if (gp.gameState == gp.titleState){
            if (code == KeyEvent.VK_W){
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0){
                    gp.ui.commandNum = 2;
                }
            }
            if (code == KeyEvent.VK_S){
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2){
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER){
                if (gp.ui.commandNum == 0){
                    gp.gameState = gp.playState;
                }
                else if (gp.ui.commandNum == 1){
                    // later
                }
                else if (gp.ui.commandNum == 2){
                    System.exit(0);
                }
            }
        }

        // PLAY STATE
        if (gp.gameState == gp.playState ){
            if (code == KeyEvent.VK_W){
                upPressed = true;
            }
            if (code == KeyEvent.VK_S){
                downPressed = true;
            }
            if (code == KeyEvent.VK_A){
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D){
                rightPressed = true;
            }
            if (code == KeyEvent.VK_P){
                gp.gameState = gp.pauseState;
            }
            if (code == KeyEvent.VK_F){
                fPressed = true;
            }
            if (code == KeyEvent.VK_M){
                gp.gameState = gp.menuState;
            }
            if(code == KeyEvent.VK_ENTER){
                enterPressed = true;
            }
            //Changing Current Weapon In Game
            if (code == KeyEvent.VK_1){
                gp.player.setCurrentWeapon(gp.player.weapons.get(0));
            }
            if (code == KeyEvent.VK_2){
                gp.player.setCurrentWeapon(gp.player.weapons.get(1));
            }
            if (code == KeyEvent.VK_3){
                gp.player.setCurrentWeapon(gp.player.weapons.get(2));
            }
            if (code == KeyEvent.VK_4){
                gp.player.setCurrentWeapon(gp.player.weapons.get(3));
            }
        }
        // PAUSE STATE
        else if (gp.gameState == gp.pauseState){
            if (code == KeyEvent.VK_P){
                gp.gameState = gp.playState;
            }
        }
        // DIALOGUE STATE
        else if (gp.gameState == gp.dialogueState){
            if (code == KeyEvent.VK_F){
                gp.gameState = gp.playState;
            }
        }
        // MENU STATE
        else if (gp.gameState == gp.menuState){
            if (code == KeyEvent.VK_W){
                gp.ui.menuNum--;
                if (gp.ui.menuNum < 0){
                    gp.ui.menuNum = 5;
                }
            }
            if (code == KeyEvent.VK_S){
                gp.ui.menuNum++;
                if (gp.ui.menuNum > 5){
                    gp.ui.menuNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER){
                if (gp.ui.menuNum == 0){
                    gp.gameState = gp.playState;
                }
                else if (gp.ui.menuNum == 1){
                    // Fast TRAVEL
                }
                else if (gp.ui.menuNum == 2){
                    // LeaderBoard
                }
                else if (gp.ui.menuNum == 3){
                    // SETTINGS
                    gp.gameState = gp.settingsState;
                }
                else if (gp.ui.menuNum == 4){
                    // HELP
                    gp.gameState = gp.helpState;
                }
                else if (gp.ui.menuNum == 5){
                    // EXIT
                }
                
            }
        }
        else if (gp.gameState == gp.helpState){
            if (code == KeyEvent.VK_ESCAPE){ //exiting to menu state back
                gp.gameState = gp.menuState;
            }
        }
        //if gameState changes by pressing a key, it changes the music
        else if (gp.gameState ==gp.settingsState){

 

            if (code == KeyEvent.VK_W){
 

                gp.ui.settingsNum--;
 

                if (gp.ui.settingsNum < 0){
 

                    gp.ui.settingsNum = 1;
 

                }
 

            }
 

            if (code == KeyEvent.VK_S){
 

                gp.ui.settingsNum++;
 

                if (gp.ui.settingsNum > 1){
 

                    gp.ui.settingsNum = 0;
 

                }
 

            }
 

            if (code == KeyEvent.VK_ENTER || gp.ui.settingsNum == 0){
 

                //fullscreen
 

            }
 

            if (gp.ui.settingsNum == 0 || code == KeyEvent.VK_D){ //increasing sound
 

                if (gp.ui.musicLevel <= 10 && gp.ui.musicLevel >= 0){
 

                    gp.ui.musicLevel++;
 

                    gp.getSound().setVolume(gp.ui.musicLevel);
 

                }
 

            }
 

            if (gp.ui.settingsNum == 0 || code == KeyEvent.VK_A){ //decreasing sound
 

                if (gp.ui.musicLevel <= 10 && gp.ui.musicLevel >= 0){
 

                    gp.ui.musicLevel--;
 

                    gp.getSound().setVolume(gp.ui.musicLevel);
 

                }
 

            }
 

            if (code == KeyEvent.VK_ESCAPE){ //exiting to menu state back
 

                gp.gameState = gp.menuState;
 

            }
 

        }
 

        if (previousState != gp.gameState){ //if gameState changes by pressing a key, it changes the music
 

            if (previousState == gp.titleState){ // if previousstate was titlestate
 

                playMusicByState(gp.gameState);
 

            }
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W){
            upPressed = false;
        }
        if (code == KeyEvent.VK_S){
            downPressed = false;
        }
        if (code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D){
            rightPressed = false;
        }
        if (code == KeyEvent.VK_F){
            fPressed = false;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    private void playMusicByState(int currentGameState){
        stopMusic();
        if (currentGameState == gp.titleState){
            playMusic(0);
        }
        else{
            playMusic(1);
        }
        previousState = currentGameState;
    }
    
    public void playMusic(int i){
        gp.getSound().setFile(i);
        gp.getSound().play();
        gp.getSound().loop();
    }

    public void stopMusic(){
        gp.getSound().stop();
    }

    public void playSoundEffect(int i){
        gp.getSound().setFile(i);
        gp.getSound().play();
    }
    
}
