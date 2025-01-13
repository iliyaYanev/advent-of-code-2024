package util;

import java.util.List;

public class Word {
    private static final char[] XMAS = "XMAS".toCharArray();
    private static final char[] MAS = "MAS".toCharArray();

    private static final List<Direction> DIRS = List.of(new Direction(1, 0), new Direction(1, 1), new Direction(0, 1), new Direction(-1, 1),
        new Direction(-1, 0), new Direction(-1, -1), new Direction(0, -1), new Direction(1, -1));

    private final List<String> word;

    public Word(List<String> fileContents) {
        this.word = fileContents;
    }

    public boolean match(char[] word, int r, int c, int dr, int dc) {
        for (int i = 0; i < word.length; i++) {
            int rr = r + i * dr;
            int cc = c + i * dc;

            if (rr < 0 || rr >= this.word.size() || cc < 0 || cc >=this.word.get(r).length() || this.word.get(rr).charAt(cc) != word[i]) {
                return false;
            }
        }

        return true;
    }

    public long xmasSes() {
        long xmases = 0;

        for (int r = 0; r < word.size(); r++) {
            for (int c = 0; c < word.get(r).length(); c++) {
                for (Direction d : DIRS) {
                    if (match(XMAS, r, c, d.dr(), d.dc())){
                        xmases++;
                    }
                }
            }
        }

        return xmases;
    }

    public long crosses() {
        long crosses = 0;

        for (int r = 0; r < word.size(); r++) {
            for (int c = 0; c < word.get(r).length(); c++) {
                if ((match(MAS, r - 1, c - 1, 1, 1) || match(MAS, r + 1, c + 1, -1, -1)) &&
                    (match(MAS, r - 1, c + 1, 1, -1) || match(MAS, r + 1, c - 1, -1, 1))) {
                    crosses++;
                }
            }
        }

        return crosses;
    }
}
