package day_22;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class MonkeyMarketTest {

    private static final List<String> FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileLines("src/test/resources/day_22/dayTwentyTwoInput.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void monkeyMarketPartOneTest() {
        long result = MonkeyMarket.secretSum(FILE_CONTENTS);

        assertEquals(20068964552L, result);
    }

    @Test
    public void monkeyMarketPartTwoTest() {
        long result = MonkeyMarket.bananasCount(FILE_CONTENTS);

        assertEquals(2246, result);
    }
}
