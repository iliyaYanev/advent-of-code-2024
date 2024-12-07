package day_07;

import java.util.Arrays;
import java.util.List;

public class BridgeRepair {

    public static long totalCalibrationResult(List<String> fileContents) {
        long totalCalibration = 0;

        for (String line : fileContents) {
            List<String> parts = Arrays.stream(line.split(": "))
                .toList();

            long result = Long.parseLong(parts.getFirst());

            if (isValid(result, parts.getLast(), false)) {
                totalCalibration += result;
            }
        }

        return totalCalibration;
    }

    public static long totalCalibrationConcatenateResult(List<String> fileContents) {
        long totalCalibration = 0;

        for (String line : fileContents) {
            List<String> parts = Arrays.stream(line.split(": "))
                .toList();

            long result = Long.parseLong(parts.getFirst());

            if (isValid(result, parts.getLast(), true)) {
                totalCalibration += result;
            }
        }

        return totalCalibration;
    }

    private static boolean isValid(long result, String part, boolean addConcatenate) {
        List<Long> numbers = Arrays.stream(part.split("\\s+")).map(Long::parseLong).toList();

        return evaluate(numbers, 0, numbers.getFirst(), result, addConcatenate);
    }

    private static boolean evaluate(List<Long> numbers, int index, long currentResult, long target, boolean addConcatenate) {
        if (currentResult > target) {
            return false;
        }

        if (index == numbers.size() - 1) {
            return currentResult == target;
        }

        if (addConcatenate) {
            return  evaluate(numbers, index + 1, currentResult + numbers.get(index + 1), target, true) ||
                evaluate(numbers, index + 1, currentResult * numbers.get(index + 1), target, true) ||
                evaluate(numbers, index + 1, Long.parseLong(currentResult + "" + numbers.get(index + 1)), target, true);
        }
        else {
            return  evaluate(numbers, index + 1, currentResult + numbers.get(index + 1), target, false) ||
                evaluate(numbers, index + 1, currentResult * numbers.get(index + 1), target, false);
        }
    }
}
