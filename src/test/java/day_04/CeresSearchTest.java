package day_04;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class CeresSearchTest {

    private static final List<String> FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileLines("src/test/resources/day_04/dayFourInput.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void ceresSearchPartOneTest() {
        long result = CeresSearch.xmasCount(FILE_CONTENTS);

        assertEquals(2685, result);
    }

    @Test
    public void ceresSearchPartTwoTest() {
        long result = CeresSearch.xXmasCount(FILE_CONTENTS);

        assertEquals(2048, result);
    }
}
