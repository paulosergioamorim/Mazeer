import java.awt.*;
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

        public int getValue() {
            return value;
        }

        public void rotate(int i) {
            value += i;
            if (value == -1) value = 3;
            if (value == 4) value = 0;
        }
    }
}
