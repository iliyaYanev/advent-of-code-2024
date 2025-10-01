package day_08;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import util.ArrayUtils;

public class ResonantCollinearity {

    public static long antiNodeLocations(List<String> fileContents, boolean updatedModel) {
        char[][] grid = ArrayUtils.getCharGrid(fileContents);
        int gridSize = grid.length;

        Map<Character, List<Point>> antennas = getAntennas(grid, gridSize);
        Set<Point> points = new HashSet<>();

        if (updatedModel) {
            antennas.values().forEach(updatedPoints -> {
                points.addAll(updatedPoints);
                points.addAll(getAntinodes(updatedPoints, gridSize, gridSize));
            });
        }
        else {
            for (var frequency : antennas.entrySet()) {
                points.addAll(getAntinodes(frequency.getValue(), gridSize, 1));
            }
        }

        return points.size();
    }

    private static Map<Character, List<Point>> getAntennas(char[][] grid, int gridSize) {
        Map<Character,List<Point>> antennas = new HashMap<>();

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                char c = grid[i][j];

                if (c != '.') {
                    antennas.computeIfAbsent(c, _ -> new ArrayList<>()).add(new Point(i, j));
                }
            }
        }

        return antennas;
    }

    private static Set<Point> getAntinodes(List<Point> antennas, int gridSize, int maxDistance) {
        Set<Point> antinodes = new HashSet<>();

        for (int i = 0; i < antennas.size() - 1; i++) {
            for (int j = i + 1; j < antennas.size(); j++) {
                Point firstPoint = antennas.get(i);
                Point secondPoint = antennas.get(j);

                if (!firstPoint.equals(secondPoint)) {
                    Point diff = new Point(secondPoint.x - firstPoint.x, secondPoint.y - firstPoint.y);

                    for (int k = 1; k <= maxDistance; k++) {
                        addAntinode(antinodes, new Point(firstPoint.x - k * diff.x, firstPoint.y - k * diff.y), gridSize);
                        addAntinode(antinodes, new Point(secondPoint.x + k * diff.x, secondPoint.y + k * diff.y), gridSize);
                    }
                }
            }
        }

        return antinodes;
    }

    private static void addAntinode(Set<Point> antinodes, Point antinode,  int gridSize) {
        if (ArrayUtils.isValidPoint(antinode, gridSize)) {
            antinodes.add(antinode);
        }
    }
}
