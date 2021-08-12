import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Clone {
    private final List<Point> path;
    private final Facing facing = Facing.RIGTH;
    private double heuristic;

    private final int size;

    private final Random random = new Random();

    public List<Point> getPath() {
        return path;
    }

    public Clone(int size) {
        this.size = size;
        path = new ArrayList<>();
    }

    public double getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(Point A, Point B) {
        int energy = 10;
        while (!A.equals(B) && energy != 0) {
            int x = A.x;
            int y = A.y;

            Point north = new Point(x-1,y);
            Point south = new Point(x+1,y);
            Point west = new Point(x,y-1);
            Point east = new Point(x,y+1);

            Point front;
            Point left;
            Point right;

            Facing facing = Facing.valueOf(this.facing.value);

            switch (facing) {
                case NORTH -> {
                    front = north;
                    left = west;
                    right = east;
                }
                case SOUTH -> {
                    front = south;
                    left = east;
                    right = west;
                }
                case LEFT -> {
                    front = west;
                    left = south;
                    right = north;
                }
                case RIGTH -> {
                    front = east;
                    left = north;
                    right = south;
                }
                default -> throw new IllegalStateException("Unexpected value: " + facing);
            }

            List<Point> candidates = new ArrayList<>();

            candidates.add(front);
            candidates.add(left);
            candidates.add(right);

            candidates.removeIf(point -> (
                    point.x == 0
                 || point.x == size - 1
                 || point.y == 0
                 || point.y == size - 1
            ));

            int index = random.nextInt(candidates.size());

            A = candidates.get(index);

            if (A.equals(north)) facing.setValue(0);
            else if (A.equals(right)) facing.setValue(1);
            else if (A.equals(south)) facing.setValue(2);
            else if (A.equals(left)) facing.setValue(3);

            path.add(A);

            energy--;
        }
        heuristic = A.distance(B);
    }

    private enum Facing {
        NORTH(0),
        RIGTH(1),
        SOUTH(2),
        LEFT(3);

        Facing(int value) {
            this.value = value;
        }

        private int value;

        public void setValue(int value) {
            this.value = value;
        }

        private static final HashMap<Integer,Facing> map = new HashMap<>();

        static {
            for (Facing facing: values()) {
                map.put(facing.value,facing);
            }
        }

        public static Facing valueOf(int value) {
            return map.get(value);
        }

    }
}
