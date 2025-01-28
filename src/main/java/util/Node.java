package util;

import java.awt.Point;
import java.util.List;

public record Node (Point p, List<Character> path, int cost, Direction direction) {

    public Node(Point p, List<Character> path, int cost) {
        this(p, path, cost, null);
    }
}
