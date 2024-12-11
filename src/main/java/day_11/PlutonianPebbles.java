package day_11;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlutonianPebbles {

    public static long stoneBlink(String input, int blinks) {

        return generateStones(input, blinks);
    }

    public static long generateStones(String input, int blinks) {
        Map<Long, Long> stones = Arrays.stream(input.split("\\s+"))
            .map(Long::parseLong)
            .collect(Collectors.toMap(l -> l, l -> 1L));

        for (int i = 0; i < blinks; i++) {
            Map<Long, Long> newStones = new HashMap<>();

            stones.forEach((stone, count) ->
                blink(stone).forEach(newStone -> newStones.merge(newStone, count, Long::sum))
            );

            stones = newStones;
        }

        return stones.values().stream()
            .mapToLong(Long::longValue)
            .sum();
    }

    private static List<Long> blink(long stone) {
        if (stone == 0L) {
            return List.of(1L);
        }

        String stoneString = Long.toString(stone);
        int length = stoneString.length();

        if (length % 2 == 0) {
            return List.of(
                Long.parseLong(stoneString.substring(0, length / 2)),
                Long.parseLong(stoneString.substring(length / 2))
            );
        }

        return List.of(stone * 2024);
    }
}
