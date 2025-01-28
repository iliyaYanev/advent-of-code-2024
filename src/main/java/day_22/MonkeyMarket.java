package day_22;

import java.util.Arrays;
import java.util.List;
import util.Sequence;

public class MonkeyMarket {

    public static long secretSum(List<String> fileContents) {
        long result = 0L;
        List<Integer> numbers = fileContents.stream()
            .map(Integer::parseInt)
            .toList();

        for (long number : numbers) {
            for (int i = 0; i < 2000; i++) {
                number = hash(number);
            }

            result += number;
        }

        return result;
    }

    public static long bananasCount(List<String> fileContents) {
        List<Integer> numbers = fileContents.stream()
            .map(Integer::parseInt)
            .toList();

        long[] result = new long[130321];
        int[] seen = new int[130321];
        int id = 0;

        for (int initial : numbers) {
            long first = hash(initial);
            long second = hash(first);
            long current = hash(second);

            Sequence sequence = new Sequence(0, diff(initial, first), diff(first, second), diff(second, current));

            for (int i = 3; i < 2000; i++) {
                long previous = current;
                current = hash(current);
                sequence = new Sequence(sequence.b(), sequence.c(), sequence.d(), diff(previous, current));
                int key = sequence.key();

                if (seen[key] != id + 1) {
                    result[key] += current % 10;
                    seen[key] = id + 1;
                }
            }

            id++;
        }

        return Arrays.stream(result).max().orElse(0);
    }

    private static long hash(long n) {
        n = (n ^ (n << 6)) & 0xffffff;
        n = (n ^ (n >> 5)) & 0xffffff;

        return (n ^ (n << 11)) & 0xffffff;
    }

    private static int diff(long previous, long current) {
        return (int) (9 + current % 10 - previous % 10);
    }
}
