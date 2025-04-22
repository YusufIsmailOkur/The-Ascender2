package mainn;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, fPressed;
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
            //Changing Current Weapon In Game
            if (code == KeyEvent.VK_1){
                gp.player.setCurrentWeapon(gp.player.weapons[0]);
            }
            if (code == KeyEvent.VK_2){
                gp.player.setCurrentWeapon(gp.player.weapons[1]);
            }
            if (code == KeyEvent.VK_3){
                gp.player.setCurrentWeapon(gp.player.weapons[2]);
            }
            if (code == KeyEvent.VK_4){
                gp.player.setCurrentWeapon(gp.player.weapons[3]);
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
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    
    // public void playMusic(int i){
    //     GamePanel.getSound().setFile(i);
    //     GamePanel.getSound().play();
    //     GamePanel.getSound().loop();
    // }

    // public void stopMusic(){
    //     GamePanel.getSound().stop();
    // }

    // public void playSoundEffect(int i){
    //     GamePanel.getSound().setFile(i);
    //     GamePanel.getSound().play();
    // }
    
}
