package day_06;

import java.awt.Point;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import util.Direction;
import util.State;

public class GuardGallivant {

    public static long guardDistinctPositions(List<String> fileContents) {
        char[][] map = parseMaze(fileContents);
        Point startingPoint = getStartingPoint(fileContents);

        return getPatrolPath(startingPoint, map).size();
    }

    public static long obstructionDistinctPositions(List<String> fileContents) {
        char[][] map = parseMaze(fileContents);
        Point startingPoint = getStartingPoint(fileContents);
        Set<Point> visited = getPatrolPath(startingPoint, map);
        int index = 0;

        for (Point p : visited) {
            Set<State> path = new HashSet<>();
            Point current = startingPoint;
            Direction direction = Direction.NORTH;
            path.add(new State(current, direction));
            boolean outOfBounds = false;

            while (!outOfBounds) {
                Point next = direction.move(current);
                try {
                    if (map[next.y][next.x] == '#' || next.equals(p)) {
                        direction = direction.rotate90(true);

                        if (!path.add(new State(current, direction))) {
                            index++;
                            break;
                        }
                    }
                    else {
                        current = next;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    outOfBounds = true;
                }
            }
        }

        return index;
    }

    private static char[][] parseMaze(List<String> fileContents) {
        return fileContents.stream()
            .map(String::toCharArray)
            .toArray(char[][]::new);
    }

    private static Point getStartingPoint(List<String> fileContents) {
        return fileContents.stream()
            .map(line -> new Point(line.indexOf('^'), fileContents.indexOf(line)))
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
            Point next = direction.move(current);

            try {
                if (map[next.y][next.x] == '#') {
                    direction = direction.rotate90(true);
                }
                else {
                    visited.add(next);
                    current = next;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                outOfBounds = true;
            }
        }

        return visited;
    }
}
