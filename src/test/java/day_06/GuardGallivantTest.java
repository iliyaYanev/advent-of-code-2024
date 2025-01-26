package day_06;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class GuardGallivantTest {

    private static final List<String> FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileLines("src/test/resources/day_06/daySixInput.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void guardGallivantPartOneTest() {
        long result = GuardGallivant.guardDistinctPositions(FILE_CONTENTS);

        assertEquals(5516, result);
    }

    @Test
    public void guardGallivantPartTwoTest() {
        long result = GuardGallivant.obstructionDistinctPositions(FILE_CONTENTS);

        assertEquals(2008, result);
    }
}
