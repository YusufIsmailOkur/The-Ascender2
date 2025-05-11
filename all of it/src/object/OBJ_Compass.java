package object;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class OBJ_Compass extends SuperObject{
    
    public OBJ_Compass(){
        interactable = true;

        name = "compass";
        craftable = true;

        try {

            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/compass.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void interact() {
        //Create the dialog
        JDialog dialog = new JDialog();
        dialog.setUndecorated(true); 
        dialog.setSize(400, 300); 
        dialog.setLocationRelativeTo(null);
        dialog.setAlwaysOnTop(true);

        //Load the image

        //Create a panel to draw the image
        JPanel imagePanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
            }
        };

        dialog.add(imagePanel);
        dialog.setVisible(true);

        //ESC key to close
        dialog.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    dialog.dispose();
                }
            }
        });

        dialog.requestFocusInWindow();
    }
}

