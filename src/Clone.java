import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Clone {
    private final List<Point> path = new ArrayList<>();
    private final Random random = new Random();

    public double getHeuristic(Point A, Point B) {
        int energy = 10;
        while (A != B && energy != 0) {
            Point north = new Point(A.x-1,A.y);
            Point south = new Point(A.x+1,A.y);
            Point left = new Point(A.x, A.y+1);
            Point rigth = new Point(A.x, A.y-1);

            List<Point> candidates = new ArrayList<>(List.of(new Point[] {
                    north, south, left, rigth
            })); candidates.removeIf(Mesh::isBorder);

            int index = random.nextInt(candidates.size());
            A = candidates.get(index);
            path.add(A);
            energy--;
        } return A.distance(B) / (energy + 1);
    }

    public List<Point> getPath() {
        return path;
    }
}
