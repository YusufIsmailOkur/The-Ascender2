package object;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;

public class OBJ_Letter extends SuperObject{
    BufferedImage imageLeft, imageRight;
    
    public OBJ_Letter(){
        interactable = true;
        name = "letter";
        craftable = true;

        try {

            imageLeft = ImageIO.read(getClass().getResourceAsStream("/res/objects/letter1.png"));
            imageRight = ImageIO.read(getClass().getResourceAsStream("/res/objects/letter2.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void interact() {
//create the dialog
        JDialog dialog = new JDialog((java.awt.Frame) null, true); //make the dialog modal so user cannot click anywhere until he close the image
        dialog.setUndecorated(true);
        dialog.setSize(400,600);
        dialog.setLocationRelativeTo(null);
        dialog.setAlwaysOnTop(true);

        //create a panel to draw the image
        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imageLeft, 0, 0, getWidth(), getHeight(), null);
            }
        };

        imagePanel.setFocusable(true); //debug
        dialog.add(imagePanel);

        // ESC key to close
        imagePanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    dialog.dispose();
                    drawSecondImage();
                }
            }
        });

        dialog.setVisible(true);        //show dialog first
        imagePanel.requestFocusInWindow(); //request focus    
        }


    private void drawSecondImage(){
        //create the dialog
        JDialog dialog = new JDialog((java.awt.Frame) null, true); //make the dialog modal so user cannot click anywhere until he close the image
        dialog.setUndecorated(true);
        dialog.setSize(400,600);
        dialog.setLocationRelativeTo(null);
        dialog.setAlwaysOnTop(true);

        //create a panel to draw the image
        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imageRight, 0, 0, getWidth(), getHeight(), null);
            }
        };

        imagePanel.setFocusable(true); //debug
        dialog.add(imagePanel);

        // ESC key to close
        imagePanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    dialog.dispose();
                }
            }
        });

        dialog.setVisible(true);        //show dialog first
        imagePanel.requestFocusInWindow(); //request focus
    }
}

