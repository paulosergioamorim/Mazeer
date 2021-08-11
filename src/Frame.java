import javax.swing.*;

public class Frame extends JFrame {

    private final MeshPanel meshPanel;

    public Frame() {
        super("Mazeer");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        meshPanel = new MeshPanel();
        this.setVisible(true);
        this.setContentPane(meshPanel);
        this.pack();
        this.setResizable(false);
    }

    public static void main(String[] args) {
        new Frame();
    }
}
