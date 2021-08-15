import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Paulo Sergio
 */
public class Mesh {
    public int[][] field;

    private final List<Point> points = new ArrayList<>();
    private final List<Point> path = new ArrayList<>();
    private final Random random = new Random();
    private final int size; // this value is just to initialization

    /**
     * Create a field with size
     * @param size is size of mesh
     */
    public Mesh(int size) {
        this.size = size;
        field = new int[size][size];
        this.map();
        this.create();
        this.values();
    }

    /**
     * Set value for each point in mesh,
     * if path contains point, value equals to 0,
     * else, value is 1
     * @see #path
     */
    private void values() {
        points.forEach(point ->
                field[point.x][point.y] = path.contains(point) ? 0: 1
        );
    }

    /**
     * Map each point in mesh to a list
     * @see #field
     */
    private void map() {
        for (int x = 0; x < field.length; x++)
            for (int y = 0; y < field[0].length; y++) {
                Point point = new Point(x,y);
                points.add(point);
            }
    }

    /**
     * Define mesh path
     * @see #find(Point, Point)
     */
    private void create() {
        List<Point> candidates = new ArrayList<>(this.points);
        candidates.removeIf(this::isBorder);
        Point A = candidates.get(random.nextInt(candidates.size()));

        for (int i = 0; i < 3; i++) {
            System.out.println("Step " + (i + 1));
            Point finalA = A;
            candidates.removeIf(point -> point.distance(finalA) < 10);
            Point B = candidates.get(random.nextInt(candidates.size()));
            find(A,B);
            A = B;
        }
    }

    /**
     * Find a path from A to B
     * @param A initial point
     * @param B final point
     * @see Clone#setHeuristic(Point, Point)
     */
    private void find(@NotNull Point A, @NotNull Point B) {
        while (!A.equals(B)) {
            path.add(A);
            List<Clone> clones = new ArrayList<>();

            List<Clone> clones_up = new ArrayList<>();
            List<Clone> clones_down = new ArrayList<>();
            List<Clone> clones_left = new ArrayList<>();
            List<Clone> clones_rigth = new ArrayList<>();

            for (int i = 0; i < 1000; i++) {
                Clone clone = new Clone(this);
                clone.setHeuristic(A,B);
                clones.add(clone);
            }

            for (Clone clone : clones) {
                if (clone.getFirst().equals(new Point(A.x - 1, A.y))) clones_up.add(clone);
                if (clone.getFirst().equals(new Point(A.x + 1, A.y))) clones_down.add(clone);
                if (clone.getFirst().equals(new Point(A.x, A.y - 1))) clones_left.add(clone);
                if (clone.getFirst().equals(new Point(A.x, A.y + 1))) clones_rigth.add(clone);
            }

            double avarage_up = clones_up.stream().mapToDouble(Clone::getHeuristic).sum() / clones_up.size();
            double avarage_down = clones_down.stream().mapToDouble(Clone::getHeuristic).sum() / clones_down.size();
            double avarage_left = clones_left.stream().mapToDouble(Clone::getHeuristic).sum() / clones_left.size();
            double avarage_rigth = clones_rigth.stream().mapToDouble(Clone::getHeuristic).sum() / clones_rigth.size();

            List<Double> avarages = new ArrayList<>(List.of(
                    new Double[] { avarage_up, avarage_down, avarage_left, avarage_rigth })
            ); avarages.sort(Double::compare);

            double best_avarage = avarages.get(0);

            if (best_avarage == avarage_up) A = clones_up.get(0).getFirst();
            if (best_avarage == avarage_down) A = clones_down.get(0).getFirst();
            if (best_avarage == avarage_left) A = clones_left.get(0).getFirst();
            if (best_avarage == avarage_rigth) A = clones_rigth.get(0).getFirst();
        } path.add(A);
    }

    /**
     * Verify if point is part of maze border
     * @param point input point
     * @return if point is part of border
     * @see Mesh#field
     */
    public boolean isBorder(Point point) {
        return point.x == 0
            || point.x == size - 1
            || point.y == 0
            || point.y == size - 1;
    }

    public List<Point> getPath() { return path; }

    @Override public String toString() {
        String s = "";
        for (int x = 0; x < field.length; x++) {
            for (int y = 0; y < field[0].length; y++)
                s += field[x][y] + " ";
            s += "\n";
        } return s;
    }
}
