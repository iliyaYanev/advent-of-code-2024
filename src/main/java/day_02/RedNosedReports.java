package day_02;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class RedNosedReports {

    public static long safeReports(List<String> fileContents) {
        long safeReports = 0;

        for (String report : fileContents) {
            if (isSafe(report)) {
                safeReports++;
            }
        }

        return safeReports;
    }

    public static long safeDampenerReports(List<String> fileContents) {
        long safeDampenerReports = 0;

        for (String report : fileContents) {
            if (isSafeDampener(report)) {
                safeDampenerReports++;
            }
        }
        
        return safeDampenerReports;
    }

    private static boolean isSafe(String report) {
        List<String> levels = Arrays.stream(report.split("\\s+"))
            .toList();

        for (int i = 1; i < levels.size(); i++) {
            int difference = Integer.parseInt(levels.get(i)) - Integer.parseInt(levels.get(i - 1));

            if (Math.abs(difference) < 1 || Math.abs(difference) > 3 ||
                (difference > 0 && i > 1 && Integer.parseInt(levels.get(i - 1)) < Integer.parseInt(levels.get(i - 2)))
                || (difference < 0 && i > 1 && Integer.parseInt(levels.get(i - 1)) > Integer.parseInt(levels.get(i - 2)))) {

                return false;
            }
        }

        return true;
    }

    private static boolean isSafeDampener(String report) {
        if (isSafe(report)) {
            return true;
        }

        List<String> levels = Arrays.stream(report.split("\\s+"))
            .toList();

        return IntStream.range(0, levels.size())
            .anyMatch(i -> isSafe(removeLevel(levels, i)));
    }

    private static String removeLevel(List<String> levels, int index) {
        StringBuilder builder = new StringBuilder();
        
        for (int i = 0; i < levels.size(); i++) {
            if (i != index) {
                builder.append(levels.get(i)).append(" ");
            }
        }
        
        return builder.toString().trim();
    }
}
