import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class WumpusPanel extends JPanel implements KeyListener {
    public static int PLAYING = 0;
    public static int DEAD = 1;
    public static int WON = 2;

    int status = -1;
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
    BufferedImage buffer = new BufferedImage(1000, 1000, BufferedImage.TYPE_4BYTE_ABGR);
    WumpusSquare[][] grid;
    WumpusPanel(){
        this.setPreferredSize(new Dimension(500,500));
        this.setVisible(true);
        WumpusMap w = new WumpusMap();
        grid = w.getGrid();
        setImages();
        repaint();
    }
    void reset(){};

    @Override
    public void paint(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0,0,getWidth(),getHeight());
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                WumpusSquare s = wm.getGrid()[i][j];
                g.drawImage(floor,i*50, j*50, null);
                if(s.isBreeze()){
                    g.drawImage(breeze, i*50, j*50, null);
                }
            }
        }

    };

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()){
            case 'w' ->{
                wp.setDirection(WumpusPlayer.NORTH);
            }
            case 's' ->{
                wp.setDirection(WumpusPlayer.SOUTH);
            }
            case 'a' ->{
                wp.setDirection(WumpusPlayer.WEST);
            }
            case 'd' ->{
                wp.setDirection(WumpusPlayer.EAST);
            }
            case 'i' ->{

            }
            case 'k' ->{

            }
            case 'j' ->{

            }
            case 'l' ->{

            }
            case 'c' ->{

            }
            case 'p' ->{

            }
            case 'n' ->{

            }
            case '*' ->{

            }
        }
    }

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
    @Override
    public void keyPressed(KeyEvent e) {
    }
    //useless
    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void addNotify(){
        super.addNotify();
        requestFocus();
    };

    //check for multiple booleans being true cause some squares have stench and wind

}
