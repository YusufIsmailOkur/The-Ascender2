package mainn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class MapPanel extends JPanel {
    GamePanel gp;
    Rectangle[] floorButtons;
    int floorCount = 18;
    BufferedImage[] previews;

    public MapPanel(GamePanel gp) {
        this.gp = gp;
        this.setOpaque(false);
        this.setFocusable(true); // Make it able to receive mouse input
        this.requestFocusInWindow(); // Request focus when shown
        floorButtons = new Rectangle[floorCount];
        previews = new BufferedImage[floorCount];

        for (int i = 0; i < floorCount; i++) {
            if (i > 9) {
                floorButtons[i] = new Rectangle(250 , 100 + i % 10 * 60, 120, 50);
            }
            else{
                floorButtons[i] = new Rectangle(100 , 100 + i * 60, 120, 50);
            }
        }

        this.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                Point p = e.getPoint();
                for (int i = 0; i < floorCount; i++) {
                    if (floorButtons[i].contains(p)) {
                        boolean unlocked = gp.player.discoveredFloors[i];
                        if (true) {
                            gp.player.currentFloor = i;
                            gp.tileM.loadMap("/res/maps/map" + (gp.player.currentFloor + 1) + ".txt");
                            gp.player.x = 1 * gp.tileSize;
                            gp.player.y = 7 * gp.tileSize;
                            gp.gameState = gp.playState;
                            gp.mapPanel.setVisible(false);
                            gp.requestFocusInWindow();
                            gp.repaint();
                            break;
                        }
                    }
                }
            }
        });
        this.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                int code = e.getKeyCode();
                if (code == KeyEvent.VK_Q || code == KeyEvent.VK_ESCAPE) {
                    if (gp.gameState == gp.mapState) {
                        gp.mapPanel.setVisible(false);
                        gp.gameState = gp.playState;
                        gp.requestFocusInWindow();
                        gp.repaint();
                    }
                }
            }
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 24));
        g2.drawString("Map - Click Unlocked Floor to Fast Travel", 100, 50);

        for (int i = 0; i < floorButtons.length; i++) {
            boolean unlocked = gp.player.discoveredFloors[i];
            g2.setColor(unlocked ? Color.GREEN : Color.GRAY);
            g2.fill(floorButtons[i]);
            g2.setColor(Color.BLACK);
            g2.drawRect(floorButtons[i].x, floorButtons[i].y, floorButtons[i].width, floorButtons[i].height);
            g2.drawString("Floor " + (i + 1), floorButtons[i].x + 10, floorButtons[i].y + 20);
            g2.drawImage(previews[i], floorButtons[i].x + 200, floorButtons[i].y + 5, 40, 40, null);
        }
    }

    public void update() {
        this.requestFocusInWindow(); // Request focus every update to ensure it gets input
        repaint();
    }
}