package util;

import java.util.ArrayList;
import java.util.List;

public record Computer(long first, long second, long third, List<Long> instructions) {

    public List<Long> getOutput() {
        long firstInstruction = first;
        long secondInstruction = second;
        long thirdInstruction = third;

        List<Long> output = new ArrayList<>();
        int pointer = 0;

        while (pointer < instructions.size()) {
            long opcode = instructions.get(pointer);
            long operand = instructions.get(pointer + 1);

            long combo = switch ((int) operand) {
                case 4 -> firstInstruction;
                case 5 -> secondInstruction;
                case 6 -> thirdInstruction;
                default -> operand;
            };

            switch ((int) opcode) {
                case 0 -> firstInstruction /= (long) Math.pow(2, combo);
                case 1 -> secondInstruction ^= operand;
                case 2 -> secondInstruction = combo % 8;
                case 3 -> pointer = (firstInstruction != 0L) ? (int) (operand - 2) : pointer;
                case 4 -> secondInstruction ^= thirdInstruction;
                case 5 -> output.add(combo % 8);
                case 6 -> secondInstruction = (long) (firstInstruction / Math.pow(2, combo));
                case 7 -> thirdInstruction = (long) (firstInstruction / Math.pow(2, combo));
            }

            pointer += 2;
        }

        return output;
    }
}
