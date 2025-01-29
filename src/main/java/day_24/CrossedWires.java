package day_24;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CrossedWires {

    public static long zWiresOutput(List<String> fileContents) {
        Map<String, String> registers = registers(fileContents);

        return registers.keySet().stream()
            .filter(s -> s.startsWith("z"))
            .sorted(Comparator.reverseOrder())
            .mapToLong(name -> compute(registers, name))
            .reduce(0L, (current, value) -> current * 2 + value);
    }

    public static String wireNames(List<String> fileContents) {
        Map<String, String> registers = registers(fileContents);
        List<String> swaps = new ArrayList<>();

        int index = 0;
        String current = "";

        while (registers.containsKey(String.format("x%02d", index))) {
            String x = String.format("x%02d", index);
            String y = String.format("y%02d", index);
            String z = String.format("z%02d", index);

            if (index == 0) {
                current = find(registers, x, "AND", y);
            } else {
                String xor = find(registers, x, "XOR", y);
                String and = find(registers, x, "AND", y);
                String next = find(registers, xor, "XOR", current);

                if (next == null) {
                    swaps.addAll(List.of(xor, and));
                    swap(registers, xor, and);
                    index = 0;
                    continue;
                }

                if (!next.equals(z)) {
                    swaps.addAll(List.of(next, z));
                    swap(registers, next, z);
                    index = 0;
                    continue;
                }

                next = find(registers, xor, "AND", current);
                current = find(registers, and, "OR", next);
            }

            index++;
        }

        return swaps.stream()
            .sorted()
            .collect(Collectors.joining(","));
    }

    private static Map<String, String> registers(List<String> fileContents) {
        Map<String, String> registers = new HashMap<>();

        for (String line : fileContents.subList(0, fileContents.indexOf(""))) {
            String[] parts = line.split(": ");
            registers.put(parts[0], parts[1]);
        }

        for (String line : fileContents.subList(fileContents.indexOf("") + 1, fileContents.size())) {
            String[] parts = line.split(" -> ");
            registers.put(parts[1], parts[0]);
        }

        return registers;
    }

    private static int compute(Map<String, String> registers, String name) {
        String value = registers.get(name);

        if (value.matches("-?\\d+")) {
            return Integer.parseInt(value);
        }

        String[] parts = value.split("\\s+");
        int op1 = compute(registers, parts[0]);
        int op2 = compute(registers, parts[2]);

        return switch (parts[1]) {
            case "XOR" -> op1 ^ op2;
            case "AND" -> op1 & op2;
            default -> op1 | op2;
        };
    }

    private static String find(Map<String, String> registers, String op1, String op, String op2) {
        return registers.entrySet().stream()
            .filter(entry -> {
                String value = entry.getValue();
                return value.equals(op1 + " " + op + " " + op2) || value.equals(op2 + " " + op + " " + op1);
            })
            .map(Map.Entry::getKey)
            .findFirst()
            .orElse(null);
    }

    private static void swap(Map<String, String> registers, String firstRegister, String secondRegister) {
        String temp = registers.put(firstRegister, registers.get(secondRegister));
        registers.put(secondRegister, temp);
    }
}
