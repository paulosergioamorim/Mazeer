import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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

        this.mapField();
        this.findPath(new Point(1,1), new Point(Mesh.size-2, Mesh.size-2));
        this.formatField();
    }

    // private methods

    private void formatField() {
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

    private void create() {

    }

    private void findPath(Point A, Point B) {
        path.add(A);
        while (!A.equals(B)) {
            List<Clone> clones = new ArrayList<>();

            for (int i = 0; i < 1000; i++) {
                Clone clone = new Clone(size);
                clone.setHeuristic(A,B);
                clones.add(clone);
            }

            List<Clone> clones_up = new ArrayList<>();
            List<Clone> clones_down = new ArrayList<>();
            List<Clone> clones_left = new ArrayList<>();
            List<Clone> clones_rigth = new ArrayList<>();

            for (Clone clone : clones) {
                if (clone.getFirst().equals(new Point(A.x - 1, A.y))) clones_up.add(clone);
                if (clone.getFirst().equals(new Point(A.x + 1, A.y))) clones_down.add(clone);
                if (clone.getFirst().equals(new Point(A.x, A.y - 1))) clones_left.add(clone);
                if (clone.getFirst().equals(new Point(A.x, A.y + 1))) clones_rigth.add(clone);
            }

            double avarage_up = clones_up.stream().mapToDouble(Clone::getHeuristic).sum() / clones_up.size();
            double avarage_down = clones_down.stream().mapToDouble(Clone::getHeuristic).sum() / clones_down.size();
            double avarage_left = clones_left.stream().mapToDouble(Clone::getHeuristic).sum() / clones_left.size();
            double avarage_rigth = clones_rigth.stream().mapToDouble(Clone::getHeuristic).sum() / clones_rigth.size();

            List<Double> avarages = new ArrayList<>(List.of(new Double[] {
                    avarage_up, avarage_down, avarage_left, avarage_rigth
            }));

            avarages.sort(Double::compare);
            double best_avarage = avarages.get(0);

            if (best_avarage == avarage_up) A = clones_up.get(0).getFirst();
            if (best_avarage == avarage_down) A = clones_down.get(0).getFirst();
            if (best_avarage == avarage_left) A = clones_left.get(0).getFirst();
            if (best_avarage == avarage_rigth) A = clones_rigth.get(0).getFirst();

            path.add(A);
        }
    }

    @Override
    public String toString() {
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
        Mesh mesh = new Mesh(20);
        Point init = new Point(1,1);
        Point end = new Point(Mesh.size-2,Mesh.size-2);
        mesh.findPath(init,end);
        mesh.formatField();
        System.out.println(mesh);
    }
}
