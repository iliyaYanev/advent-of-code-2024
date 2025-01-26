package day_10;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class HoofItTest {

    private static final List<String> FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileLines("src/test/resources/day_10/dayTenInput.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void hoofItPartOneTest() {
        long result = HoofIt.trailheadsSum(FILE_CONTENTS, false);

        assertEquals(796, result);
    }

    @Test
    public void hoofItPartTwoTest() {
        long result = HoofIt.trailheadsSum(FILE_CONTENTS, true);

        assertEquals(1942, result);
    }
}
