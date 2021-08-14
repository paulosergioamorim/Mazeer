import javax.swing.*;
import java.awt.*;

/**
 * @author Paulo Sergio
 */

public class MeshPanel extends JPanel {
    private final Mesh mesh;

    public MeshPanel() {
        this.setPreferredSize(new Dimension(600,600));
        mesh = new Mesh(30);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.drawMesh(g);
    }

    private void drawMesh(Graphics g) {
        for (int i = 0; i < mesh.field[0].length; i++) {
            for (int j = 0; j < mesh.field.length ; j++) {
                if (
                        i == mesh.getPath().get(mesh.getPath().size()-1).x
                     && j == mesh.getPath().get(mesh.getPath().size()-1).y
                ) g.setColor(Color.CYAN);
                else if (mesh.field[i][j] == 0) g.setColor(Color.WHITE);
                else g.setColor(Color.BLACK);
                g.fillRect(j*20,i*20,20,20);
            }
        }
    }
}
