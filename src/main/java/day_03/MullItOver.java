package day_03;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MullItOver {

    public static long decodeMemory(String input) {
        return decode(input, true);
    }

    public static long decodeMemoryMulEnabled(String input) {
        return decode(input, false);
    }

    private static long decode(String input, boolean ignore) {
        Pattern pattern = Pattern.compile("(mul\\((\\d+),(\\d+)\\))|(do\\(\\))|(don't\\(\\))");
        Matcher matcher = pattern.matcher(input);

        AtomicBoolean isEnabled = new AtomicBoolean(true);

        return matcher.results()
            .mapToInt(result -> {
                if (result.group(1) != null && (ignore || isEnabled.get())) {
                    return Integer.parseInt(result.group(2)) * Integer.parseInt(result.group(3));
                }
                else if (!ignore) {
                    if (result.group(4) != null) {
                        isEnabled.set(true);
                    }
                    else if (result.group(5) != null) {
                        isEnabled.set(false);
                    }
                }

                return 0;
            }).sum();
    }
}
