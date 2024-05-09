import javax.swing.*;

public class WumpusFrame extends JFrame {
    WumpusPanel wp;
    public WumpusFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        wp = new WumpusPanel(this);
        this.add(wp);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
