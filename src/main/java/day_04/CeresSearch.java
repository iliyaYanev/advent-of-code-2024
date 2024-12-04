package day_04;

import java.util.Arrays;

public class CeresSearch {

    public static long xmasSearch(String input) {
        long xmasCount = 0;
        char[][] charInput = convertToCharArray(input);

        for (int col = 0; col < charInput.length; col++) {
            for (int row = 0; row < charInput[col].length; row++) {
                xmasCount += xmasCount(charInput, row, col);
            }
        }

        return xmasCount;
    }

    public static long masXSearch(String input) {
        long masXCount = 0;
        char[][] charInput = convertToCharArray(input);

        for (int col = 0; col < charInput.length; col++) {
            for (int row = 0; row < charInput[col].length; row++) {
                masXCount += masCount(charInput, row, col) >= 2 ? 1 : 0;
            }
        }

        return masXCount;
    }

    private static long xmasCount(char[][] input, int row, int col) {
        int[][] directions = {{1, 1}, {1, 0}, {1, -1}, {0, 1}, {0, -1}, {-1, 1}, {-1, 0}, {-1, -1}};

        return Arrays.stream(directions)
            .filter(direction -> readString(input, row, col, direction[0], direction[1]).startsWith("XMAS"))
            .count();
    }

    private static long masCount(char[][] input, int row, int col) {
        int[][] directions = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

        return Arrays.stream(directions)
            .filter(direction -> readString(input, row - direction[0], col - direction[1], direction[0], direction[1]).startsWith("MAS"))
            .count();
    }

    private static char[][] convertToCharArray(String input) {
        return Arrays.stream(input.split(System.lineSeparator()))
            .map(String::toCharArray)
            .toArray(char[][]::new);
    }

    private static String readString(char[][] input, int row, int col, int xDirection, int yDirection) {
        StringBuilder sb = new StringBuilder();

        while (col >= 0 && col < input.length && row >= 0 && row < input[col].length) {
            sb.append(input[col][row]);
            row += xDirection;
            col += yDirection;
        }

        return sb.toString();
    }
}
