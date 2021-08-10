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
        field[1][1] = 0;
    }

    private final Point actual = new Point(1,1);
    private Point end;
    private Point north;
    private Point south;
    private Point rigth;
    private Point left;

    private void setExternalPoints(Point actual) {
        north = new Point(actual.x+1, actual.y);
        south = new Point(actual.x-1, actual.y);
        rigth = new Point(actual.x, actual.y+1);
        left = new Point(actual.x, actual.y-1);
    }

    public void findPath() {
        end = new Point(size-1,size-1);
        Point intemediate = new Point(20,7);
        conect(actual,intemediate);
        conect(intemediate,end);
    }

    public void conect(Point actual, Point end) {
        while (true) {
            if (actual.equals(end)) return;

            setExternalPoints(actual);

            double north = disperse(new Point(actual.x+1,actual.y), end);
            double south = disperse(new Point(actual.x-1,actual.y), end);
            double rigth = disperse(new Point(actual.x,actual.y+1), end);
            double left = disperse(new Point(actual.x,actual.y-1), end);

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
            }
            path.add(actual);
            field[actual.x][actual.y] = 0;
        }
    }

    public double disperse(Point init, Point end) {
        for (int i = 0; i < 7 ; i++) {
            if (init.equals(end)) {
                return 1 / (init.distance(end) + 1);
            }

            Random random = new Random();
            List<Point> candidates = new ArrayList<>();

            setExternalPoints(init);

            candidates.add(north);
            candidates.add(south);
            candidates.add(rigth);
            candidates.add(left);

            candidates.removeIf(point -> (
                    point.x == 0
                 || point.y == 0
                 || point.x == size - 1
                 || point.y == size - 1
            ));

            /*
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
            */

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
                if (field[i][j] == null) {
                    string += " " + " ";
                } else string += field[i][j] + " ";
            string += "\n";
        } return string;
    }

    public static void main(String[] args) {
        Mesh mesh = new Mesh(30);
        mesh.findPath();
        System.out.println(mesh.fieldToString());
    }
}
