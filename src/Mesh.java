import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Mesh {
    public int[][] field;
    public final List<Point> points = new ArrayList<>();
    public final List<Point> path = new ArrayList<>();

    public Mesh(int size) {
        field = new int[size][size];
    }

    public String getField() {
        String string = null;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++)
                string += field[i][j] + " ";
        } return string;
    }
}
