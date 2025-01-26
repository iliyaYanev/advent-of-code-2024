package day_07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class BridgeRepairTest {

    private static final List<String> FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileLines("src/test/resources/day_07/daySevenInput.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void bridgeRepairPartOneTest() {
        long result = BridgeRepair.calibrationResult(FILE_CONTENTS, false);

        assertEquals(7579994664753L, result);
    }

    @Test
    public void bridgeRepairPartTwoTest() {
        long result = BridgeRepair.calibrationResult(FILE_CONTENTS, true);

        assertEquals(438027111276610L, result);
    }
}
