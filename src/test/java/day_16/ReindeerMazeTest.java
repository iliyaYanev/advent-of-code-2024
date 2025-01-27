package day_16;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class ReindeerMazeTest {

    private static final List<String> FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileLines("src/test/resources/day_16/daySixteenInput.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void reindeerMazePartOneTest() {
        long result = ReindeerMaze.lowestScore(FILE_CONTENTS);

        assertEquals(74392, result);
    }

    @Test
    public void reindeerMazePartTwoTest() {
        long result = ReindeerMaze.lowestTiles(FILE_CONTENTS);

        assertEquals(426, result);
    }
}
