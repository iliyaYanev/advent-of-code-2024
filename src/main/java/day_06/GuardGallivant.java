package day_06;

import java.awt.Point;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import util.Direction;
import util.Position;

public class GuardGallivant {

    public static long distinctGuardPositions(List<String> fileContents) {
        char[][] map = parseMap(fileContents);
        Point initialPosition = parseInitialPosition(fileContents);

        return getPatrolPath(initialPosition, map).size();
    }

    public static long guardObstructions(List<String> fileContents) {
        char[][] map = parseMap(fileContents);
        Point initialPosition = parseInitialPosition(fileContents);
        Set<Point> visited = getPatrolPath(initialPosition, map);
        int index = 0;

        for (Point p : visited) {
            Set<Position> path = new HashSet<>();
            Point current = initialPosition;
            boolean outOfBounds = false;
            Direction direction = Direction.NORTH;

            path.add(new Position(current, direction));

            while (!outOfBounds) {
                Point next = Direction.getPoint(direction, current);

                try {
                    if (map[next.y][next.x] == '#' || next.equals(p)) {
                        direction = Direction.turn(direction, true);

                        if (!path.add(new Position(current, direction))) {
                            index++;
                            break;
                        }
                    } else {
                        current = next;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    outOfBounds = true;
                }
            }
        }

        return index;
    }

    private static char[][] parseMap(List<String> fileContents) {
        return fileContents.stream()
            .map(String::toCharArray)
            .toArray(char[][]::new);
    }

    private static Point parseInitialPosition(List<String> input) {
        return input.stream()
            .map(line -> new Point(line.indexOf('^'), input.indexOf(line)))
            .filter(point -> point.x != -1)
            .findFirst()
            .orElse(null);
    }

    private static Set<Point> getPatrolPath(Point start, char[][] map) {
        Set<Point> visited = new HashSet<>();

        visited.add(start);
        Point current = start;

        Direction direction = Direction.NORTH;
        boolean outOfBounds = false;

        while (!outOfBounds) {
            Point next = Direction.getPoint(direction, current);

            try {
                if (map[next.y][next.x] == '#') {
                    direction = Direction.turn(direction, true);
                } else {
                    visited.add(next);
                    current = next;
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                outOfBounds = true;
            }
        }

        return visited;
    }
}
