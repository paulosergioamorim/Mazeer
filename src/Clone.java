import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Clone {
    public final List<Point> path = new ArrayList<>();

    public Clone() {
    }

    public double getHeuristic(Point A, Point B) {
        int energy = 100;

        while (true) {
            if (A.equals(B)) return A.distance(B);

            if (energy == 0) return A.distance(B);

            Random random = new Random();
            List<Point> candidates = new ArrayList<>();

            Point north = new Point(A.x-1, A.y);
            Point south = new Point(A.x+1, A.y);
            Point rigth = new Point(A.x, A.y-1);
            Point left = new Point(A.x, A.y+1);

            candidates.add(north);
            candidates.add(south);
            candidates.add(rigth);
            candidates.add(left);

            candidates.removeIf(point -> (
                    point.x == 0
                            || point.y == 0
                            || point.x == Mesh.SIZE - 1
                            || point.y == Mesh.SIZE - 1
            ));

            if (candidates.isEmpty()) {
                path.add(A);
                return A.distance(B);
            } else {
                int result = random.nextInt(candidates.size());
                A = candidates.get(result);
                path.add(A);
            }
            energy--;
        }
    }
}
