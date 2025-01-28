package day_21;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import util.ArrayUtils;
import util.Direction;
import util.Node;

public class KeypadConundrum {

    public static long calculateComplexity(List<String> fileContents, int robotCount) {
        long totalComplexity = 0;

        final char[][] keypad = {
            {'#', '#', '#', '#', '#'},
            {'#', '7', '8', '9', '#'},
            {'#', '4', '5', '6', '#'},
            {'#', '1', '2', '3', '#'},
            {'#', '#', '0', 'A', '#'},
            {'#', '#', '#', '#', '#'}
        };

        for (var code : fileContents) {
            totalComplexity += Integer.parseInt(code.substring(0, 3)) * unlock(keypad, code, robotCount, new HashMap<>());
        }

        return totalComplexity;
    }

    private static long unlock(char[][] grid, String code, int robots, Map<String, Long> memo) {
        char[][] keypad = {
            {'#', '#', '#', '#', '#'},
            {'#', '#', '^', 'A', '#'},
            {'#', '<', 'v', '>', '#'},
            {'#', '#', '#', '#', '#'}
        };

        String key = code + "_" + robots;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        char curPos = 'A';
        long length = 0L;

        for (char nextPos : code.toCharArray()) {
            List<String> paths = findAllMinPaths(grid, curPos, nextPos);

            if (robots == 0) {
                length += paths.getFirst().length();
            } else {
                long minPathLength = Long.MAX_VALUE;

                for (String path : paths) {
                    minPathLength = Math.min(minPathLength, unlock(keypad, path, robots - 1, memo));
                }

                length += minPathLength;
            }

            curPos = nextPos;
        }

        memo.put(key, length);

        return length;
    }

    private static List<String> findAllMinPaths(char[][] grid, char s, char e) {
        Point start = ArrayUtils.findChar(grid, s);
        Point end = ArrayUtils.findChar(grid, e);

        Queue<Node> queue = new LinkedList<>(List.of(new Node(start, new ArrayList<>(), 0)));

        Map<Point, Integer> seen = new HashMap<>();
        List<List<Character>> paths = new ArrayList<>();

        int minCost = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            Point curPoint = curNode.p();

            if (curNode.direction() != null) {
                curNode.path().add(curNode.direction().getChar());
            }

            if (curPoint.equals(end)) {
                if (curNode.cost() < minCost) {
                    paths.clear();
                    minCost = curNode.cost();
                }

                if (curNode.cost() == minCost) {
                    paths.add(new ArrayList<>(curNode.path()));
                }

                continue;
            }

            if (seen.getOrDefault(curPoint, Integer.MAX_VALUE) < curNode.cost()) {
                continue;
            }

            seen.put(curPoint, curNode.cost());

            if (curNode.cost() > minCost) {
                continue;
            }

            for (Direction direction : Direction.values()) {
                Point nextPoint = direction.move(curPoint);

                if (grid[nextPoint.y][nextPoint.x] != '#') {
                    queue.add(new Node(nextPoint, new ArrayList<>(curNode.path()), curNode.cost() + 1, direction));
                }
            }
        }

        List<String> result = new ArrayList<>();

        for (List<Character> path : paths) {
            StringBuilder sb = new StringBuilder();
            for (char c : path) {
                sb.append(c);
            }

            sb.append('A');
            result.add(sb.toString());
        }

        return result;
    }
}
