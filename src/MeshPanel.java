import javax.swing.*;
import java.awt.*;

/**
 * @author Paulo Sergio
 */
public class MeshPanel extends JPanel {
    private final int size = 40;
    private final int length = 15;
    private final Mesh mesh = new Mesh(this.size);

    {
        this.setPreferredSize(new Dimension(size * length, size * length));
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
                g.fillRect(j*length,i*length,length,length);
            }
        }
    }
}
