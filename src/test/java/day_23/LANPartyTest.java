package day_23;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;
import util.GetInputFileContents;

public class LANPartyTest {

    private static final List<String> FILE_CONTENTS;

    static {
        try {
            FILE_CONTENTS = GetInputFileContents.getFileLines("src/test/resources/day_23/dayTwentyThreeInput.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void lanPartyPartOneTest() {
        long result = LANParty.tComputerCount(FILE_CONTENTS);

        assertEquals(893, result);
    }

    @Test
    public void lanPartyPartTwoTest() {
        String result = LANParty.partyPassword(FILE_CONTENTS);

        assertEquals("cw,dy,ef,iw,ji,jv,ka,ob,qv,ry,ua,wt,xz", result);
    }
}
