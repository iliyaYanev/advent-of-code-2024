package day_18;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class RAMRunTest {

    private static final List<String> FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileLines("src/test/resources/day_18/dayEighteenInput.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void ramRunPartOneTest() {
        long result = RAMRun.minimumSteps(FILE_CONTENTS);

        assertEquals(360, result);
    }

    @Test
    public void ramRunPartTwoTest() {
        String result = RAMRun.firstByteCoordinates(FILE_CONTENTS);

        assertEquals("58,62", result);
    }
}
