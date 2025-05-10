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
            try (Scanner fileScanner = new Scanner(new File("names.txt"))) {
                while (fileScanner.hasNextLine()) {
                    if (fileScanner.nextLine().equals("Start")) {
                        String name = fileScanner.nextLine();
                        long time = 0;
                        if (fileScanner.hasNextLine()){
                            fileScanner.nextLine();
                        }
                        if (fileScanner.hasNextLine()) {
                            fileScanner.nextLine();
                        }
                        if (fileScanner.hasNextLine()) {
                            time = Long.parseLong(fileScanner.nextLine());
                        }
                        playerAndTimeList.add(new PlayerAndTime(name, time));
                    }
                }
                playerAndTimeList = new ArrayList<>(playerAndTimeList);
                sortBasedOnTime(playerAndTimeList);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
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
