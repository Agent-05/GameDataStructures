import javax.swing.*;

public class WumpusFrame extends JFrame {
    public WumpusFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(new WumpusPanel());
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
