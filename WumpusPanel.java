import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

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
    BufferedImage buffer;

    WumpusPanel(){

    }
    void reset(){};

    @Override
    public void paint(Graphics g){};

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void addNotify(){};
}
