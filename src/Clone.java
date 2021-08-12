import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Clone {
    private double heuristic;

    private final List<Point> path;
    private final int size;
    private final Random random = new Random();

    public List<Point> getPath() {
        return path;
    }

    public Clone(int size) {
        this.size = size;
        path = new ArrayList<>();
    }

    public double getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(Point A, Point B) {
        int energy = 10;
        while (A != B && energy != 0) {
            int x = A.x;
            int y = A.y;

            Point north = new Point(x-1,y);
            Point south = new Point(x+1,y);
            Point left = new Point(x,y-1);
            Point rigth = new Point(x,y+1);

            List<Point> candidates = new ArrayList<>();

            candidates.add(north);
            candidates.add(south);
            candidates.add(left);
            candidates.add(rigth);

            candidates.removeIf(point -> (
                    point.x == 0
                 || point.x == size - 1
                 || point.y == 0
                 || point.y == size - 1
            ));

            int index = random.nextInt(candidates.size());

            A = candidates.get(index);

            path.add(A);

            energy--;
        }
        heuristic = A.distance(B);
    }
}
