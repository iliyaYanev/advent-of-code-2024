package day_14;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class RestroomRedoubtTest {

    private static final List<String> FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileLines("src/test/resources/day_14/dayFourteenInput.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void restroomRedoubtPartOneTest() {
        long result = RestroomRedoubt.safetyFactor(FILE_CONTENTS);

        assertEquals(208437768, result);
    }

    @Test
    public void restroomRedoubtPartTwoTest() {
        long result = RestroomRedoubt.easterEgg(FILE_CONTENTS);

        assertEquals(7492, result);
    }
}
