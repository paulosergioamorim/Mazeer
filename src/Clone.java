import java.awt.*;
import java.util.HashMap;
import java.util.List;

public class Clone {
    private final List<Point> path;
    private Facing facing;

    public Clone(List<Point> path) {
        this.path = path;
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

        private static final HashMap<Integer,Facing> map = new HashMap<>();

        static {
            for (Facing facing: values()) {
                map.put(facing.value,facing);
            }
        }

        public static Facing valueOf(int value) {
            return map.get(value);
        }

        public void rotate(int i) {
            value += i;
            if (value == -1) value = 3;
            if (value == 4) value = 0;
        }
    }
}
