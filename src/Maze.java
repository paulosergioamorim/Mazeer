import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Maze { // class to generate a maze

    public final int size = 40; // size of matrix
    public final int[][] field = new int[size][size]; // matrix

    private final List<Point> path = new ArrayList<>(); // path to solve
    private final Random random = new Random();

    private final int[] values = new int[] {0, 1}; // possible values to cell

    public Maze() {
        for (int i = 0; i < field.length; i++) // lines
            for (int j = 0; j < field.length; j++) { // columns
                int value = random.nextInt(values.length);
                field[i][j] = values[value];
            }
        this.format();
        this.solve();
    } // constructor class

    public void format() {
        for (int i = 0; i < field.length; i++) // lines
            for (int j = 0; j < field[0].length; j++) // columns
                if (
                        i == 0
                     || i == size - 1
                     || j == 0
                     || j == size - 1
                ) field[i][j] = 1; // if cell is border
                else if (
                        field[i-1][j] == 1
                     && field[i][j+1] == 1
                     && field[i+1][j] == 1
                     && field[i][j-1] == 1
                     && field[i][j] == 0
                ) field[i][j] = 1; // if cell is alone

        field[1][1] = 0; // initial cell
        field[size -2][size -2] = 0; // final cell
    } // format maze method

    public void solve() {
        Point actual = new Point(1,1);
        Point finall = new Point(size -2, size -2);

        path.add(actual);

        while (true) {
            if (actual.equals(finall)) return;

            Point north = new Point(actual.x,actual.y+1);
            Point left = new Point(actual.x-1,actual.y);
            Point south = new Point(actual.x,actual.y-1);
            Point rigth = new Point(actual.x+1, actual.y);

            List<Point> candidates = new ArrayList<>();

            if (field[left.x][left.y] == field[actual.x][actual.y]) candidates.add(left);
            if (field[north.x][north.y] == field[actual.x][actual.y]) candidates.add(north);
            if (field[rigth.x][rigth.y] == field[actual.x][actual.y]) candidates.add(rigth);
            if (field[south.x][south.y] == field[actual.x][actual.y]) candidates.add(south);

            candidates.removeIf(path::contains);

            Point actuall = actual;

            if (!candidates.isEmpty()) {
                int random = this.random.nextInt(candidates.size());
                actual = candidates.get(random);
                path.add(actual);
            } else {
                candidates.add(north);
                candidates.add(left);
                candidates.add(south);
                candidates.add(rigth);

                candidates.removeIf(point -> path.contains(point) && point.distance(finall) > actuall.distance(finall));

                candidates.removeIf(point -> (
                        point.x == 0
                     || point.x == size - 1
                     || point.y == 0
                     || point.y == size - 1
                ));

                int random = this.random.nextInt(candidates.size());
                actual = candidates.get(random);
                path.add(actual);
                field[actual.x][actual.y] = 0;
            }
        }
    } // solve method

    public void showPath() {
        System.out.println();
        for (Point point: path) System.out.printf("(%d,%d) ",point.x,point.y);
        System.out.printf("\nn° de passos: %d",path.size());
    } // showPath method

    public void showMaze() {
        System.out.println();
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) System.out.print(field[i][j] + " ");
            System.out.println();
        }
    } // showMaze method

    public static void main(String[] args) {
        Maze maze = new Maze();
    } // main method
}
