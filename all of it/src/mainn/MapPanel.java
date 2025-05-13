package mainn;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MapPanel extends JPanel {
    GamePanel gp;
    Rectangle[] floorButtons;
    int floorCount = 10;
    BufferedImage[] previews;

    public MapPanel(GamePanel gp) {
        this.gp = gp;
        this.setOpaque(false);
        this.setFocusable(true); // Make it able to receive mouse input
        this.requestFocusInWindow(); // Request focus when shown
        floorButtons = new Rectangle[floorCount];
        previews = new BufferedImage[floorCount];

        for (int i = 0; i < floorCount; i++) {
            floorButtons[i] = new Rectangle(100, 100 + i * 60, 300, 50);
            previews[i] = generatePreviewImage(i);
        }

        this.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                Point p = e.getPoint();
                for (int i = 0; i < floorCount; i++) {
                    if (floorButtons[i].contains(p)) {
                        boolean unlocked = gp.player.discoveredFloors[i];
                        if (unlocked) {
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
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 24));
        g2.drawString("Map - Click Unlocked Floor (x4) to Fast Travel", 100, 50);

        for (int i = 0; i < floorButtons.length; i++) {
            boolean unlocked = gp.player.discoveredFloors[i];
            g2.setColor(unlocked ? Color.GREEN : Color.GRAY);
            g2.fill(floorButtons[i]);
            g2.setColor(Color.BLACK);
            g2.drawRect(floorButtons[i].x, floorButtons[i].y, floorButtons[i].width, floorButtons[i].height);
            g2.drawString("Floor " + (i + 1) + (unlocked ? "[UNLOCKED]" : "[LOCKED]"),
                    floorButtons[i].x + 10, floorButtons[i].y + 20);
            g2.drawImage(previews[i], floorButtons[i].x + 200, floorButtons[i].y + 5, 40, 40, null);
        }
    }

    public void update() {
        this.requestFocusInWindow(); // Request focus every update to ensure it gets input
        repaint();
    }

    private BufferedImage generatePreviewImage(int floorIndex) {
        BufferedImage img = new BufferedImage(40, 40, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setColor(new Color(50 + (floorIndex * 20 % 200), 100, 150));
        g2.fillRect(0, 0, 40, 40);
        g2.setColor(Color.WHITE);
        g2.drawString(String.valueOf(floorIndex), 10, 25);
        g2.dispose();
        return img;
    }
}