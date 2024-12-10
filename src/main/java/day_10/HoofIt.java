package day_10;

import java.util.List;

public class HoofIt {

    public static long trailheadSum(List<String> fileContents, boolean rating) {
        int[][] grid = getGrid(fileContents);

        return calculateTotalScore(grid, rating);
    }

    private static int[][] getGrid(List<String> fileContents) {
        int[][] grid = new int[fileContents.size()][fileContents.getFirst().length()];

        for (int col = 0; col < fileContents.size(); col++) {
            String line = fileContents.get(col);

            for (int row = 0; row < line.length(); row++) {
                if (line.charAt(row) == '.') {
                    grid[col][row] = -1;
                }
                else {
                    grid[col][row] = line.charAt(row) - '0';
                }
            }
        }

        return grid;
    }

    private static boolean inBounds(int row, int col, int[][] grid) {
        return row >= 0 && col >= 0 && col < grid.length && row < grid[col].length;
    }

    private static long calculateScore(int row, int col, int[][] grid, boolean[][] found, boolean rating) {
        if (grid[col][row] == 9) {
            if (rating || !found[col][row]) {
                found[col][row] = true;
                return 1;
            }
            else {
                return 0;
            }
        }
        else {
            long total = 0;
            int next = grid[col][row] + 1;

            if (inBounds(row + 1, col, grid) && grid[col][row + 1] == next) {
                total += calculateScore(row + 1, col, grid, found, rating);
            }

            if (inBounds(row - 1, col, grid) && grid[col][row - 1] == next) {
                total += calculateScore(row - 1, col, grid, found, rating);
            }

            if (inBounds(row, col + 1, grid) && grid[col + 1][row] == next) {
                total += calculateScore(row, col + 1, grid, found, rating);
            }

            if (inBounds(row, col - 1, grid) && grid[col - 1][row] == next) {
                total += calculateScore(row, col - 1, grid, found, rating);
            }

            return total;
        }
    }

    private static long calculateTotalScore(int[][] grid, boolean rating) {
        long total = 0;

        for (int col = 0; col < grid.length; col++) {
            for (int row = 0; row < grid[col].length; row++) {
                if (grid[col][row] == 0) {
                    total += calculateScore(row, col, grid, new boolean[grid.length][grid[0].length], rating);
                }
            }
        }

        return total;
    }
}
