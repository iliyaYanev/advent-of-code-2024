package day_04;

import java.util.List;
import util.Word;

public class CeresSearch {

    public static long xmasCount(List<String> fileContents) {
        Word word = new Word(fileContents);

        return word.xmasSes();
    }

    public static long xXmasCount(List<String> fileContents) {
        Word word = new Word(fileContents);

        return word.crosses();
    }
}
