package mainn;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Collections;
import java.util.ArrayList;
import weapon.SuperWeapon;

public class WeaponListPanel extends JPanel implements ActionListener {
    public static ArrayList<SuperWeapon> weapons;
    public ArrayList<JButton> weaponButtons = new ArrayList<>();

    public int firstIndx = -1, secondIndx = -1;
    public boolean firstSelected = true;

    public GamePanel gp;
    public int frameX, frameY, frameWidth, frameHeight;

    public WeaponListPanel(GamePanel gp) {
        this.gp = gp;
        setPreferredSize(new Dimension(gp.screenWidth, gp.screenHeight));
        setLayout(null);
        setBackground(Color.BLACK);

        int margin = 50;
        frameX = margin;
        frameY = margin;
        frameWidth = gp.screenWidth - 2 * margin;
        frameHeight = gp.screenHeight - 2 * margin;
        weapons = new ArrayList<>();

        refreshWeaponList();
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
    }

    private void swapIndexes(int idx1, int idx2) {
        Collections.swap(gp.player.weapons, idx1, idx2);
        firstIndx = secondIndx = -1;
        firstSelected = true;
        refreshWeaponList();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();
        int idx = weaponButtons.indexOf(src);
        if (idx < 0) return;

        if (firstSelected) {
            firstIndx = idx;
            src.setBorder(new LineBorder(Color.WHITE, 3));
            firstSelected = false;
        } else {
            secondIndx = idx;
            src.setBorder(new LineBorder(Color.WHITE, 3));
            swapIndexes(firstIndx, secondIndx);
        }
    }

    public void refreshWeaponList() {
        removeAll();
        weaponButtons.clear();

        weapons = new ArrayList<>(gp.player.weapons);

        int cols = 3, padding = 10;
        int btnW = (frameWidth - (cols + 1) * padding) / cols;
        int btnH = 80;

        for (int i = 0; i < weapons.size(); i++) {
            SuperWeapon w = weapons.get(i);
            JButton btn = new JButton(w.name, new ImageIcon(w.imageRight));
            btn.setHorizontalTextPosition(SwingConstants.CENTER);
            btn.setVerticalTextPosition(SwingConstants.BOTTOM);
            btn.addActionListener(this);

            int row = i / cols, col = i % cols;
            int x = frameX + padding + col * (btnW + padding);
            int y = frameY + padding + row * (btnH + padding);
            btn.setBounds(x, y, btnW, btnH);

            add(btn);
            weaponButtons.add(btn);
        }

        revalidate();
        repaint();
    }
}
