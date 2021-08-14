import javax.swing.*;

/**
 * @author Paulo Sergio
 */


public class Program extends JFrame {

    private final MeshPanel meshPanel = new MeshPanel();

    public Program() {
        super("Mazeer");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(meshPanel);
        this.setVisible(true);
        this.setResizable(false);
        this.pack();
    }

    public static void main(String[] args) {
        new Program();
    }
}
