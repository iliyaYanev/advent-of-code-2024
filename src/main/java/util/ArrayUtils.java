package util;

import java.awt.Point;
import java.util.List;

public class ArrayUtils {

    public static char[][] getCharGrid(List<String> fileContents) {
        return fileContents.stream()
            .map(String::toCharArray)
            .toArray(char[][]::new);
    }

    public static boolean isValidPoint(Point point, int gridSize) {
        return point.x >= 0 && point.x < gridSize && point.y >= 0 && point.y < gridSize;
    }
}
