package day_09;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class DiskFragmenterTest {

    private static final String FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileAsString("src/test/resources/day_09/dayNineInput.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void diskFragmenterPartOneTest() {
        long result = DiskFragmenter.fileSystemChecksum(FILE_CONTENTS, false);

        assertEquals(6332189866718L, result);
    }

    @Test
    public void diskFragmenterPartTwoTest() {
        long result = DiskFragmenter.fileSystemChecksum(FILE_CONTENTS, true);

        assertEquals(6353648390778L, result);
    }
}
