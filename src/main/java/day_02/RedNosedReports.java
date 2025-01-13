package day_02;

import java.util.List;
import java.util.stream.IntStream;

public class RedNosedReports {

    public static long safeReports(List<String> fileContents) {
        return fileContents.stream()
            .filter(RedNosedReports::isSafeReport)
            .count();
    }

    public static long safeWithDampener(List<String> fileContents) {
        return fileContents.stream()
            .filter(RedNosedReports::isSafeWithDampener)
            .count();
    }

    private static boolean isSafeReport(String report) {
        String[] levels = report.split("\\s+");

        for (int i = 1; i < levels.length; i++) {
            int diff = Integer.parseInt(levels[i]) - Integer.parseInt(levels[i - 1]);
            if (Math.abs(diff) < 1 || Math.abs(diff) > 3 ||
                (diff > 0 && i > 1 && Integer.parseInt(levels[i - 1]) < Integer.parseInt(levels[i - 2])) ||
                (diff < 0 && i > 1 && Integer.parseInt(levels[i - 1]) > Integer.parseInt(levels[i - 2]))) {

                return false;
            }
        }

        return true;
    }

    private static boolean isSafeWithDampener(String report) {
        if (isSafeReport(report)) {
            return true;
        }

        String[] levels = report.split(" ");

        return IntStream.range(0, levels.length)
            .anyMatch(i -> isSafeReport(removeLevel(levels, i)));
    }

    private static String removeLevel(String[] levels, int index) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < levels.length; i++) {
            if (i != index) {
                builder.append(levels[i]).append(" ");
            }
        }

        return builder.toString().trim();
    }
}
