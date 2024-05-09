import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class WumpusPanel extends JPanel {
    public static int PLAYING = 0;
    public static int DEAD = 1;
    public static int WON = 2;

    WumpusFrame frame;
    int status = -1;
    int wumDeath = 0;
    boolean cheating = false;
    WumpusPlayer wp = new WumpusPlayer();
    WumpusMap wm = new WumpusMap();
    BufferedImage floor;
    BufferedImage arrow;
    BufferedImage fog;
    BufferedImage gold;
    BufferedImage ladder;
    BufferedImage pit;
    BufferedImage breeze;
    BufferedImage wumpus;
    BufferedImage deadWumpus;
    BufferedImage stench;
    BufferedImage playerUp;
    BufferedImage playerDown;
    BufferedImage playerLeft;
    BufferedImage playerRight;
    WumpusSquare[][] grid;
    String playerMessage = "";
    WumpusPanel(WumpusFrame frame){
        this.frame = frame;
        this.setPreferredSize(new Dimension(500,650));
        this.setVisible(true);
        WumpusMap w = new WumpusMap();
        this.setLayout(null);
        grid = w.getGrid();
        status = 0;
        setImages();
        repaint();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                WumpusSquare s = wm.getGrid()[i][j];
                if (s.isLadder()){
                    wp.setRowPosition(j);
                    wp.setColPosition(i);
                }
            }
        }

        KeyListener k = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                switch (e.getKeyChar()){
                    case 'w' ->{
                        if (status == 0) {
                            wp.setDirection(WumpusPlayer.NORTH);
                            int row = wp.getRowPosition();
                            int col = wp.getColPosition();
                            if (row - 1 >= 0) {
                                wp.setRowPosition(row - 1);
                                repaint();
                            }
                        }
                    }
                    case 's' ->{
                        if (status == 0) {
                            wp.setDirection(WumpusPlayer.SOUTH);
                            int row = wp.getRowPosition();
                            int col = wp.getColPosition();
                            if (row + 1 < 10) {
                                wp.setRowPosition(row + 1);
                                repaint();
                            }
                        }
                    }
                    case 'a' ->{
                        if (status == 0) {
                            wp.setDirection(WumpusPlayer.WEST);
                            int row = wp.getRowPosition();
                            int col = wp.getColPosition();
                            if (col - 1 >= 0) {
                                wp.setColPosition(col - 1);
                                repaint();
                            }
                        }
                    }
                    case 'd' ->{
                        if (status == 0) {
                            wp.setDirection(WumpusPlayer.EAST);
                            int row = wp.getRowPosition();
                            int col = wp.getColPosition();
                            if (col + 1 < 10) {
                                wp.setColPosition(col + 1);
                                repaint();
                            }
                        }
                    }
                    case 'i' ->{
                        //shoots upwards
                        if (status == 0 && wp.isArrow()) {
                            playerMessage = "You shot an arrow";
                            wp.setArrow(false);
                            int row = wp.getRowPosition();
                            int col = wp.getColPosition();
                            for (int i = 0; i <= wp.getRowPosition(); i++) {
                                WumpusSquare s = wm.getGrid()[col][row-i];
                                if (s.isWumpus()){
                                    s.setDeadWumpus(true);
                                    s.setWumpus(false);
                                    repaint();
                                    wumDeath = 1;
                                    playerMessage = "You hear a scream in the distance";
                                }
                            }
                        }
                        repaint();

                    }
                    case 'k' ->{
                        //shoots downwards
                        if (status == 0 && wp.isArrow()) {
                            playerMessage = "You shot an arrow";
                            wp.setArrow(false);
                            int row = wp.getRowPosition();
                            int col = wp.getColPosition();
                            int slots = 9 - wp.getRowPosition();
                            for (int i = 1; i <= slots; i++) {
                                WumpusSquare s = wm.getGrid()[col][row+i];
                                if (s.isWumpus()){
                                    s.setDeadWumpus(true);
                                    s.setWumpus(false);
                                    repaint();
                                    wumDeath = 1;
                                    playerMessage = "You hear a scream in the distance";

                                }
                            }
                        }
                        repaint();

                    }
                    case 'j' ->{
                        //shoots left
                        if (status == 0 && wp.isArrow()) {
                            playerMessage = "You shot an arrow";
                            wp.setArrow(false);
                            int row = wp.getRowPosition();
                            int col = wp.getColPosition();
                            for (int i = 0; i <= wp.getColPosition(); i++) {
                                WumpusSquare s = wm.getGrid()[col-i][row];
                                if (s.isWumpus()){
                                    s.setDeadWumpus(true);
                                    s.setWumpus(false);
                                    repaint();
                                    wumDeath = 1;
                                    playerMessage = "You hear a scream in the distance";

                                }
                            }
                        }
                        repaint();


                    }
                    case 'l' ->{
                        //shoots right
                        if (status == 0 && wp.isArrow()) {
                            playerMessage = "You shot an arrow";
                            wp.setArrow(false);
                            int row = wp.getRowPosition();
                            int col = wp.getColPosition();
                            int slots = 9 - wp.getColPosition();
                            for (int i = 1; i <= slots; i++) {
                                WumpusSquare s = wm.getGrid()[col+i][row];
                                if (s.isWumpus()){
                                    s.setDeadWumpus(true);
                                    s.setWumpus(false);
                                    repaint();
                                    wumDeath = 1;
                                    playerMessage = "You hear a scream in the distance";
                                }
                            }
                            repaint();

                        }

                    }
                    case 'c' ->{
                        int row = wp.getRowPosition();
                        int col = wp.getColPosition();
                        if (status == 0) {
                            WumpusSquare s = wm.getGrid()[col][row];
                            if (s.isLadder()){
                                if (status == 0) {
                                    if (wp.isGold() && wumDeath != 1){
                                        playerMessage = "The Wumpus is still alive, kill it";
                                    }
                                    else if (wp.isGold() && wumDeath == 1){
                                        playerMessage = "You escaped and ended the Wumpus. Congrats!!!";
                                        status = 2;
                                        wp.setRowPosition(100);
                                        wp.setColPosition(100);
                                    }
                                }
                                repaint();
                            }
                        }

                    }
                    case 'p' ->{
                        int row = wp.getRowPosition();
                        int col = wp.getColPosition();
                        if (status == 0) {
                            WumpusSquare s = wm.getGrid()[col][row];
                            if (s.isGold()){
                                s.setGold(false);
                                wp.setGold(true);
                                repaint();
                            }
                        }

                    }
                    case 'n' ->{
                        if (status == 1 || status == 2){
                            reset();
                        }
                    }
                    case '*' ->{
                        cheating = !cheating;
                        repaint();
                        System.out.println("*");
                    }
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
        this.addKeyListener(k);
    }
    void reset(){
        status = 0;
        wumDeath = 0;
        wm.createMap();
        grid = wm.getGrid();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                WumpusSquare s = wm.getGrid()[i][j];
                if (s.isLadder()){
                    wp.setRowPosition(j);
                    wp.setColPosition(i);
                }
            }
        }
        playerMessage = "";
        wp.setGold(false);
        wp.setArrow(true);
        repaint();

    };

    @Override
    public void paint(Graphics g){
        g.setColor(new Color(54, 51, 51));
        g.fillRect(0,0,getWidth(),getHeight());
        g.setColor(Color.BLACK);
        g.fillRect(150, 510, 340, 130);
        g.fillRect(10, 510, 130, 130);
        g.setColor(Color.RED);
        g.drawString("Inventory", 15, 525);
        g.drawString("Messages", 155, 525);
        if (wp.isGold()){
            g.drawImage(gold, 15, 550, 70, 70, null);
        }
        if (wp.isArrow()){
            g.drawImage(arrow, 75, 550, 70, 70, null);
        }
        int dir = wp.getDirection();
        int col = wp.getColPosition();
        int row = wp.getRowPosition();
        String curMessage = "";
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                WumpusSquare s = wm.getGrid()[i][j];
                if (j == row && i == col){
                    s.setVisited(true);
                    int messageNum = 0;

                    if(s.isGold()){
                        g.setColor(Color.blue);
                        curMessage = "You see a glimmer";
                        g.drawString(curMessage, 165, 545 + (messageNum * 15));
                        messageNum ++;
                    }
                    else if(s.isLadder()){
                        g.setColor(Color.blue);
                        curMessage = "You bump into a ladder";
                        g.drawString(curMessage, 165, 545 + (messageNum * 15));
                        messageNum ++;
                    }
                    else if(s.isPit()){
                        g.setColor(Color.blue);
                        curMessage = curMessage +"\nYou fell to your death";
                        g.drawString(curMessage, 165, 545 + (messageNum * 15));
                        messageNum ++;
                        status = 1;
                        wp.setColPosition(100);
                        wp.setRowPosition(100);

                    }
                    if (s.isStench() || s.isDeadWumpus()){
                        g.setColor(Color.blue);
                        curMessage = "You smell a stench";
                        g.drawString(curMessage, 165, 545 + (messageNum * 15));
                        messageNum ++;
                    }
                    if (s.isBreeze()){
                        g.setColor(Color.blue);
                        curMessage = "You feel a breeze";
                        g.drawString(curMessage, 165, 545 + (messageNum * 15));
                        messageNum ++;
                    }
                    if (s.isWumpus()){
                        g.setColor(Color.blue);
                        curMessage = "You are eaten by the Wumpus";
                        g.drawString(curMessage, 165, 545 + (messageNum * 15));
                        messageNum ++;
                        status = 1;
                        wp.setColPosition(100);
                        wp.setRowPosition(100);
                    }

                }
                if(cheating || s.isVisited())
                {
                    g.drawImage(floor,i*50, j*50, null);
                    if(s.isLadder()){
                        g.drawImage(ladder, i*50, j*50, null);
                    }
                    if(s.isPit()){
                        g.drawImage(pit, i*50, j*50, null);
                    }
                    if(s.isWumpus()){
                        g.drawImage(wumpus, i*50, j*50, null);
                    }
                    if (s.isDeadWumpus()){
                        g.drawImage(deadWumpus, i*50, j*50, null);
                    }
                    if(!s.isPit() && !s.isLadder())
                    {
                        if(s.isGold()){
                            g.drawImage(gold, i*50, j*50, null);
                        }
                        if(s.isBreeze()){
                            g.drawImage(breeze, i*50, j*50, null);
                        }
                        if(s.isStench()){
                            g.drawImage(stench, i*50, j*50, null);
                        }
                    }
                }else{
                    g.setColor(Color.BLACK);
                    g.fillRect(i*50, j*50, 50, 50);
                }
            }
        }
        switch (dir){
            case WumpusPlayer.NORTH -> {
                g.drawImage(playerUp, wp.getColPosition() * 50, wp.getRowPosition() * 50, null);
            }
            case WumpusPlayer.SOUTH -> {
                g.drawImage(playerDown,wp.getColPosition() * 50, wp.getRowPosition() * 50, null);

            }
            case WumpusPlayer.WEST -> {
                g.drawImage(playerLeft, wp.getColPosition() * 50, wp.getRowPosition() * 50, null);
            }
            case WumpusPlayer.EAST -> {
                g.drawImage(playerRight, wp.getColPosition() * 50, wp.getRowPosition() * 50, null);
            }
        }
        if (!playerMessage.isEmpty()){
            g.setColor(Color.BLACK);
            g.fillRect(150, 510, 340, 130);
            g.setColor(Color.RED);
            g.drawString("Inventory", 15, 525);
            g.drawString("Messages", 155, 525);

            g.setColor(Color.BLUE);
            g.drawString(playerMessage, 165, 545);
            playerMessage = "";
        }

    };


    public void setImages()
    {
        try
        {
            arrow = ImageIO.read((new File("Wumpus_World_Images\\arrow.gif")));
            //fog = ImageIO.read((new File("\\Wumpus_World_Image\\fog.gif")));
            gold = ImageIO.read((new File("Wumpus_World_Images\\gold.gif")));
            ladder = ImageIO.read((new File("Wumpus_World_Images\\ladder.gif")));
            floor = ImageIO.read((new File("Wumpus_World_Images\\Floor.gif")));
            pit = ImageIO.read((new File("Wumpus_World_Images\\pit.gif")));
            breeze = ImageIO.read((new File("Wumpus_World_Images\\breeze.gif")));
            wumpus = ImageIO.read((new File("Wumpus_World_Images\\wumpus.gif")));
            deadWumpus = ImageIO.read((new File("Wumpus_World_Images\\deadwumpus.gif")));
            stench = ImageIO.read((new File("Wumpus_World_Images\\stench.gif")));
            playerUp = ImageIO.read((new File("Wumpus_World_Images\\playerUp.png")));
            playerDown = ImageIO.read((new File("Wumpus_World_Images\\playerDown.png")));
            playerLeft =ImageIO.read((new File("Wumpus_World_Images\\playerLeft.png")));
            playerRight =ImageIO.read((new File("Wumpus_World_Images\\playerRight.png")));
        }
        catch(Exception e)
        {
            System.out.println("Error Loading Images: " + e.getMessage());
            e.printStackTrace();
        }

    }

    //useless
    public void addNotify(){
        super.addNotify();
        this.requestFocus();
    };

    public void updateMessage(String str){
        Graphics g = this.getGraphics();
        g.drawString(str, 165, 545);
    }

    //check for multiple booleans being true cause some squares have stench and wind

}
