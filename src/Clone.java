import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Clone {

    public final List<Point> parcial = new ArrayList<>();

    public double getHeuristic(Point A, Point B) {
        int energy = 10;

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

            candidates.removeIf(Mesh::isBorder);

            if (candidates.isEmpty()) break;
            else {
                int result = random.nextInt(candidates.size());
                A = candidates.get(result);
                parcial.add(A);
            } energy--;
        }
        return A.distance(B) / (energy + 1);
    }
}
