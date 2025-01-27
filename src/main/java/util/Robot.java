package util;

import java.awt.Point;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Robot(int x, int y, int vx, int vy) {

    public static Robot from(String line) {
        Matcher matcher = Pattern.compile("p=(\\d+),(\\d+) v=(-?\\d+),(-?\\d+)")
            .matcher(line);

        if (!matcher.matches()) {
            throw new IllegalArgumentException(line);
        }

        return new Robot(
            Integer.parseInt(matcher.group(1)),
            Integer.parseInt(matcher.group(2)),
            Integer.parseInt(matcher.group(3)),
            Integer.parseInt(matcher.group(4))
        );
    }

    public Point move(int times, int width, int height) {
        int x = (this.x + times * this.vx) % width;
        int y = (this.y + times * this.vy) % height;

        return new Point(x < 0 ? x + width : x, y < 0 ? y + height : y);
    }
}
