package part3.effects;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by shiqing on 18-1-3.
 */
public class Debugging {

    final static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }
    }


    public static void main(String[] args) {
        List<Point> points = Arrays.asList(new Point(12, 2), null);
        points.stream().filter(p -> Objects.nonNull(p)).map(p -> p.getX()).forEach(System.out::println);

    }




}
