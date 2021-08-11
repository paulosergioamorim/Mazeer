import javax.swing.*;
import java.awt.*;

public class MeshPanel extends JPanel {

    private final Mesh mesh;

    public MeshPanel() {
        this.setPreferredSize(new Dimension(800,800));
        mesh = new Mesh();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.drawMesh(g);
    }

    private void drawMesh(Graphics g) {
        for (int i = 0; i < mesh.field[0].length; i++) {
            for (int j = 0; j < mesh.field.length ; j++) {
                if (mesh.field[i][j] == 0) g.setColor(Color.white);
                else g.setColor(Color.black);
                g.fillRect(j*20,i*20,20,20);
            }
        }
    }
}
