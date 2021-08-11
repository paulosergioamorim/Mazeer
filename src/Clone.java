import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Clone {
    public final List<Point> path = new ArrayList<>();

    public double getHeuristic(Point A, Point B) {
        int energy = 15;

        while (!A.equals(B) && energy != 0) {
            Point north = new Point(A.x-1, A.y);
            Point south = new Point(A.x+1, A.y);
            Point rigth = new Point(A.x, A.y-1);
            Point left = new Point(A.x, A.y+1);

            Random random = new Random();
            List<Point> candidates = new ArrayList<>();

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

            if (candidates.isEmpty()) break;
            else {
                int result = random.nextInt(candidates.size());
                A = candidates.get(result);
                path.add(A);
            } energy--;
        }
        return Math.pow(A.distance(B),2) / ((energy + 1) * A.distance(new Point(Mesh.SIZE/2,Mesh.SIZE/2)));
    }
}
