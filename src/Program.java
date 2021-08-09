import javax.swing.*;

public class Program extends JFrame {
    public Program() {
        super("Mazeer");

        JPanel mainPanel = new MainPanel();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setVisible(true);
        this.pack();

        this.setResizable(false);
    }

    public static void main(String[] args) {
        new Program();
    }
}
