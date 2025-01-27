package util;

import java.awt.Point;
import java.util.List;

public class ArrayUtils {

    public static int[][] getIntGrid(List<String> input) {
        return input.stream()
            .map(s -> s.chars().map(Character::getNumericValue).toArray())
            .toArray(int[][]::new);
    }

    public static char[][] getCharGrid(List<String> fileContents) {
        return fileContents.stream()
            .map(String::toCharArray)
            .toArray(char[][]::new);
    }

    public static boolean isValidPoint(Point point, int gridSize) {
        return point.x >= 0 && point.x < gridSize && point.y >= 0 && point.y < gridSize;
    }

    public static boolean isValidPoint(Point point, int[][] grid) {
        return point.x >= 0 && point.x < grid[0].length && point.y >= 0 && point.y < grid.length;
    }

    public static Point findChar(char[][] grid, char c) {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                if (grid[y][x] == c) {
                    return new Point(x, y);
                }
            }
        }

        return new Point(-1, -1);
    }
}
