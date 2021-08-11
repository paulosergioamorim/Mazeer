import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Mesh {
    private final Integer[][] field;

    public static final int SIZE = 50;
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

    public void findPath() {
        Point A = new Point(1,1);
        Point B = new Point(SIZE-1,SIZE-1);
        for (int i = 0; i < 3; i++) {
            Clone clone1 = new Clone();
            Clone clone2 = new Clone();
            Clone clone3 = new Clone();
            Clone clone4 = new Clone();
            Clone clone5 = new Clone();

            List<Double> values = new ArrayList<>();

            double heuristic1 = clone1.getHeuristic(A,B);
            double heuristic2 = clone2.getHeuristic(A,B);
            double heuristic3 = clone2.getHeuristic(A,B);
            double heuristic4 = clone2.getHeuristic(A,B);
            double heuristic5 = clone2.getHeuristic(A,B);

            values.add(heuristic1);
            values.add(heuristic2);
            values.add(heuristic3);
            values.add(heuristic4);
            values.add(heuristic5);

            values.sort((o1, o2) -> (int) (o2 - o1));

            double best = values.get(values.size()-1);

            if (best == heuristic1) {
                A = clone1.path.get(0);
                this.path.add(A);
                field[A.x][A.y] = 0;
            }
            else if (best == heuristic2) {
                A = clone2.path.get(0);
                this.path.add(A);
                field[A.x][A.y] = 0;
            }
            else if (best == heuristic3) {
                A = clone3.path.get(0);
                this.path.add(A);
                field[A.x][A.y] = 0;
            }
            else if (best == heuristic4) {
                A = clone4.path.get(0);
                this.path.add(A);
                field[A.x][A.y] = 0;
            }
            else if (best == heuristic5) {
                A = clone5.path.get(0);
                this.path.add(A);
                field[A.x][A.y] = 0;
            }
        }
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
        Mesh mesh = new Mesh();
        mesh.findPath();
        System.out.println(mesh.fieldToString());
    }
}
