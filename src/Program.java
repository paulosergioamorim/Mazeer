import javax.swing.*;

/**
 * @author Paulo Sergio
 */
public class Program extends JFrame {

    private final MeshPanel panel = new MeshPanel();

    /**
     * Create a window
     * @param title title of the window
     */
    public Program(String title) {
        super(title);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(panel);
        this.setResizable(false);
        this.pack();
    }

    public static void main(String[] args) {
        JFrame frame = new Program("Mazeer");
        frame.setVisible(true);
    }
}
