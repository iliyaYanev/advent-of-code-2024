package day_14;

import java.awt.Point;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import util.Robot;

public class RestroomRedoubt {

    public static long safetyFactor(List<String> fileContents) {
        int width = 101;
        int height = 103;
        List<Long> quadrants = Arrays.asList(0L, 0L, 0L, 0L);

        List<Robot> robots = fileContents.stream()
            .map(Robot::from)
            .toList();

        for (Robot robot : robots) {
            Point finalPoint = robot.move(100, width, height);

            if (finalPoint.x < width / 2 && finalPoint.y < height / 2) {
                quadrants.set(0, quadrants.getFirst() + 1);
            } else if (finalPoint.x > width / 2 && finalPoint.y < height / 2) {
                quadrants.set(1, quadrants.get(1) + 1);
            } else if (finalPoint.x < width / 2 && finalPoint.y > height / 2) {
                quadrants.set(2, quadrants.get(2) + 1);
            } else if (finalPoint.x > width / 2 && finalPoint.y > height / 2) {
                quadrants.set(3, quadrants.get(3) + 1);
            }
        }

        return quadrants.get(0) * quadrants.get(1) * quadrants.get(2) * quadrants.get(3);
    }

    public static long easterEgg(List<String> fileContents) {
        List<Robot> robots = fileContents.stream()
            .map(Robot::from)
            .toList();

        int nbRobots = robots.size();
        int i = 0;

        while (true) {
            int finalI = i;

            Set<Point> points = robots.stream()
                .map(r -> r.move(finalI, 101, 103))
                .collect(Collectors.toSet());

            if (points.size() == nbRobots) {
                break;
            }

            i++;
        }

        return i;
    }
}
