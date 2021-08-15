import javax.swing.*;
import java.awt.*;

/**
 * @author Paulo Sergio
 */
public class MeshPanel extends JPanel {
    private final Mesh mesh = new Mesh(50);

    {
        this.setPreferredSize(new Dimension(800,800));
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
                g.fillRect(j*16,i*16,16,16);
            }
        }
    }
}
