package day_20;

import java.awt.Point;
import java.util.List;
import util.ArrayUtils;

public class RaceCondition {

    public static long cheatsCount(List<String> fileContents, int maxDistance) {
        char[][] raceTrack = ArrayUtils.getCharGrid(fileContents);

        Point start = ArrayUtils.findChar(raceTrack, 'S');
        Point end = ArrayUtils.findChar(raceTrack, 'E');

        List<Point> path = ArrayUtils.findShortestPath(raceTrack, start, end, c -> c != '#');
        int cheats = 0;

        for (int i = 0; i < path.size() - 1; i++) {
            for (int j = i + 1; j < path.size(); j++) {
                int distance = manhattanDistance(path.get(i), path.get(j));

                if (distance <= maxDistance && (j - i - distance) >= 100) {
                    cheats++;
                }
            }
        }

        return cheats;
    }

    private static int manhattanDistance(Point a, Point b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }
}
