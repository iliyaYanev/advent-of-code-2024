package day_05;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.InputFileContents;

public class PrintQueueTest {

    private static final List<String> FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = InputFileContents.getFileLines("src/test/resources/day_05/dayFiveInput.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void printQueuePartOneTest() {
        long result = PrintQueue.orderedMiddlePageCount(FILE_CONTENTS);

        assertEquals(6949, result);
    }

    @Test
    public void printQueuePartTwoTest() {
        long result = PrintQueue.unorderedMiddlePageCount(FILE_CONTENTS);

        assertEquals(4145, result);
    }
}
