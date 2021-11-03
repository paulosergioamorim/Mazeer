import org.jetbrains.annotations.NotNull;

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
    private final Mesh mesh;

    public Clone(Mesh mesh) {
        this.mesh = mesh;
    }

    private int repeated() {
        return (int) path
                .stream()
                .filter(point -> mesh
                        .getPath()
                        .contains(point))
                .count();
    }

    private double distanceToPath() {
        double d = 0;
        for (Point point : path) {
            Point ref = mesh.getPath().get(mesh.getPath().size()-1);
            d += point.distance(ref);
        }
        d /= path.size();
        return d;
    }

    public void setHeuristic(@NotNull Point A, @NotNull Point B) {
        int energy = 100;
        while (!A.equals(B) && energy > 1) {
            Point up = new Point(A.x-1,A.y);
            Point down = new Point(A.x+1,A.y);
            Point left = new Point(A.x, A.y-1);
            Point rigth = new Point(A.x, A.y+1);

            List<Point> candidates = new ArrayList<>(List.of(
                    new Point[] { up, down, left, rigth })
            );

            candidates.removeIf(mesh::isBorder);

            A = candidates.get(random.nextInt(candidates.size()));
            path.add(A);
            energy--;

        } heuristic = A.distance(B)
                * repeated()
                * repeated();
    // quanto menor a heuristica, melhor o clone
    }

    public void setFirst() { first = path.get(0); }

    public Point getFirst() { return first; }

    public double getHeuristic() { return heuristic; }
}
