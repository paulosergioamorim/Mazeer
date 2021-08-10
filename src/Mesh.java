import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Mesh {
    public int[][] field;
    public final int size;

    public final List<Point> points = new ArrayList<>();
    public final List<Point> path = new ArrayList<>();

    public Mesh(int size) {
        this.size = size;
        field = new int[size][size];
    }

    public void setBounds() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if (
                        i == 0
                     || i == size - 1
                     || j == 0
                     || j == size -1
                ) field[i][j] = 1;
            }
        }
    }

    public List<Point> findPath() {
        Point actual = new Point(1,1);
        Point end = new Point(size-2,size-2);

        while (true) {
            if (actual.equals(end)) return path;

            Point north = new Point(actual.x,actual.y+1);
            Point south = new Point(actual.x,actual.y-1);
            Point rigth = new Point(actual.x+1, actual.y);
            Point left = new Point(actual.x-1, actual.y);
        }
    }

    public String fieldToString() {
        String string = null;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++)
                string += field[i][j] + " ";
            string += "\n";
        } return string;
    }
}
