package mainn;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import object.SuperObject;
import weapon.SuperWeapon;

public class Inventory extends JPanel implements ActionListener {
    private GamePanel gp;
    private Font arial_40, arial_80B;
    private int screenX, screenY;
    private int slotCol = 4, slotRow = 4, slotSize = 48, slotGap = 5;
    private int frameX, frameY, frameWidth, frameHeight;

    private ArrayList<SuperObject> allObjects;
    private ArrayList<SuperWeapon> allWeapons;
    private ArrayList<SuperObject> displayObjects;
    private ArrayList<SuperWeapon> displayWeapons;

    private JTextField searchField;
    private JButton craftToggle;
    private boolean craftMode = false;
    private ArrayList<JButton> craftButtons = new ArrayList<>();

    public Inventory(GamePanel gp) {
        this.gp = gp;
        setPreferredSize(new Dimension(gp.screenWidth, gp.screenHeight));
        setLayout(null);

        // Fonts
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);

        // Coordinates
        screenX = gp.screenWidth / 2 - (slotCol * slotSize + (slotCol - 1) * slotGap) / 2;
        screenY = gp.screenHeight / 2 - (slotRow * slotSize + (slotRow - 1) * slotGap) / 2;
        frameX = screenX - 10;
        frameY = screenY - 10;
        frameWidth = (slotCol * slotSize + (slotCol - 1) * slotGap) + 20;
        frameHeight = (slotRow * slotSize + (slotRow - 1) * slotGap) + 20;

        // Get lists
        allObjects = gp.player.objects;
        allWeapons = gp.player.weapons;
        displayObjects = new ArrayList<>(allObjects);
        displayWeapons = new ArrayList<>(allWeapons);

        // Search field
        searchField = new JTextField();
        searchField.setFont(arial_40);
        searchField.setBounds(frameX + frameWidth - 200, frameY + 10, 180, 40);
        searchField.addActionListener(this);
        add(searchField);

        // Craft toggle button
        craftToggle = new JButton("Craft");
        craftToggle.setFont(new Font("Arial", Font.PLAIN, 20));
        craftToggle.setBounds(frameX + 20, frameY + frameHeight + 10, 100, 40);
        craftToggle.addActionListener(this);
        add(craftToggle);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchField) {
            filterItems(searchField.getText().trim().toLowerCase());
        } else if (e.getSource() == craftToggle) {
            craftMode = !craftMode;
            craftToggle.setText(craftMode ? "Exit Craft" : "Craft");
            refreshCraftButtons();
        } else if (craftButtons.contains(e.getSource())) {
            JButton btn = (JButton) e.getSource();
            String itemName = btn.getActionCommand();
            JOptionPane.showMessageDialog(this, "Crafting: " + itemName);
            // TODO: implement actual crafting logic here
        }
    }

    private void filterItems(String term) {
        displayObjects.clear();
        displayWeapons.clear();
        for (SuperObject obj : allObjects) {
            if (obj.name.toLowerCase().contains(term)) displayObjects.add(obj);
        }
        for (SuperWeapon wp : allWeapons) {
            if (wp.name.toLowerCase().contains(term)) displayWeapons.add(wp);
        }
        if (displayObjects.isEmpty() && displayWeapons.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Could not find " + term + " item.");
            displayObjects.addAll(allObjects);
            displayWeapons.addAll(allWeapons);
        }
        refreshCraftButtons();
        repaint();
    }

    private void refreshCraftButtons() {
        // Remove old craft buttons
        for (JButton btn : craftButtons) remove(btn);
        craftButtons.clear();

        if (craftMode) {
            int idx = 0;
            // Objects
            for (SuperObject obj : displayObjects) {
                if (obj.craftable) {
                    int col = idx % slotCol;
                    int row = idx / slotCol;
                    int x = screenX + col * (slotSize + slotGap);
                    int y = screenY + row * (slotSize + slotGap) - 25;
                    JButton btn = new JButton("Craft");
                    btn.setFont(new Font("Arial", Font.PLAIN, 14));
                    btn.setBounds(x, y, 60, 20);
                    btn.setActionCommand(obj.name);
                    btn.addActionListener(this);
                    add(btn);
                    craftButtons.add(btn);
                }
                idx++;
            }
            // Weapons
            for (SuperWeapon wp : displayWeapons) {
                if (wp.craftable) {
                    int col = idx % slotCol;
                    int row = idx / slotCol;
                    int x = screenX + col * (slotSize + slotGap);
                    int y = screenY + row * (slotSize + slotGap) - 25;
                    JButton btn = new JButton("Craft");
                    btn.setFont(new Font("Arial", Font.PLAIN, 14));
                    btn.setBounds(x, y, 60, 20);
                    btn.setActionCommand(wp.name);
                    btn.addActionListener(this);
                    add(btn);
                    craftButtons.add(btn);
                }
                idx++;
            }
        }

        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Background frame
        g2.setColor(Color.BLACK);
        g2.fillRect(frameX, frameY, frameWidth, frameHeight);
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(5));
        g2.drawRect(frameX, frameY, frameWidth, frameHeight);

        // Header
        g2.setFont(arial_80B);
        g2.drawString("INVENTORY", frameX + 20, frameY + 80);

        // Draw objects
        int idx = 0;
        for (SuperObject obj : displayObjects) {
            int col = idx % slotCol;
            int row = idx / slotCol;
            int x = screenX + col * (slotSize + slotGap);
            int y = screenY + row * (slotSize + slotGap);
            if (obj != null && obj.image != null) {
                g2.drawImage(obj.image, x, y, slotSize, slotSize, null);
            }
            idx++;
        }
        // Draw weapons
        for (SuperWeapon wp : displayWeapons) {
            int col = idx % slotCol;
            int row = idx / slotCol;
            int x = screenX + col * (slotSize + slotGap);
            int y = screenY + row * (slotSize + slotGap);
            if (wp != null && wp.imageRight != null) {
                g2.drawImage(wp.imageRight, x, y, slotSize, slotSize, null);
            }
            idx++;
        }
    }
}
