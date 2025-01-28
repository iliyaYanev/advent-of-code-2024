package day_17;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import util.Computer;

public class ChronospatialComputer {

    public static String joinCommas(List<String> fileContents) {
        long firstInstruction = getRegister(fileContents, 0);
        long secondInstruction = getRegister(fileContents, 1);
        long thirdInstruction = getRegister(fileContents, 2);


        List<Long> instructions = getInstructions(fileContents);

        Computer computer = new Computer(firstInstruction, secondInstruction, thirdInstruction, instructions);

        return computer.getOutput()
            .stream()
            .map(String::valueOf)
            .collect(Collectors.joining(","));
    }

    public static long lowestPositive(List<String> fileContents) {
        long secondInstruction = getRegister(fileContents, 1);
        long thirdInstruction = getRegister(fileContents, 2);
        List<Long> instructions = getInstructions(fileContents);

        Set<Long> possibleASet = new HashSet<>();
        possibleASet.add(0L);

        int programSize = instructions.size();

        for (int i = 1; i <= programSize; i++) {
            Set<Long> newPossibleASet = new HashSet<>();

            for (long possibleA : possibleASet) {
                for (long registerA = possibleA; registerA < possibleA + 8; registerA++) {
                    Computer computer = new Computer(registerA, secondInstruction, thirdInstruction, instructions);

                    if (isValid(computer.getOutput(), instructions, i, programSize)) {
                        newPossibleASet.add(i < programSize ? registerA << 3 : registerA);
                    }
                }
            }

            possibleASet = newPossibleASet;
        }

        return possibleASet
            .stream()
            .mapToLong(l -> l)
            .min()
            .orElse(0L);
    }

    private static long getRegister(List<String> fileContents, int index) {
        return Long.parseLong(fileContents.get(index).split(" ")[2]);
    }

    private static List<Long> getInstructions(List<String> input) {
        return Arrays.stream(input.get(4).split(": ")[1]
                .split(","))
            .map(Long::parseLong)
            .toList();
    }

    private static boolean isValid(List<Long> output, List<Long> instructions, int i, int programSize) {
        int outputSize = output.size();

        for (int j = i; j > 0; j--) {
            if (outputSize < i || !Objects.equals(instructions.get(programSize - i), output.get(outputSize - i))) {
                return false;
            }
        }

        return true;
    }
}
