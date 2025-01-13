package day_01;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

public class HistorianHysteria {

    public static long totalDistance(List<String> fileContents) {
        Pair<List<Integer>, List<Integer>> numbers = parseInput(fileContents);

        long totalDistance = 0;

        for (int i = 0; i < numbers.getLeft().size(); i++) {
            totalDistance += Math.abs(numbers.getLeft().get(i) - numbers.getRight().get(i));
        }

        return totalDistance;
    }

    public static long similarityScore(List<String> fileContents) {
        Pair<List<Integer>, List<Integer>> numbers = parseInput(fileContents);

        long similarityScore = 0;

        for (long locationId : numbers.getLeft()) {
            int occurrenceCount = 0;

            for (long rightLocationId : numbers.getRight()) {
                if (locationId == rightLocationId) {
                    occurrenceCount++;
                }
            }

            similarityScore += locationId * occurrenceCount;
        }

        return similarityScore;
    }

    private static Pair<List<Integer>, List<Integer>> parseInput(List<String> fileContents) {
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        fileContents.forEach(line -> {
            List<Integer> numbers = Arrays.stream(line.split("\\s+"))
                .map(Integer::parseInt)
                .toList();

            left.add(numbers.getFirst());
            right.add(numbers.getLast());
        });

        Collections.sort(left);
        Collections.sort(right);

        return Pair.of(left, right);
    }
}
