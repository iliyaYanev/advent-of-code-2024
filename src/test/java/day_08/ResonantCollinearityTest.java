package day_08;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class ResonantCollinearityTest {

    private static final List<String> FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileLines("src/test/resources/day_08/dayEightInput.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void resonantCollinearityPartOneTest() {
        long result = ResonantCollinearity.antiNodeLocations(FILE_CONTENTS, false);

        assertEquals(299, result);
    }

    @Test
    public void resonantCollinearityPartTwoTest() {
        long result = ResonantCollinearity.antiNodeLocations(FILE_CONTENTS, true);

        assertEquals(1032, result);
    }
}
