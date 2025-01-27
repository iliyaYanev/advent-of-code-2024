package day_13;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClawContraption {

    public static long tokens(List<String> fileContents, long offset) {
        long tokens = 0;
        Pattern pattern = Pattern.compile("(\\d{1,5})");
        List<Long> parts = new ArrayList<>();

        for (String line : fileContents) {
            Matcher matcher = pattern.matcher(line);

            while (matcher.find()) {
                parts.add(Long.parseLong(matcher.group(1)));
            }
        }

        for (int i = 0; i < parts.size(); i += 6) {
            tokens += min(parts.get(i), parts.get(i+1), parts.get(i+2), parts.get(i+3),
                parts.get(i+4) + offset, parts.get(i+5) + offset);
        }

        return tokens;
    }

    private static long min(long buttonAx, long buttonAy, long buttonBx, long buttonBy, long prizeX, long priceY) {
        long bPress = (prizeX * buttonAy - priceY * buttonAx) / (buttonBx * buttonAy - buttonBy * buttonAx);
        long aPress = (prizeX - bPress * buttonBx) / buttonAx;

        return (aPress * buttonAx + bPress * buttonBx == prizeX && aPress * buttonAy + bPress * buttonBy == priceY) ? 3 * aPress + bPress : 0;
    }
}
