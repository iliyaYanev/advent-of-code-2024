package day_11;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.InputFileContents;

public class PlutonianPebblesTest {

    private static final String FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = InputFileContents.getFileAsString("src/test/resources/day_11/dayElevenInput.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void plutonianPebblesPartOneTest() {
        long result = PlutonianPebbles.stoneBlink(FILE_CONTENTS, 25);

        assertEquals(220999, result);
    }

    @Test
    public void plutonianPebblesPartTwoTest() {
        long result = PlutonianPebbles.stoneBlink(FILE_CONTENTS, 75);

        assertEquals(261936432123724L, result);
    }
}
