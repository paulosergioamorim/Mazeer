import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Clone {
    private final List<Point> path;
    private final Random random = new Random();

    private double heuristic;

    private final int size;
    private Point first;

    private List<Point> getPath() {
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

            Point up = new Point(x-1,y);
            Point down = new Point(x+1,y);
            Point left = new Point(x,y-1);
            Point rigth = new Point(x,y+1);

            List<Point> candidates = new ArrayList<>();

            candidates.add(up);
            candidates.add(down);
            candidates.add(left);
            candidates.add(rigth);

            candidates.removeIf(Mesh::isBorder);

            int index = random.nextInt(candidates.size());

            A = candidates.get(index);
            path.add(A);

            if (energy == 10)
                first = A;

            energy--;
        }
        heuristic = 1 / (A.distance(B) + 1);
    }

    public Point getFirst() {
        return first;
    }
}
