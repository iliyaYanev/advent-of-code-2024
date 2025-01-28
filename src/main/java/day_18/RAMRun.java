package day_18;

import java.awt.Point;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import util.ArrayUtils;

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

        return ArrayUtils.findShortestPath(memory, start, end, c -> c != '#').size() - 1;
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
                lastValidPath = ArrayUtils.findShortestPath(memory, start, end, isNotAWall);
            }

            if (i > bytes && lastValidPath.contains(point)) {
                lastValidPath = ArrayUtils.findShortestPath(memory, start, end, isNotAWall);

                if (lastValidPath.isEmpty()) {
                    return fileContents.get(i);
                }
            }
        }

        return null;
    }
}
