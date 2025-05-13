package mainn;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class LeaderBoard extends JPanel {
    public ArrayList<PlayerAndTime> playerAndTimeList = new ArrayList<>();


    private GamePanel gp;

    private int frameX, frameY, frameWidth, frameHeight;

    LeaderBoard(GamePanel gp) {
        this.gp = gp;
        setPreferredSize(new Dimension(gp.screenWidth, gp.screenHeight));
        setLayout(null);
        setBackground(Color.BLACK);

        int margin = 50;
        frameX = margin;
        frameY = margin;
        frameWidth = gp.screenWidth - 2 * margin;
        frameHeight = gp.screenHeight - 2 * margin;

        getPlayerNameAndTimeAddToList();
    }

    public void getPlayerNameAndTimeAddToList() {
        ArrayList<String> lines = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(new File("names.txt"))) {
            while (fileScanner.hasNextLine()) {
                lines.add(fileScanner.nextLine().trim());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        for (int i = 0; i < lines.size(); i++) {
            if ("Start".equals(lines.get(i))) {
                int endIdx = -1;
                for (int j = i + 1; j < lines.size(); j++) {
                    if ("End".equals(lines.get(j))) {
                        endIdx = j;
                        break;
                    }
                }

                if (endIdx == -1) {
                    break;
                }

                ArrayList<String> block = new ArrayList<>(lines.subList(i , endIdx));

                if (block.size() > 6 && (block.get(5).equals("true"))) {
                    String name = block.get(1);
                    long time;
                    try {
                        time = Long.parseLong(block.get(4));
                    } catch (NumberFormatException nfe) {
                        System.out.println("sex");
                        i = endIdx;
                        continue;
                    }
                    playerAndTimeList.add(new PlayerAndTime(name, time));
                }
                i = endIdx;
            }
        }

        sortBasedOnTime(playerAndTimeList);
    }


    public void sortBasedOnTime(ArrayList<PlayerAndTime> arrList) {
        for(int i=1; i<arrList.size(); i++) {
            PlayerAndTime chosen = arrList.get(i);
            int j = i - 1;
            while(j>=0 && arrList.get(j).time > chosen.time) {
                arrList.set(j+1, arrList.get(j));
                j--;
            }
            arrList.set(j+1, chosen);
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(frameX, frameY, frameWidth, frameHeight);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 60));
        String title = "Leader Board";
        int titleWidth = g.getFontMetrics().stringWidth(title);
        g.drawString(title, frameX + (frameWidth - titleWidth) / 2, frameY + 80);

        g.setFont(new Font("Arial", Font.PLAIN, 30));
        int y = frameY + 150;
        if (playerAndTimeList.isEmpty()) {
            g.drawString("No data available", frameX + 20, y);
        } else {
            for (PlayerAndTime player : playerAndTimeList) {
                String text = player.name + ": " + player.time;
                g.drawString(text.substring(0,text.length()-3), frameX + 20, y);
                y += 40;
            }
        }
        //Write user
        if(gp.player.name!=null) {
            g.drawString("You", frameX + 20, y + 40);
            long timeS = KeyHandler.timeStart;
            String text2 = gp.player.name + ": " + (System.currentTimeMillis() - timeS);
            g.drawString(text2.substring(0, text2.length() - 4) + gp.player.totalTime, frameX, y + 80);
        }
    }

    static class PlayerAndTime {
        String name;
        long time;

        public PlayerAndTime(String name, long time) {
            this.name = name;
            this.time = time;
        }
    }
}
