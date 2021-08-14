import javax.swing.*;

public class Frame extends JFrame {

    private final Panel panel = new Panel();

    public Frame() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(panel);
        this.setVisible(true);
        this.setResizable(false);
        this.pack();
    }

    public static void main(String[] args) {
        new Frame();
    }
}
