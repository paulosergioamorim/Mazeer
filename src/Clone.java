import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Clone {
    private final List<Point> path;
    private final Random random = new Random();

    private final int size;

    public List<Point> getPath() {
        return path;
    }

    public Clone(int size) {
        this.size = size;
        path = new ArrayList<>();
    }

    public double getHeuristic(Point A, Point B) {
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

            candidates.removeIf(Mesh::isBorder);

            int index = random.nextInt(candidates.size());

            A = candidates.get(index);

            path.add(A);

            energy--;
        }
        return A.distance(B);
    }
}
