package object;

import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class OBJ_TestDoorClosed extends SuperObject{
    String password;
    private static boolean changed = false;


    public OBJ_TestDoorClosed(String password){

        this.password = password;
        name = "testdoorclosed";
        craftable = false;
        opened = false;
        this.opened = false;

        try {

            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/testDoorClosed.png")); 
        } catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }

    @Override
    public void interact() {
        if (opened) return;
        //arachne door
        if (password.equals("arachne")){
            String input = JOptionPane.showInputDialog(null, "The door is locked. Enter the password:");

            if (input != null && input.trim().equalsIgnoreCase(password)) {
                JOptionPane.showMessageDialog(null, "Correct password! The door opens.");
                this.collision = false;
                this.opened = true;
                

                try {
                    image = ImageIO.read(getClass().getResourceAsStream("/res/objects/testDoorOpened.png")); 
                } catch(IOException e){
                    e.printStackTrace();
                }
            } else if (input != null) {
                JOptionPane.showMessageDialog(null, "Incorrect password.");
            }
        }
        //123 door
        else if (password.equals("01548752")){
            String input = JOptionPane.showInputDialog(null, "The door is locked. Enter the password:");

            if (input != null && input.trim().equalsIgnoreCase(password)) {
                JOptionPane.showMessageDialog(null, "Correct password! The door opens.");
                this.collision = false;
                this.opened = true;

                try {
                    image = ImageIO.read(getClass().getResourceAsStream("/res/objects/testDoorOpened.png")); 
                } catch(IOException e){
                    e.printStackTrace();
                }
            } else if (input != null) {
                JOptionPane.showMessageDialog(null, "Incorrect password.");
            }
        }
        //athena door
        else if (password.equals("athena")){
            String input = JOptionPane.showInputDialog(null, "The door is locked. Enter the password:");

            if (input != null && input.trim().equalsIgnoreCase(password)) {
                JOptionPane.showMessageDialog(null, "Correct password! The door opens.\nYou heard something from statues.");
                this.collision = false;
                this.opened = true;
                setChanged(true);

                try {
                    image = ImageIO.read(getClass().getResourceAsStream("/res/objects/testDoorOpened.png")); 
                } catch(IOException e){
                    e.printStackTrace();
                }
            } else if (input != null) {
                JOptionPane.showMessageDialog(null, "Incorrect password.");
            }
        }
        //yes door
        else if (password.equals("yes")){
            String input = JOptionPane.showInputDialog(null, "The door is locked. Enter the password:");

            if (input != null && input.trim().equalsIgnoreCase(password)) {
                JOptionPane.showMessageDialog(null, "Correct password! The door opens.");
                this.collision = false;
                this.opened = true;

                try {
                    image = ImageIO.read(getClass().getResourceAsStream("/res/objects/testDoorOpened.png")); 
                } catch(IOException e){
                    e.printStackTrace();
                }
            } else if (input != null) {
                JOptionPane.showMessageDialog(null, "Incorrect password.");
            }
        }
        
    }

    public static boolean isChanged() {
        return changed;
    }

    public static void setChanged(boolean changed) {
        OBJ_TestDoorClosed.changed = changed;
    }
}
