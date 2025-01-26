package day_07;

import java.util.Arrays;
import java.util.List;

public class BridgeRepair {

    public static long calibrationResult(List<String> fileContents, boolean concatenate) {
        long sum = 0;

        for (String line : fileContents) {
            String[] parts = line.split(": ");
            long result = Long.parseLong(parts[0]);

            if (isValid(result, parts[1], concatenate)) {
                sum += result;
            }
        }

        return sum;
    }

    private static boolean isValid(long result, String part, boolean concatenate) {
        Long[] numbers = Arrays.stream(part.split(" "))
            .map(Long::parseLong)
            .toArray(Long[]::new);

        return calculate(numbers, 0, numbers[0], result, concatenate);
    }

    private static boolean calculate(Long[] numbers, int index, long currentResult, long target, boolean concatenate) {
        if (currentResult > target) {
            return false;
        }

        if (index == numbers.length - 1) {
            return currentResult == target;
        }

        if (concatenate) {
            return  calculate(numbers, index + 1, currentResult + numbers[index + 1], target, true) ||
                calculate(numbers, index + 1, currentResult * numbers[index + 1], target, true) ||
                calculate(numbers, index + 1, Long.parseLong(currentResult + "" + numbers[index + 1]), target, true);
        }
        else {
            return  calculate(numbers, index + 1, currentResult + numbers[index + 1], target, false) ||
                calculate(numbers, index + 1, currentResult * numbers[index + 1], target, false);
        }
    }
}
