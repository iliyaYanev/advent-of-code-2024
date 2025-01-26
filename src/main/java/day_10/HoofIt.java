package day_10;

import java.awt.Point;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import util.ArrayUtils;
import util.Direction;

public class HoofIt {

    public static long trailheadsSum(List<String> fileContents, boolean rating) {
        int[][] grid = ArrayUtils.getIntGrid(fileContents);
        int result = 0;

        for (int col = 0; col < grid.length; col++) {
            for (int row = 0; row < grid[0].length; row++) {
                if (grid[col][row] == 0) {
                    result += rating ?
                        calculateRating(grid, new Point(row, col), 0) :
                        calculateScore(grid, new Point(row, col), 0, new HashSet<>());
                }
            }
        }

        return result;
    }

    private static int calculateScore(int[][] grid, Point point, int current, Set<Point> visited) {
        int score = 0;
        visited.add(point);

        if (current == 9) {
            return 1;
        }

        for (Direction direction : Direction.values()) {
            Point nextPoint = direction.move(point);

            if (ArrayUtils.isValidPoint(nextPoint, grid)
                && grid[nextPoint.y][nextPoint.x] == current + 1
                && !visited.contains(nextPoint)) {
                score += calculateScore(grid, nextPoint, current + 1, visited);
            }
        }

        return score;
    }

    private static int calculateRating(int[][] grid, Point point, int current) {
        int score = 0;

        if (current == 9) {
            return 1;
        }

        for (Direction direction : Direction.values()) {
            Point nextPoint = direction.move(point);
            if (ArrayUtils.isValidPoint(nextPoint, grid) && grid[nextPoint.y][nextPoint.x] == current + 1) {
                score += calculateRating(grid, nextPoint, current + 1);
            }
        }

        return score;
    }
}
