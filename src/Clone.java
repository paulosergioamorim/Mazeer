import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Clone {
    private Point first;
    private double heuristic;
    private final List<Point> path = new ArrayList<>();
    private final Random random = new Random();

    public void setHeuristic(Point A, Point B) {
        int energy = 15;
        while (!A.equals(B) && energy != 0) {
            int x = A.x;
            int y = A.y;

            Point up = new Point(x-1,y);
            Point down = new Point(x+1,y);
            Point left = new Point(x,y-1);
            Point rigth = new Point(x,y+1);

            List<Point> candidates = new ArrayList<>(List.of(
                    new Point[] {
                            up, down, left, rigth
                    })
            );

            candidates.removeIf(Mesh::isBorder);

            int index = random.nextInt(candidates.size());

            A = candidates.get(index);
            path.add(A);

            if (energy == 15) first = A;
            energy--;
        }
        heuristic = A.distance(B);
    }

    public Point getFirst() {
        return first;
    }

    public double getHeuristic() {
        return heuristic;
    }
}
