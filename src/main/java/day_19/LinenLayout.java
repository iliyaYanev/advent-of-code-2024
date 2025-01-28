package day_19;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LinenLayout {

    public static long designCount(List<String> fileContents) {
        int count = 0;
        String[] patterns = fileContents.getFirst().split(", ");

        for (String design : fileContents.subList(2, fileContents.size())) {
            if (isPossible(design, patterns, new HashMap<>())) {
                count++;
            }
        }

        return count;
    }

    public static long totalArrangements(List<String> fileContents) {
        long count = 0L;
        String[] patterns = fileContents.getFirst().split(", ");

        for (String design : fileContents.subList(2, fileContents.size())) {
            if (isPossible(design, patterns, new HashMap<>())) {
                count += arrangementCount(design, patterns, new HashMap<>());
            }
        }

        return count;
    }

    private static boolean isPossible(String design, String[] patterns, Map<String, Boolean> memory) {
        if (design.isEmpty()) {
            return true;
        }

        if (memory.containsKey(design)) {
            return memory.get(design);
        }

        for (String pattern : patterns) {
            if (design.startsWith(pattern)) {
                if (isPossible(design.substring(pattern.length()), patterns, memory)) {
                    memory.put(design, true);

                    return true;
                }
            }
        }

        memory.put(design, false);

        return false;
    }

    private static long arrangementCount(String design, String[] patterns, Map<String, Long> memory) {
        long count = 0L;

        if (memory.containsKey(design)) {
            return memory.get(design);
        }

        for (String pattern : patterns) {
            if (design.equals(pattern)) {
                count++;
            } else if (design.startsWith(pattern)) {
                count += arrangementCount(design.substring(pattern.length()), patterns, memory);
            }
        }

        memory.put(design, count);

        return count;
    }
}
