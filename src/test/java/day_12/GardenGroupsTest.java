package day_12;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class GardenGroupsTest {

    private static final List<String> FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileLines("src/test/resources/day_12/dayTwelveInput.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void gardenGroupsPartOneTest() {
        long result = GardenGroups.fencingPricing(FILE_CONTENTS, false);

        assertEquals(1424472, result);
    }

    @Test
    public void gardenGroupsPartTwoTest() {
        long result = GardenGroups.fencingPricing(FILE_CONTENTS, true);

        assertEquals(870202, result);
    }
}
