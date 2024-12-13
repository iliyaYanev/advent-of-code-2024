package day_13;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import util.Game;

public class ClawContraption {

    public static long fewestTokens(String input, long offset) {
        List<Game> games = parseInput(input);

        return findSolution(games, offset);
    }

    private static List<Game> parseInput(String input) {
        Scanner scanner = new Scanner(input);
        List<Game> games = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String[] buttonA = scanner.nextLine().split(":")[1].trim().split(", ");
            String[] buttonB = scanner.nextLine().split(":")[1].trim().split(", ");
            String[] prize = scanner.nextLine().split(":")[1].trim().split(", ");

            games.add(
                new Game(
                    new int[]{Integer.parseInt(buttonA[0].split("\\+")[1]),
                        Integer.parseInt(buttonA[1].split("\\+")[1])},
                    new int[]{Integer.parseInt(buttonB[0].split("\\+")[1]),
                        Integer.parseInt(buttonB[1].split("\\+")[1])},
                    new int[]{Integer.parseInt(prize[0].split("=")[1]),
                        Integer.parseInt(prize[1].split("=")[1])}
                )
            );

            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
        }

        return games;
    }

    private static long findSolution(List<Game> games, long offset) {
        long totalTokensA = 0;
        long totalTokensB = 0;

        for (Game game : games) {
            long[] solutions = solveEquations(game.buttonA()[0], game.buttonB()[0], game.prize()[0] + offset,
                game.buttonA()[1], game.buttonB()[1], game.prize()[1] + offset);

            if (solutions[0] != -1L && solutions[1] != -1L) {
                totalTokensA += solutions[0] * 3;
                totalTokensB += solutions[1];
            }
        }

        return totalTokensA + totalTokensB;
    }

    private static long[] solveEquations(long x1, long y1, long a1, long x2, long y2, long a2) {
        long det = x1 * y2 - y1 * x2;

        if (det == 0) {
            return new long[]{-1, -1};
        }

        long detX = a1 * y2 - y1 * a2;
        long detY = x1 * a2 - a1 * x2;

        if (detX % det != 0 || detY % det != 0) {
            return new long[]{-1, -1};
        }

        return new long[]{detX / det, detY / det};
    }
}
