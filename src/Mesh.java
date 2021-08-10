import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

            Random random = new Random();
            List<Point> candidates = new ArrayList<>();

            Point north = new Point(actual.x,actual.y+1);
            Point south = new Point(actual.x,actual.y-1);
            Point rigth = new Point(actual.x+1, actual.y);
            Point left = new Point(actual.x-1, actual.y);

            if (field[north.x][north.y] != 1) candidates.add(north);
            if (field[south.x][south.y] != 1) candidates.add(south);
            if (field[rigth.x][rigth.y] != 1) candidates.add(rigth);
            if (field[left.x][left.y] != 1) candidates.add(left);

            candidates.removeIf(path::contains);

            int result = random.nextInt(candidates.size());
            actual = candidates.get(result);
            path.add(actual);
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
