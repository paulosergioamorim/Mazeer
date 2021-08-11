import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Mesh {
    private final Integer[][] field;

    public static final int SIZE = 30;
    public final List<Point> path = new ArrayList<>();

    public Mesh() {
        field = new Integer[SIZE][SIZE];

        this.setBounds();
    }

    public void setBounds() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if (
                        i == 0
                     || i == SIZE - 1
                     || j == 0
                     || j == SIZE -1
                ) field[i][j] = 1;
            }
        }
        field[1][1] = 0;
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

            A = best_Clone.path.get(0);
            path.add(A);
            field[A.x][A.y] = 0;
        }
    }

    public String pathToString() {
        var ref = new Object() {
            String string = "";
        };
        path.forEach(point -> ref.string += String.format("(%d,%d) ",point.x,point.y));
        return ref.string;
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
        Mesh mesh = new Mesh();
        mesh.findPath(new Point(1,1), new Point(SIZE-2,SIZE-2));
        System.out.println(mesh.fieldToString());
    }
}
