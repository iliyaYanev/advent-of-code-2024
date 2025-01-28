package day_19;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class LinenLayoutTest {

    private static final List<String> FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileLines("src/test/resources/day_19/dayNineteenInput.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void linenLayoutPartOneTest() {
        long result = LinenLayout.designCount(FILE_CONTENTS);

        assertEquals(272, result);
    }

    @Test
    public void linenLayoutPartTwoTest() {
        long result = LinenLayout.totalArrangements(FILE_CONTENTS);

        assertEquals(1041529704688380L, result);
    }
}
