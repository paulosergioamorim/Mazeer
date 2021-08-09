import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    private static final int SIZE = 800;

    private final Maze maze = new Maze();

    public MainPanel() {
        this.setPreferredSize(new Dimension(SIZE,SIZE));
        maze.showMaze();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.createMesh(g);
    }

    private void createMesh(Graphics g) {
        for (int i = 0; i < maze.field[0].length; i++) {
            for (int j = 0; j < maze.field.length ; j++) {
                if (
                        i == maze.size - 2
                                && j == maze.size - 2
                ) g.setColor(new Color(0x5096FF));
                else if (maze.field[i][j] == 0) g.setColor(Color.white);
                else g.setColor(Color.black);
                g.fillRect(j*20,i*20,20,20);
            }
        }

    }
}
