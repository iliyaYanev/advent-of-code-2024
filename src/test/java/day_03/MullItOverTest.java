package day_03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class MullItOverTest {

    private static final String FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileAsString("src/test/resources/day_03/dayThreeInput.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void mullItOverPartOneTest() {
        long result = MullItOver.multiplicationResult(FILE_CONTENTS, true);

        assertEquals(168539636, result);
    }

    @Test
    public void mullItOverPartTwoTest() {
        long result = MullItOver.multiplicationResult(FILE_CONTENTS, false);

        assertEquals(97529391, result);
    }
}
