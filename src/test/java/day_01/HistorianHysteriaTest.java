package day_01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.InputFileContents;

public class HistorianHysteriaTest {

    private static final List<String> FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = InputFileContents.getFileLines("src/test/resources/day_01/dayOneInput.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void historianHysteriaPartOneTest() {
        long result = HistorianHysteria.totalDistance(FILE_CONTENTS);

        assertEquals(2000468, result);
    }

    @Test
    public void historianHysteriaPartTwoTest() {
        long result = HistorianHysteria.similarityScore(FILE_CONTENTS);

        assertEquals(18567089, result);
    }
}
