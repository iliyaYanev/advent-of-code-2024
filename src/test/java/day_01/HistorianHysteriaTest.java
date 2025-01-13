package day_01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class HistorianHysteriaTest {

    private static final List<String> FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileLines("src/test/resources/day_01/dayOneInput.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void historianHysteriaPartOneTest() {
        long result = HistorianHysteria.totalDistance(FILE_CONTENTS);

        assertEquals(result, 2000468);
    }

    @Test
    public void historianHysteriaPartTwoTest() {
        long result = HistorianHysteria.similarityScore(FILE_CONTENTS);

        assertEquals(result, 18567089);
    }
}
