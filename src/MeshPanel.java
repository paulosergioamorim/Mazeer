import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MeshPanel extends JPanel {
    private final Mesh mesh;
    private final Player player;

    public MeshPanel() {
        this.setPreferredSize(new Dimension(600,600));
        this.setFocusable(true);
        this.setDoubleBuffered(true);
        mesh = new Mesh(30);

        Point init = mesh.getPath().get(0);
        player = new Player(init.x,init.y);

        addKeyListener(new KeyBoard());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.drawMesh(g);
        this.drawPlayer(g);
    }

    private void drawPlayer(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        graphics.drawImage(player.getImage(),player.x, player.y, this);
    }

    private void drawMesh(Graphics g) {
        for (int i = 0; i < mesh.field[0].length; i++) {
            for (int j = 0; j < mesh.field.length ; j++) {
                if (
                        i == mesh.getPath().get(mesh.getPath().size()-1).x
                     && j == mesh.getPath().get(mesh.getPath().size()-1).y
                ) g.setColor(Color.GREEN);
                else if (mesh.field[i][j] == 0) g.setColor(Color.WHITE);
                else g.setColor(Color.BLACK);
                g.fillRect(j*20,i*20,20,20);
            }
        }
    }

    private class KeyBoard extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            player.keyPressed(e);
            player.update();
            repaint();
        }
        @Override
        public void keyReleased(KeyEvent e) {
            super.keyReleased(e);
            player.keyReleased(e);
            player.update();
            repaint();
        }
    }
}
