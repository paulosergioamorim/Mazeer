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

    public Mesh(int size) {
        Mesh.size = size;
        field = new Integer[size][size];
        mapField(size);
        createField();
        formatField();
    }

    private void mapField(int size) {
        for (int x = 0; x < size; x++)
            for (int y = 0; y < size; y++)
                points.add(new Point(x,y));
    }

    private void formatField() {
        points.forEach(point ->
                field[point.x][point.y] = (path.contains(point)) ? 0: 1
        );
    }

    public void createField() {
        var obj = new Object() {
            final List<Point> candidates = new ArrayList<>(points);
            final int index = random.nextInt(candidates.size());
            Point A = candidates.get(index);
        };

        obj.candidates.removeIf(Mesh::isBorder);

        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(obj.candidates.size());
            Point B = obj.candidates.get(index);
            findPath(obj.A,B);
            obj.A = B;
        }
    }

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

            values.sort(Double::compare); // from smallest to largest

            double best_Heuristic = values.get(0);

            Clone best_Clone = map.get(best_Heuristic);

            A = best_Clone.getPath().get(0);
            path.add(A);
        }
    }

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
