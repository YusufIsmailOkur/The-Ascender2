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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import object.SuperObject;
import weapon.SuperWeapon;

public class Inventory extends JPanel implements ActionListener {
    private GamePanel gp;
    private Font titleFont, sectionFont;

    private int slotCol = 4, slotSize = 48, slotGap = 5;
    private int frameX, frameY, frameWidth, frameHeight;
    private int slotsStartX, slotsStartY;

    private ArrayList<SuperObject> allObjects;
    private ArrayList<SuperWeapon> allWeapons;
    private ArrayList<SuperObject> displayObjects;
    private ArrayList<SuperWeapon> displayWeapons;

    private JTextField searchField;
    private JButton craftToggle;
    private boolean craftMode = false;
    private ArrayList<JButton> craftButtons = new ArrayList<>();//ALL craftable objects will have one craft button(Might change)

    public Inventory(GamePanel gp) {
        this.gp = gp;
        setPreferredSize(new Dimension(gp.screenWidth, gp.screenHeight));
        setLayout(null);
        setBackground(Color.BLACK);

        titleFont = new Font("Arial", Font.BOLD, 60);
        sectionFont = new Font("Arial", Font.PLAIN, 30);

        int margin = 50;
        frameX = margin;
        frameY = margin;
        frameWidth = gp.screenWidth - 2 * margin;
        frameHeight = gp.screenHeight - 2 * margin;

        slotsStartX = frameX + 20;
        slotsStartY = frameY + 120;

        allObjects = gp.player.objects;
        allWeapons = gp.player.weapons;
        displayObjects = new ArrayList<>(allObjects);
        displayWeapons = new ArrayList<>(allWeapons);

        //DYNAMIC Search Bar
        searchField = new JTextField();
        searchField.setFont(sectionFont);
        int sfW = 200, sfH = 40;
        int sfX = frameX + frameWidth - sfW - 20;
        int sfY = frameY + 20;
        searchField.setBounds(sfX, sfY, sfW, sfH);
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) { applyFilters(); }
            @Override public void removeUpdate(DocumentEvent e) { applyFilters(); }
            @Override public void changedUpdate(DocumentEvent e) { applyFilters(); }
        });
        add(searchField);

        // Craft Mode BUTTON
        craftToggle = new JButton("Craft Mode");
        craftToggle.setFont(new Font("Arial", Font.PLAIN, 20));
        int ctW = 200, ctH = 40;
        int ctX = sfX + sfW + 10 - 200;
        int ctY = sfY*2;
        craftToggle.setBounds(ctX, ctY, ctW, ctH);
        craftToggle.addActionListener(this);
        add(craftToggle);

        //EXIT BUTTON
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 20));
        int ebW = 100, ebH = 40;
        int ebX = frameX + frameWidth - ebW - 20;
        int ebY = frameY + frameHeight - ebH - 20;
        exitButton.setBounds(ebX, ebY, ebW, ebH);
        exitButton.addActionListener(e -> {
            // 1) switch back to play state
            gp.gameState = gp.playState;

            // 2) hide the inventory overlay
            this.setVisible(false);

        });
        add(exitButton);

        // FILTER AT BEGINNING
        applyFilters();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == craftToggle) {
            craftMode = !craftMode;
            if(craftMode)
            {
                craftToggle.setText("Exit Craft");
            }
            else
            {
                craftToggle.setText("Craft Mode");
            }
            applyFilters();//Apply filters after action
        } else if (craftButtons.contains(e.getSource())) {
            String itemName = ((JButton) e.getSource()).getActionCommand();
            JOptionPane.showMessageDialog(this, "Crafting: " + itemName);
        }
    }

    private void applyFilters() {
        String term = searchField.getText().trim().toLowerCase();

        displayObjects.clear();
        for (SuperObject obj : allObjects) {
            boolean matchesSearch = obj.name.toLowerCase().contains(term);
            boolean matchesCraft = !craftMode || obj.craftable;
            if (matchesSearch && matchesCraft) {
                displayObjects.add(obj);
            }
        }

        displayWeapons.clear();
        for (SuperWeapon wp : allWeapons) {
            boolean matchesSearch = wp.name.toLowerCase().contains(term);
            boolean matchesCraft = !craftMode || wp.craftable;
            if(!craftMode)

            if (matchesSearch && matchesCraft) {
                displayWeapons.add(wp);
            }
        }

        if (displayObjects.isEmpty() && displayWeapons.isEmpty()) {
            if (!term.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Could not find \"" + term + "\" item.");
            }
            // ShOW ALL if EMPtY
            if (!craftMode) {
                displayObjects.addAll(allObjects);
                displayWeapons.addAll(allWeapons);
            }
        }

        refreshCraftButtons();
        repaint();
    }

    private void refreshCraftButtons() {
        for (JButton b : craftButtons)
        {
            remove(b);
        }
        craftButtons.clear();

        if (!craftMode)
        {
            return;
        }

        for (int oindx = 0; oindx < displayObjects.size(); oindx++)
        {
            SuperObject obj = displayObjects.get(oindx);
            if (obj.craftable)
            {
                addCraftButton(obj.name, oindx);
            }
        }
        int base = slotCol * 2;
        for (int windx = 0; windx < displayWeapons.size(); windx++)
        {
            SuperWeapon wp = displayWeapons.get(windx);
            if (wp.craftable)
            {
                addCraftButton(wp.name, base + windx);
            }
        }
        revalidate();
    }

    private void addCraftButton(String name, int idx) {
        int col = idx % slotCol;
        int row = idx / slotCol;
        int x = slotsStartX + col * (slotSize + slotGap) + 10;
        int y = slotsStartY + row * (slotSize + slotGap) - 30;
        JButton btn = new JButton("Craft");
        btn.setFont(new Font("Arial", Font.PLAIN, 14));
        btn.setBounds(x, y, 60, 20);
        btn.setActionCommand(name);
        btn.addActionListener(this);
        add(btn);
        craftButtons.add(btn);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(frameX, frameY, frameWidth, frameHeight);

        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(4));
        g2.drawRect(frameX, frameY, frameWidth, frameHeight);

        g2.setFont(titleFont);
        g2.drawString("INVENTORY", frameX + 20, frameY + 60);

        g2.setFont(sectionFont);
        g2.drawString("OBJECTS", frameX + 20, frameY + 100);
        drawSlots(g2, displayObjects, slotsStartY);



        g2.setFont(sectionFont);
        g2.setColor(Color.WHITE);
        int weaponsY = slotsStartY + 4 * (slotSize + slotGap) + 40;
        g2.drawString("WEAPONS", frameX + 20, weaponsY - 10);
        drawSlots(g2, displayWeapons, weaponsY);

        //Highlight craftable objects!!!!!!!!!!!Might NOT work properly
        if (craftMode)
        {
            g2.setColor(Color.YELLOW);
            g2.setStroke(new BasicStroke(3));
        }
    }

    //DRAW SLOTS even EMPTY
    private void drawSlots(Graphics2D g2, ArrayList<?> items, int startY) {
        g2.setStroke(new BasicStroke(2));
        for (int row = 0; row < 2; row++)
        {
            for (int col = 0; col < slotCol; col++)
            {
                int x = slotsStartX + col * (slotSize + slotGap);
                int y = startY + row * (slotSize + slotGap);

                g2.setColor(Color.BLACK);
                g2.fillRect(x, y, slotSize, slotSize);

                g2.setColor(Color.GRAY);
                g2.drawRect(x, y, slotSize, slotSize);

                int idx = row * slotCol + col;
                if (idx < items.size()) {
                    Object obj = items.get(idx);
                    if (obj instanceof SuperObject so) {
                        g2.drawImage(so.image, x, y, slotSize, slotSize, null);
                        //WRITE NAME
                        g2.setColor(Color.WHITE);
                        g2.setFont(new Font("Arial", Font.PLAIN, 15));
                        g2.drawString(so.name, x, y + slotGap + 60);
                    }
                    else if (obj instanceof SuperWeapon sw) {
                        g2.drawImage(sw.imageRight, x, y, slotSize, slotSize, null);
                        //WRITE NAME
                        g2.setColor(Color.WHITE);
                        g2.setFont(new Font("Arial", Font.PLAIN, 15));
                        g2.drawString(sw.name, x, y+slotGap+ 60);

                    }
                }
            }
            //ADDING SPACE BETWEEN ROWS
            startY += slotSize + slotGap;   
        }
    }
}
