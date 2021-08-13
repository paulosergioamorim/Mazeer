import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Mesh {
    public final Integer[][] field;

    private static int size = 0;
    private final List<Point> points = new ArrayList<>();
    private final List<Point> path = new ArrayList<>();
    private final Random random = new Random();

    /**
     * Create a field, a path and format
     * @param size size of maze
     */
    public Mesh(int size) {
        Mesh.size = size;
        field = new Integer[size][size];
        this.mapField(size);
        this.createField();
        this.formatField();
    }

    /**
     * Add all points to a list
     * @param size size of maze
     */
    private void mapField(int size) {
        for (int x = 0; x < size; x++)
            for (int y = 0; y < size; y++)
                points.add(new Point(x,y));
    }

    /**
     * Define a value to each point,
     * if is in path, your value is 0, else is 1
     */
    private void formatField() {
        points.forEach(point ->
                field[point.x][point.y] = (path.contains(point)) ? 0: 1
        );
    }

    public void createField() {
        var ref = new Object() {
            final List<Point> candidates = new ArrayList<>(points);
            final int index = random.nextInt(candidates.size());
            Point A = candidates.get(index);
        };
        ref.candidates.removeIf(Mesh::isBorder);
        ref.candidates.removeIf(point -> point.distance(new Point(size/2,size/2)) < 5);

        for (int i = 0; i < 10; i++) {
            ref.candidates.remove(ref.A);
            int index = random.nextInt(ref.candidates.size());
            Point B = ref.candidates.get(index);
            findPath(ref.A,B);
            ref.A = B;
        }
    }

    /**
     * Use Monte Carlo algorithm to find a path
     * @param A initial point
     * @param B final point
     */
    public void findPath(Point A, Point B) {
        while (!A.equals(B)) {
            List<Double> values = new ArrayList<>();
            HashMap<Double, Clone> map = new HashMap<>();

            for (int i = 0; i < 1000; i++) {
                Clone clone = new Clone();
                double heuristic = clone.getHeuristic(A,B);
                map.put(heuristic,clone);
            }

            map.forEach((value, clone) -> values.add(value));

            values.sort(Double::compare); // organize list from smallest to largest

            double best_Heuristic = values.get(0);

            Clone best_Clone = map.get(best_Heuristic);

            A = best_Clone.getPath().get(0);
            path.add(A);
        }
    }

    /**
     * Verify if border of maze contais point parameter
     * @param point point to verify
     * @return if point is border or not
     */
    public static boolean isBorder(Point point) {
        return point.x == 0
            || point.x == size - 1
            || point.y == 0
            || point.y == size - 1;
    }

    public List<Point> getPath() {
        return path;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++)
                s += field[i][j] + " ";
            s += "\n";
        } return s;
    }
}
