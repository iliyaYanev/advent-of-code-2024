package day_16;

import java.awt.Point;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import util.ArrayUtils;
import util.Direction;
import util.Position;
import util.ReindeerState;

public class ReindeerMaze {

    public static long lowestScore(List<String> fileContents) {
        char[][] maze = ArrayUtils.getCharGrid(fileContents);
        int i = fileContents.size() - 2;

        Position start = new Position(new Point(1, i), Direction.EAST);
        Point end = new Point(i, 1);

        Map<Position, Integer> seen = new HashMap<>();
        seen.put(start, 0);
        int min = Integer.MAX_VALUE;

        Queue<ReindeerState> queue = new PriorityQueue<>(Comparator.comparing(ReindeerState::score));
        queue.add(new ReindeerState(start, 0, new HashSet<>(List.of(start.point()))));

        while (!queue.isEmpty()) {
            ReindeerState state = queue.poll();

            for (ReindeerState next : state.nextStates()) {
                if (next.score() >= min) {
                    break;
                }

                if (maze[next.position().point().y][next.position().point().x] != '#'
                    && next.score() < seen.getOrDefault(next.position(), Integer.MAX_VALUE)) {
                    queue.add(next);
                    seen.put(next.position(), next.score());

                    if (end.equals(next.position().point())) {
                        min = next.score();
                    }
                }
            }
        }

        return min;
    }

    public static long lowestTiles(List<String> fileContents) {
        char[][] maze = ArrayUtils.getCharGrid(fileContents);
        int i = fileContents.size() - 2;

        Position start = new Position(new Point(1, i), Direction.EAST);
        Point end = new Point(i, 1);

        Set<Point> bestSpots = new HashSet<>();
        Map<Position, Integer> seen = new HashMap<>();
        seen.put(start, 0);
        int min = Integer.MAX_VALUE;

        Queue<ReindeerState> queue = new PriorityQueue<>(Comparator.comparing(ReindeerState::score));
        Map<Point, Set<Point>> paths = new HashMap<>();

        queue.add(new ReindeerState(start, 0, new HashSet<>(List.of(start.point()))));

        while (!queue.isEmpty()) {
            ReindeerState state = queue.poll();

            for (ReindeerState next : state.nextStates()) {
                if (next.score() > min) {
                    break;
                }
                if (maze[next.position().point().y][next.position().point().x] != '#') {
                    int score = seen.getOrDefault(next.position(), Integer.MAX_VALUE);

                    if (next.score() < score) {
                        queue.add(next);
                        seen.put(next.position(), next.score());

                        if (end.equals(next.position().point())) {
                            min = next.score();
                            bestSpots.addAll(next.visited());
                        }

                        paths.put(next.position().point(), next.visited());
                    } else if (next.score() == score) {
                        paths.get(next.position().point()).addAll(next.visited());
                    }
                }
            }
        }

        return bestSpots.size();
    }
}
