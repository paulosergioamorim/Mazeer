import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mesh {
    public Integer[][] field;
    public final int size;

    public final List<Point> points = new ArrayList<>();
    public final List<Point> path = new ArrayList<>();

    public Mesh(int size) {
        this.size = size;
        field = new Integer[size][size];

        this.setBounds();
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

    public void findPath() {
        Point actual = new Point(1,1);
        Point end = new Point(size-2,size-2);

        while (true) {
            if (actual.equals(end)) return;

            double north = disperse(new Point(actual.x+1,actual.y));
            double south = disperse(new Point(actual.x-1,actual.y));
            double rigth = disperse(new Point(actual.x,actual.y+1));
            double left = disperse(new Point(actual.x,actual.y-1));

            double max = Double.max(Double.max(north, south), Double.max(rigth, left));

            if (max == north) {
                actual = new Point(actual.x+1,actual.y);
            }
            else if (max == south) {
                actual = new Point(actual.x-1,actual.y);
            }
            else if (max == rigth) {
                actual = new Point(actual.x,actual.y+1);
            }
            else if (max == left) {
                actual = new Point(actual.x,actual.y-1);
            } path.add(actual);

        }
    }

    public double disperse(Point init) {
        Point end = new Point(size-2,size-2);

        for (int i = 0; i < 10 ; i++) {
            if (init.equals(end)) {
                System.out.println("End");
                return 1 / (init.distance(end) + 1);
            }

            Random random = new Random();
            List<Point> candidates = new ArrayList<>();

            Point north = new Point(init.x+1,init.y);
            Point south = new Point(init.x-1,init.y);
            Point rigth = new Point(init.x, init.y+1);
            Point left = new Point(init.x, init.y-1);

            if (field[north.x][north.y] == null) candidates.add(north);
            if (field[south.x][south.y] == null) candidates.add(south);
            if (field[rigth.x][rigth.y] == null) candidates.add(rigth);
            if (field[left.x][left.y] == null) candidates.add(left);

            candidates.removeIf(point -> (
                    point.x == 0
                            || point.x == size - 1
                            || point.y == 0
                            || point.y == size - 1
            ));


            if (candidates.isEmpty()) {
                return 1 / (init.distance(end) + 1);
            } else {
                int result = random.nextInt(candidates.size());
                init = candidates.get(result);
            }
        }
        return 1 / (init.distance(end) + 1);
    }

    public String pathToString() {
        String string = "";
        for (Point point: path) string += String.format("(%d,%d) ",point.x,point.y);
        return  string;
    }

    public String fieldToString() {
        String string = "";
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++)
                string += field[i][j] + " ";
            string += "\n";
        } return string;
    }

    public static void main(String[] args) {
        Mesh mesh = new Mesh(10);
        mesh.findPath();
        System.out.println(mesh.fieldToString());
        System.out.println(mesh.pathToString());
    }
}
