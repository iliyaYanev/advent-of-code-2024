package day_15;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class WarehouseWoesTest {

    private static final List<String> FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileLines("src/test/resources/day_15/dayFifteenInput.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void warehouseWoesPartOneTest() {
        long result = WarehouseWoes.boxesSum(FILE_CONTENTS);

        assertEquals(1421727, result);
    }

    @Test
    public void warehouseWoesPartTwoTest() {
        long result = WarehouseWoes.sealedBoxesSum(FILE_CONTENTS);

        assertEquals(1463160, result);
    }
}
