package day_17;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class ChronospatialComputerTest {

    private static final List<String> FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileLines("src/test/resources/day_17/daySeventeenInput.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void chronospatialComputerPartOneTest() {
        String result = ChronospatialComputer.joinCommas(FILE_CONTENTS);

        assertEquals("7,3,0,5,7,1,4,0,5", result);
    }

    @Test
    public void chronospatialComputerPartTwoTest() {
        long result = ChronospatialComputer.lowestPositive(FILE_CONTENTS);

        assertEquals(202972175280682L, result);
    }
}
