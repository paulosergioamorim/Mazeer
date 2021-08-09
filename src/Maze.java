import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Maze { // class to generate a maze

    private static final int SIZE = 25; // size of matrix
    private static final int[][] FIELD = new int[SIZE][SIZE]; // matrix

    private final List<Point> path = new ArrayList<>(); // path to solve
    private final Random random = new Random();

    private final int[] values = new int[] {0, 1}; // possible values to cell

    public Maze() {
        for (int i = 0; i < FIELD.length; i++) // lines
            for (int j = 0; j < FIELD.length; j++) { // columns
                int value = random.nextInt(values.length);
                FIELD[i][j] = values[value];
            }
    } // constructor class

    public void format() {
        for (int i = 0; i < FIELD.length; i++) // lines
            for (int j = 0; j < FIELD[0].length; j++) // columns
                if (
                        i == 0
                        || i == SIZE - 1
                        || j == 0
                        || j == SIZE - 1
                ) FIELD[i][j] = 1; // if cell is border

        FIELD[1][1] = 0; // initial cell
        FIELD[SIZE-2][SIZE-2] = 0; // final cell
    } // format maze method

    public void solve() {
        Point actual = new Point(1,1);
        Point finall = new Point(SIZE-2, SIZE-2);

        path.add(actual);

        while (true) {
            if (actual.equals(finall)) return;

            Point north = new Point(actual.x,actual.y+1);
            Point left = new Point(actual.x-1,actual.y);
            Point south = new Point(actual.x,actual.y-1);
            Point rigth = new Point(actual.x+1, actual.y);

            List<Point> candidates = new ArrayList<>();

            if (FIELD[left.x][left.y] == FIELD[actual.x][actual.y]) candidates.add(left);
            if (FIELD[north.x][north.y] == FIELD[actual.x][actual.y]) candidates.add(north);
            if (FIELD[rigth.x][rigth.y] == FIELD[actual.x][actual.y]) candidates.add(rigth);
            if (FIELD[south.x][south.y] == FIELD[actual.x][actual.y]) candidates.add(south);

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
                        || point.x == SIZE - 1
                        || point.y == 0
                        || point.y == SIZE - 1
                ));

                int random = this.random.nextInt(candidates.size());
                actual = candidates.get(random);
                path.add(actual);
                FIELD[actual.x][actual.y] = 0;
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
        for (int i = 0; i < FIELD.length; i++) {
            for (int j = 0; j < FIELD[0].length; j++) System.out.print(FIELD[i][j] + " ");
            System.out.println();
        }
    } // showMaze method

    public static void main(String[] args) {
        Maze maze = new Maze();
        maze.format();
        maze.solve();
        maze.showMaze();
        maze.showPath();
    } // main method
}
