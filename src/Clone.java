import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Paulo Sergio
 */

public class Clone {
    private Point first;
    private double heuristic;
    private final List<Point> path = new ArrayList<>();
    private final Random random = new Random();

    /**
     * Initiate Clone heuristic
     * @param A initial point
     * @param B final point
     */
    public Clone(Point A, Point B) {
        this.setHeuristic(A,B);
    }

    public void setHeuristic(Point A, Point B) {
        int energy = 15;
        while (!A.equals(B) && energy != 0) {
            Point up = new Point(A.x-1,A.y);
            Point down = new Point(A.x+1,A.y);
            Point left = new Point(A.x, A.y-1);
            Point rigth = new Point(A.x, A.y+1);

            List<Point> candidates = new ArrayList<>(List.of(
                    new Point[] {
                            up, down, left, rigth
                    })
            );

            candidates.removeIf(Mesh::isBorder);

            A = candidates.get(random.nextInt(candidates.size()));
            path.add(A);

            if (energy == 15) first = A; energy--;
        } heuristic = A.distance(B);
    }

    public Point getFirst() {
        return first;
    }

    public double getHeuristic() {
        return heuristic;
    }
}
