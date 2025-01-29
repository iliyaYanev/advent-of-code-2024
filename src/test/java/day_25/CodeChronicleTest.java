package day_25;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class CodeChronicleTest {

    private static final List<String> FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileLines("src/test/resources/day_25/dayTwentyFiveInput.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void codeChronicleTest() {
        long result = CodeChronicle.uniqueKeyLockPairs(FILE_CONTENTS);

        assertEquals(3196, result);
    }
}
