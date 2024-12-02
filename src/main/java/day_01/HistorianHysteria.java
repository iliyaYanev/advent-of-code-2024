package day_01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.commons.lang3.tuple.Pair;

public class HistorianHysteria {

    public static long totalDistance(List<String> fileContents) {
        List<Pair<Integer, Integer>> pairs = buildPairs(fileContents);

        return calculateTotalDistance(
            pairs.stream().map(Pair::getLeft).sorted().toList(),
            pairs.stream().map(Pair::getRight).sorted().toList()
        );
    }

    public static long similarityScore(List<String> fileContents) {
        List<Pair<Integer, Integer>> pairs = buildPairs(fileContents);

        return calculateSimilarityScore(
            pairs.stream().map(Pair::getLeft).sorted().toList(),
            pairs.stream().map(Pair::getRight).sorted().toList()
        );
    }

    private static List<Pair<Integer, Integer>> buildPairs(List<String> fileContents) {
        List<Pair<Integer, Integer>> pairs = new ArrayList<>();

        fileContents.forEach(line -> {
            List<Integer> integers = Arrays.stream(line.split("\\s+"))
                .map(Integer::parseInt)
                .toList();

            pairs.add(Pair.of(integers.get(0), integers.get(1)));
        });

        return pairs;
    }

    private static long calculateTotalDistance(List<Integer> left, List<Integer> right) {
        int totalDistance = 0;

        for (int i = 0; i < left.size(); i++) {
            totalDistance += Math.abs(left.get(i) - right.get(i));
        }

        return totalDistance;
    }

    private static long calculateSimilarityScore(List<Integer> left, List<Integer> right) {
        Map<Integer, Long> rightCountMap = right.stream()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return left.stream()
            .mapToInt(num -> num * rightCountMap.getOrDefault(num, 0L).intValue())
            .sum();
    }
}
