import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {
    public int x,y;
    private int dx, dy;

    private final Image image;

    public Player(int x, int y) {
        this.x = y * 20 + 2;
        this.y = x * 20 + 2;
        image = new ImageIcon("src/player.png").getImage();
    }

    public void update() {
        x += dx;
        y += dy;
    }

    public void keyPressed(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP -> dy = -2;
            case KeyEvent.VK_DOWN -> dy = 2;
            case KeyEvent.VK_RIGHT -> dx = 2;
            case KeyEvent.VK_LEFT -> dx = -2;
        }
    }

    public void keyReleased(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP, KeyEvent.VK_DOWN -> dy = 0;
            case KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT -> dx = 0;
        }
    }

    public Image getImage() {
        return image;
    }
}
