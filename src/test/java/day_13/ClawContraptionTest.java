package day_13;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.InputFileContents;

public class ClawContraptionTest {

    private static final String FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = InputFileContents.getFileAsString("src/test/resources/day_13/dayThirteenInput.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void clawContraptionPartOneTest() {
        long result = ClawContraption.fewestTokens(FILE_CONTENTS, 0);

        assertEquals(32041, result);
    }

    @Test
    public void clawContraptionPartTwoTest() {
        long result = ClawContraption.fewestTokens(FILE_CONTENTS, 10000000000000L);

        assertEquals(95843948914827L, result);
    }
}
