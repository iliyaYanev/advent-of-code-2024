package day_03;

import static java.lang.Integer.parseInt;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MullItOver {

    public static long multiplicationResult(String input, boolean ignoreConditions) {
        Pattern pattern = Pattern.compile("(mul\\((\\d+),(\\d+)\\))|(do\\(\\))|(don't\\(\\))");
        Matcher matcher = pattern.matcher(input);
        AtomicBoolean isEnabled = new AtomicBoolean(true);

        return matcher.results()
            .mapToInt(result -> {
                if (result.group(1) != null && (ignoreConditions || isEnabled.get())) {
                    return parseInt(result.group(2)) * parseInt(result.group(3));
                }
                else if (!ignoreConditions) {
                    if (result.group(4) != null) {
                        isEnabled.set(true);
                    }
                    else if (result.group(5) != null) {
                        isEnabled.set(false);
                    }
                }

                return 0;
            })
            .sum();
    }
}
