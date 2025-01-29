package day_24;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class CrossedWiresTest {

    private static final List<String> FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileLines("src/test/resources/day_24/dayTwentyFourInput.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void crossedWiresPartOneTest() {
        long result = CrossedWires.zWiresOutput(FILE_CONTENTS);

        assertEquals(59619940979346L, result);
    }

    @Test
    public void crossedWiresPartTwoTest() {
        String result = CrossedWires.wireNames(FILE_CONTENTS);

        assertEquals("bpt,fkp,krj,mfm,ngr,z06,z11,z31", result);
    }
}
