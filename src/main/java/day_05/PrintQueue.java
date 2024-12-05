package day_05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrintQueue {

    public static long orderedMiddlePageCount(List<String> fileContents) {
        Map<Integer, List<Integer>> rules = getRules(fileContents);
        List<List<Integer>> updates = getUpdates(fileContents);

        return updates.stream()
            .filter(update -> isValid(rules, update))
            .mapToInt(update -> update.get(update.size() / 2))
            .sum();
    }

    public static long unorderedMiddlePageCount(List<String> fileContents) {
        Map<Integer, List<Integer>> rules = getRules(fileContents);
        List<List<Integer>> updates = getUpdates(fileContents);

        return updates.stream()
            .filter(update -> !isValid(rules, update))
            .mapToInt(update -> orderUpdates(rules, update).get(update.size() / 2))
            .sum();
    }

    private static Map<Integer, List<Integer>> getRules(List<String> fileContents) {
        Map<Integer, List<Integer>> rules = new HashMap<>();

        for (String line : fileContents) {
            if (line.isEmpty()) {
                break;
            }

            List<String> parts = Arrays.stream(line.split("\\|"))
                .toList();

            rules.computeIfAbsent(Integer.parseInt(parts.getFirst()), k -> new ArrayList<>())
                .add(Integer.parseInt(parts.getLast()));
        }

        return rules;
    }

    private static List<List<Integer>> getUpdates(List<String> fileContents) {
        List<List<Integer>> updates = new ArrayList<>();
        boolean rules = true;

        for (String line : fileContents) {
            if (line.isEmpty()) {
                rules = false;
            }
            else if (!rules) {
                updates.add(Arrays.stream(line.split(","))
                    .map(Integer::parseInt)
                    .toList());
            }
        }

        return updates;
    }

    private static boolean isValid(Map<Integer, List<Integer>> rules, List<Integer> updates) {
        for (int i = 0; i < updates.size(); i++) {
            int key = updates.get(i);

            if (!rules.containsKey(key)) {
                return i == updates.size() - 1;
            }

            for (int page : updates.subList(i + 1, updates.size())) {
                if (!rules.get(key).contains(page)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static List<Integer> orderUpdates(Map<Integer, List<Integer>> rules, List<Integer> updates) {
        int[] fixed = new int[updates.size()];

        for (int page : updates) {
            int fixedIndex = 0;

            for (Map.Entry<Integer, List<Integer>> entry : rules.entrySet()) {
                if (entry.getValue().contains(page) && updates.contains(entry.getKey())) {
                    fixedIndex++;
                }
            }

            fixed[fixedIndex] = page;
        }

        return Arrays.stream(fixed).boxed().toList();
    }
}
