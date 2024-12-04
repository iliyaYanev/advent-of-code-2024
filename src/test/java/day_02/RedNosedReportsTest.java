package day_02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.InputFileContents;

public class RedNosedReportsTest {

    private static final List<String> FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = InputFileContents.getFileLines("src/test/resources/day_02/dayTwoInput.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void RedNosedReportsPartOneTest() {
        long result = RedNosedReports.safeReports(FILE_CONTENTS);

        assertEquals(369, result);
    }

    @Test
    public void RedNosedReportsPartTwoTest() {
        long result = RedNosedReports.safeDampenerReports(FILE_CONTENTS);

        assertEquals(428, result);
    }
}
