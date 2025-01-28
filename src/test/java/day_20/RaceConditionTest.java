package day_20;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class RaceConditionTest {

    private static final List<String> FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileLines("src/test/resources/day_20/dayTwentyInput.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void raceConditionPartOneTest() {
        long result = RaceCondition.cheatsCount(FILE_CONTENTS, 2);

        assertEquals(1317, result);
    }

    @Test
    public void raceConditionPartTwoTest() {
        long result = RaceCondition.cheatsCount(FILE_CONTENTS, 20);

        assertEquals(982474, result);
    }
}
