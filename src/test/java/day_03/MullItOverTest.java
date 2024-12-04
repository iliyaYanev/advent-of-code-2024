package day_03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.InputFileContents;

public class MullItOverTest {

    private static final String FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = InputFileContents.getFileAsString("src/test/resources/day_03/dayThreeInput.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void mullItOverPartOneTest() {
        long result = MullItOver.decodeMemory(FILE_CONTENTS);

        assertEquals(168539636, result);
    }

    @Test
    public void mullItOverPartTwoTest() {
        long result = MullItOver.decodeMemoryMulEnabled(FILE_CONTENTS);

        assertEquals(97529391, result);
    }
}
