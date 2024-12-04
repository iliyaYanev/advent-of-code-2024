package day_04;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.InputFileContents;

public class CeresSearchTest {

    private static final String FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = InputFileContents.getFileAsString("src/test/resources/day_04/dayFourInput.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void ceresSearchPartOneTest() {
        long result = CeresSearch.xmasSearch(FILE_CONTENTS);

        assertEquals(2685, result);
    }

    @Test
    public void ceresSearchPartTwoTest() {
        long result = CeresSearch.masXCount(FILE_CONTENTS);

        assertEquals(2048, result);
    }
}
