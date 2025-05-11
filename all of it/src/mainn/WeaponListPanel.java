package mainn;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import weapon.SuperWeapon;

public class WeaponListPanel extends JPanel implements ActionListener {
    public static ArrayList<SuperWeapon> weapons = new ArrayList<>();
    private ArrayList<JButton> weaponButtons = new ArrayList<>();

    private int firstIndx = -1, secondIndx = -1;
    private boolean firstSelected = true;

    private GamePanel gp;
    private int frameX, frameY, frameWidth, frameHeight;

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

        int weaponCount = gp.player.weapons.size();
        for (int i = 0; i < weaponCount; i++) {
            weapons.add(gp.player.weapons.get(i));
        }

        int cols = 3;
        int padding = 10;
        int btnW = (frameWidth - (cols + 1) * padding) / cols;
        int btnH = 80;

        for (int i = 0; i < weapons.size(); i++) {
            SuperWeapon w = weapons.get(i);
            JButton btn = new JButton(w.name);
            btn.setIcon(new ImageIcon(w.imageRight));
            btn.setHorizontalTextPosition(SwingConstants.CENTER);
            btn.setVerticalTextPosition(SwingConstants.BOTTOM);
            btn.addActionListener(this);

            int row = i / cols;
            int col = i % cols;
            int x = frameX + padding + col * (btnW + padding);
            int y = frameY + padding + row * (btnH + padding);

            btn.setBounds(x, y, btnW, btnH);
            add(btn);
            weaponButtons.add(btn);
        }
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
        Collections.swap(weapons, idx1, idx2);
        Collections.swap(weaponButtons, idx1, idx2);

        removeAll();
        int cols = 3;
        int padding = 10;
        int btnW = (frameWidth - (cols + 1) * padding) / cols;
        int btnH = 80;

        for (int i = 0; i < weaponButtons.size(); i++) {
            JButton btn = weaponButtons.get(i);
            SuperWeapon w = weapons.get(i);
            btn.setText(w.name);
            if (w.imageRight instanceof Icon) {
                btn.setIcon((Icon) w.imageRight);
            } else {
                btn.setIcon(new ImageIcon(w.imageRight));
            }

            int row = i / cols;
            int col = i % cols;
            int x = frameX + padding + col * (btnW + padding);
            int y = frameY + padding + row * (btnH + padding);
            btn.setBounds(x, y, btnW, btnH);
            btn.setBorder(null);
            add(btn);
        }

        revalidate();
        repaint();

        firstIndx = secondIndx = -1;
        firstSelected = true;
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
}
