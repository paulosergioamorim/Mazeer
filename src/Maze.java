import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Maze { // comentário

    private final int SIZE = 25;
    private final int[][] field = new int[SIZE][SIZE];

    private final List<Point> path = new ArrayList<>();
    private final Random random = new Random();

    public Maze() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                double value = Math.random();
                if (value >= 0.5d) {
                    field[i][j] = 1;
                } else {
                    field[i][j] = 0;
                }
            }
        }
    }

    public void format() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if (i == 0) field[i][j] = 1;
                if (i == SIZE -1) field[i][j] = 1;
                if (j == 0) field[i][j] = 1;
                if (j == SIZE -1) field[i][j] = 1;

                field[1][1] = 0;
                field[SIZE-2][SIZE-2] = 0;
            }
        }
    }

    public void solve() {
        Point actual = new Point(1,1);
        Point finall = new Point(this.SIZE-2,this.SIZE-2);

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
                        || point.x == this.SIZE - 1
                        || point.y == 0
                        || point.y == this.SIZE - 1
                ));

                int random = this.random.nextInt(candidates.size());
                actual = candidates.get(random);
                path.add(actual);
                field[actual.x][actual.y] = 0;
            }
        }
    }

    private double disperse(int[][] field, Point actual) {
        return 0d;
    }

    public void showPath() {
        for (Point point: path) System.out.printf("(%d,%d) ",point.x,point.y);
        System.out.printf("\nn° de passos: %d",path.size());
    }

    public void showMaze() {
        System.out.println();
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) System.out.print(field[i][j] + " "); System.out.println();
        }
    }

    public static void main(String[] args) {
        Maze maze = new Maze();
        maze.format();
        maze.solve();
        maze.showMaze();

    }
}
