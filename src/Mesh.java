import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Mesh {
    public int[][] field;

    public final int size;

    private final List<Point> points;
    private final List<Point> path;

    /**
     * Create a field with size
     * @param size is size of mesh
     */
    public Mesh(int size) {
        this.size = size;
        field = new int[this.size][this.size];
        points = new ArrayList<>();
        path = new ArrayList<>();
    }

    // private methods

    private void formatMesh() {
        points.forEach(
                point -> field[point.x][point.y]
                        = isBorder(point) ? 1: 0
        );
        points.forEach(
                point -> this.field[point.x][point.y]
                        = path.contains(point) ? 0: 1
        );
    }

    private void map() {
        for (int x = 0; x < field.length; x++)
            for (int y = 0; y < field[0].length; y++) {
                Point point = new Point(x,y);
                points.add(point);
            }
    }

    // public methods

    /**
     * @param point input point
     * @return if point is part of border
     */
    public boolean isBorder(Point point) {
        return (
                point.x == 0
             || point.x == size - 1
             || point.y == 0
             || point.y == size - 1
        );
    }

    // getters methods

    /**
     * @return path
     */
    public List<Point> getPath() {
        return path;
    }

    /**
     * @return all points
     */
    public List<Point> getPoints() {
        return points;
    }
}
