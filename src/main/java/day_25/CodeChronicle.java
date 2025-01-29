package day_25;

import java.util.ArrayList;
import java.util.List;
import util.ArrayUtils;

public class CodeChronicle {

    public static long uniqueKeyLockPairs(List<String> fileContents) {
        long pairs = 0;
        List<char[][]> locks = new ArrayList<>();
        List<char[][]> keys = new ArrayList<>();

        for (int i = 0; i < fileContents.size(); i += 8) {
            (fileContents.get(i).startsWith("#") ? locks : keys)
                .add(ArrayUtils.getCharGrid(fileContents.subList(i + 1, i + 6)));
        }

        for (char[][] lock : locks) {
            for (char[][] key : keys) {
                if (fits(lock, key)) {
                    pairs++;
                }
            }
        }

        return pairs;
    }

    private static boolean fits(char[][] lock, char[][] key) {
        for (int y = 0; y < key.length; y++) {
            for (int x = 0; x < key[y].length; x++) {
                if (lock[y][x] == '#' && key[y][x] == '#') {
                    return false;
                }
            }
        }

        return true;
    }
}
