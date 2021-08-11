import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Mesh {
    public static final int SIZE = 40;

    public final Integer[][] field;

    public final List<Point> points = new ArrayList<>();
    public final List<Point> path = new ArrayList<>();

    public Mesh() {
        field = new Integer[SIZE][SIZE];

        for (int x = 0; x < SIZE; x++)
            for (int y = 0; y < SIZE; y++)
                points.add(new Point(x,y));

        this.findPath(new Point(1,1), new Point(SIZE-2,SIZE-2));
        this.formatMesh();
    }

    private void formatMesh() {
        points.forEach(point -> {
            if (isBound(point))
                field[point.x][point.y] = 1;
        });
        points.forEach(point -> {
            if (!path.contains(point))
                field[point.x][point.y] = 1;
        });
        path.forEach(point -> field[point.x][point.y] = 0);
    }

    private boolean isBound(Point point) {
        assert points.contains(point);
        return point.x == 0
            || point.x == SIZE - 1
            || point.y == 0
            || point.y == SIZE - 1;
    }

    public void findPath(Point A, Point B) {
        while (!A.equals(B)) {
            List<Double> values = new ArrayList<>();
            HashMap<Double, Clone> map = new HashMap<>();

            for (int i = 0; i < 10; i++) {
                Clone clone = new Clone();
                double heuristic = clone.getHeuristic(A,B);
                map.put(heuristic,clone);
            }

            map.forEach((value, clone) -> values.add(value));

            values.sort(Double::compare); // from smallest to largest

            double best_Heuristic = values.get(0);

            Clone best_Clone = map.get(best_Heuristic);

            A = best_Clone.path.get(0);
            path.add(A);
        }
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
}
