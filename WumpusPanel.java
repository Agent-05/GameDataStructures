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
        this.setPreferredSize(new Dimension(500,500));
        this.setVisible(true);
    }
    void reset(){};

    @Override
    public void paint(Graphics g){

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

}
