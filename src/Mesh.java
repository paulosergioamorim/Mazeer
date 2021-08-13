import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Mesh {
    public int[][] field;
    public static int size = 0;

    private final List<Point> points;
    private final List<Point> path;

    /**
     * Create a field with size
     * @param size is size of mesh
     */
    public Mesh(int size) {
        Mesh.size = size;
        field = new int[Mesh.size][Mesh.size];
        points = new ArrayList<>();
        path = new ArrayList<>();

        mapField();
        findPath(new Point(1,1),new Point(size-2,size-2));
        formatField();
        System.out.println(fieldToString());
    }

    // private methods

    private void formatField() {
        points.forEach(
                point -> field[point.x][point.y] = isBorder(point) ? 1: 0
        );
        points.forEach(
                point -> field[point.x][point.y] = path.contains(point) ? 0: 1
        );
    }

    private void mapField() {
        for (int x = 0; x < field.length; x++)
            for (int y = 0; y < field[0].length; y++) {
                Point point = new Point(x,y);
                points.add(point);
            }
    }

    private void findPath(Point A, Point B) {
        for (int j = 0; j < 100; j++) {

            if (A == B) break;

            List<Clone> clones = new ArrayList<>();

            for (int i = 0; i < 200; i++) {
                Clone clone = new Clone(size);
                clone.setHeuristic(A,B);
                clones.add(clone);
            }

            List<Clone> clones_up = new ArrayList<>();
            List<Clone> clones_down = new ArrayList<>();
            List<Clone> clones_left = new ArrayList<>();
            List<Clone> clones_rigth = new ArrayList<>();

            Point finalA = A;
            clones.forEach(clone -> {
                if (clone.getFirst().equals(new Point(finalA.x - 1, finalA.y))) {
                    clones_up.add(clone);
                }
                if (clone.getFirst().equals(new Point(finalA.x + 1, finalA.y))) {
                    clones_down.add(clone);
                }
                if (clone.getFirst().equals(new Point(finalA.x, finalA.y - 1))) {
                    clones_left.add(clone);
                }
                if (clone.getFirst().equals(new Point(finalA.x, finalA.y + 1))) {
                    clones_rigth.add(clone);
                }
            });

            double avarage_up = 0;
            for (Clone clone:
                 clones_up) {
                avarage_up += clone.getHeuristic();
                avarage_up /= clones_down.size();
            }

            double avarage_down = 0;
            for (Clone clone:
                    clones_down) {
                avarage_down += clone.getHeuristic();
                avarage_down /= clones_down.size();
            }

            double avarage_left = 0;
            for (Clone clone:
                    clones_left) {
                avarage_left += clone.getHeuristic();
                avarage_left /= clones_left.size();
            }

            double avarage_rigth = 0;
            for (Clone clone:
                    clones_rigth) {
                avarage_rigth += clone.getHeuristic();
                avarage_rigth /= clones_rigth.size();
            }

            List<Double> avarages = new ArrayList<>();

            avarages.add(avarage_up);
            avarages.add(avarage_down);
            avarages.add(avarage_left);
            avarages.add(avarage_rigth);

            avarages.sort(Double::compare);

            double best_avarage = avarages.get(avarages.size()-1);

            if (best_avarage == avarage_up) A = clones_up.get(0).getFirst();
            if (best_avarage == avarage_down) A = clones_down.get(0).getFirst();
            if (best_avarage == avarage_left) A = clones_left.get(0).getFirst();
            if (best_avarage == avarage_rigth) A = clones_rigth.get(0).getFirst();

            path.add(A);
        }
    }

    private String fieldToString() {
        String s = "";
        for (int x = 0; x < field.length; x++) {
            for (int y = 0; y < field[0].length; y++) {
                s += field[x][y] + " ";
            }
            s += "\n";
        }
        return s;
    }
    // public methods
    /**
     * @param point input point
     * @return if point is part of border
     */
    public static boolean isBorder(Point point) {
        return (
                point.x == 0
             || point.x == size - 1
             || point.y == 0
             || point.y == size - 1
        );
    }

    // getters methods

    /**
     * @return path
     */
    public List<Point> getPath() {
        return path;
    }

    /**
     * @return all points
     */
    public List<Point> getPoints() {
        return points;
    }

    public static void main(String[] args) {
        int size = 10;
        new Mesh(size);
    }
}
