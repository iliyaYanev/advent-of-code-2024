package day_13;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class ClawContraptionTest {

    private static final List<String> FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileLines("src/test/resources/day_13/dayThirteenInput.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void clawContraptionPartOneTest() {
        long result = ClawContraption.tokens(FILE_CONTENTS, 0);

        assertEquals(32041, result);
    }

    @Test
    public void clawContraptionPartTwoTest() {
        long result = ClawContraption.tokens(FILE_CONTENTS, 10000000000000L);

        assertEquals(95843948914827L, result);
    }
}
