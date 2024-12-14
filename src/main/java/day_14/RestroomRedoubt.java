package day_14;

import java.util.Arrays;
import java.util.List;
import util.Robot;

public class RestroomRedoubt {

    public static long safetyFactor(List<String> fileContents) {
        long[] factors = new long[4];
        List<Robot> robots = parseRobots(fileContents);

        for (Robot robot : robots) {
            robot.move(100);

            if (robot.isInQuadrant()) {
                factors[robot.getQuadrant()]++;
            }
        }

        return Arrays.stream(factors).reduce(1, (x, y) -> x * y);
    }

    public static long findMinSafetyFactorTime(List<String> fileContents) {
        List<Robot> robots = parseRobots(fileContents);

        int width = 101;
        int height = 103;
        int minSafetyFactorTime = -1;
        long minSafetyFactor = Long.MAX_VALUE;


        for (int second = 0; second < width * height; second++) {
            long[] quadrantCounts = new long[4];
            int midX = (width - 1) / 2;
            int midY = (height - 1) / 2;

            for (Robot robot : robots) {
                int newX = (robot.getX() + robot.getVx() * second) % width;
                int newY = (robot.getY() + robot.getVy() * second) % height;

                if(newX < 0) {
                    newX += width;
                }

                if(newY < 0)  {
                    newY += height;
                }

                if (newX != midX && newY != midY) {
                    int quadrant = (newY < midY ? 0 : 2) + (newX < midX ? 0 : 1);
                    quadrantCounts[quadrant]++;
                }
            }

            long currentSafetyFactor = calculateSafetyFactor(quadrantCounts);

            if (currentSafetyFactor < minSafetyFactor) {
                minSafetyFactor = currentSafetyFactor;
                minSafetyFactorTime = second;
            }
        }

        return minSafetyFactorTime;
    }

    private static List<Robot> parseRobots(List<String> fileContents) {
        return fileContents.stream()
            .map(RestroomRedoubt::parseRobot)
            .map(data -> new Robot(data[0], data[1], data[2], data[3]))
            .toList();
    }

    private static int[] parseRobot(String line) {
        String[] info = line.split(" ");

        int[] positions = Arrays.stream(info[0].substring(2).split(","))
            .mapToInt(Integer::parseInt)
            .toArray();

        int[] velocities = Arrays.stream(info[1].substring(2).split(","))
            .mapToInt(Integer::parseInt)
            .toArray();

        return new int[]{positions[0], positions[1], velocities[0], velocities[1]};
    }

    private static long calculateSafetyFactor(long[] quadrantCounts) {
        long safetyFactor = 1;

        for (long count : quadrantCounts) {
            safetyFactor *= count;
        }

        return safetyFactor;
    }
}
