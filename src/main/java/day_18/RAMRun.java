package day_18;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.function.Predicate;
import util.Direction;

public class RAMRun {

    public static long minimumSteps(List<String> fileContents) {
        int memorySize = 71;
        int bytes = 1024;
        char[][] memory = new char[memorySize][memorySize];

        Point start = new Point(0, 0);
        Point end = new Point(memorySize - 1, memorySize - 1);

        for (int i = 0; i < bytes; i++) {
            String[] parts = fileContents.get(i).split(",");
            Point point = new Point(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));

            memory[point.y][point.x] = '#';
        }

        return findShortestPath(memory, start, end, c -> c != '#').size() - 1;
    }

    public static String firstByteCoordinates(List<String> fileContents) {
        int memorySize = 71;
        int bytes = 1024;
        char[][] memory = new char[memorySize][memorySize];

        Point start = new Point(0, 0);
        Point end = new Point(memorySize - 1, memorySize - 1);

        Predicate<Character> isNotAWall = c -> c != '#';
        List<Point> lastValidPath = Collections.emptyList();

        for (int i = 0; i < fileContents.size(); i++) {
            String[] parts = fileContents.get(i).split(",");
            Point point = new Point(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));

            memory[point.y][point.x] = '#';

            if (i == bytes) {
                lastValidPath = findShortestPath(memory, start, end, isNotAWall);
            }

            if (i > bytes && lastValidPath.contains(point)) {
                lastValidPath = findShortestPath(memory, start, end, isNotAWall);

                if (lastValidPath.isEmpty()) {
                    return fileContents.get(i);
                }
            }
        }

        return null;
    }

    private static List<Point> findShortestPath(char[][] grid, Point start, Point end, Predicate<Character> isPassable) {
        Map<Point, Point> parentMap = new HashMap<>();
        Queue<Point> queue = new LinkedList<>(List.of(start));
        Set<Point> visited = new HashSet<>(List.of(start));

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            if (current.equals(end)) {
                List<Point> path = new ArrayList<>();

                for (Point at = end; at != null; at = parentMap.get(at)) {
                    path.add(at);
                }

                Collections.reverse(path);

                return path;
            }

            for (Direction direction : Direction.values()) {
                Point newPoint = direction.move(current);

                if (isValidPoint(newPoint, grid) && !visited.contains(newPoint) && isPassable.test(grid[newPoint.y][newPoint.x])) {
                    queue.add(newPoint);
                    visited.add(newPoint);
                    parentMap.put(newPoint, current);
                }
            }
        }

        return Collections.emptyList();
    }

    private static boolean isValidPoint(Point point, char[][] grid) {
        return point.x >= 0 && point.x < grid[0].length && point.y >= 0 && point.y < grid.length;
    }
}
