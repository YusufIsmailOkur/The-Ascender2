package mainn;

import entity.Player;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Main {
   static JFrame window;
   static Boolean isFullScreen = false;
   ArrayList<Player> allPlayers = new ArrayList<>();

   public static void main(String[] args) {
      window = new JFrame();
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      window.setResizable(false);
      window.setTitle("2D GAME");


      GamePanel gamePanel = new GamePanel();
      window.add(gamePanel);

      window.pack();

      window.setLocationRelativeTo(null);
      window.setVisible(true);

      gamePanel.setupGame();
      gamePanel.startGameThread();
   }

   public static void fullScreen() {
      GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
  
      if (!isFullScreen){
          //if it is not fullscreen, it goes fullscreen
          window.dispose();
          window.setUndecorated(true);
          window.setResizable(false);
          graphicsDevice.setFullScreenWindow(window);
          window.setVisible(true);
          isFullScreen = !isFullScreen;
      } 
      else{
          //exiting fullscreen
          graphicsDevice.setFullScreenWindow(null);
  
          window.dispose();
          window.setUndecorated(false);
          window.setResizable(true);
          window.setSize(1000, 800); 
         //  frame.setLocationRelativeTo(null); 
          window.setVisible(true);
          isFullScreen = !isFullScreen;
      }
  }

  public static Boolean getIsFullScreen() {
   return isFullScreen;
   }
}
