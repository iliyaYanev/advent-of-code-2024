package day_21;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class KeypadConundrumTest {

    private static final List<String> FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileLines("src/test/resources/day_21/dayTwentyOneInput.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void keypadConundrumPartOneTest() {
        long result = KeypadConundrum.calculateComplexity(FILE_CONTENTS, 2);

        assertEquals(171596, result);
    }

    @Test
    public void keypadConundrumPartTwoTest() {
        long result = KeypadConundrum.calculateComplexity(FILE_CONTENTS, 25);

        assertEquals(209268004868246L, result);
    }
}
