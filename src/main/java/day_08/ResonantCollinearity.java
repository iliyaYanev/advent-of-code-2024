package day_08;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.math3.util.ArithmeticUtils;

public class ResonantCollinearity {

    public static long antinodeLocationsCount(List<String> fileContents, boolean harmonics) {
        long antinodeCount = 0;
        char[][] maze = parseMaze(fileContents);
        Map<Character, List<Point>> frequencies = new HashMap<>();

        for(int row = 0; row < maze.length; row++){
            for(int col = 0; col < maze[0].length; col++){
                if(maze[row][col] != '.'){
                    frequencies.computeIfAbsent(maze[row][col], k -> new ArrayList<>());
                    frequencies.get(maze[row][col]).add(new Point(row, col));
                }
            }
        }

        Set<Point> antinodes = new HashSet<>();

        for(Character c: frequencies.keySet()){
            List<Point> lst = frequencies.get(c);
            for(int row = 0; row < lst.size(); row++){
                for(int col = row + 1; col < lst.size(); col++){
                    Point fistPoint = lst.get(row);
                    Point secondPoint = lst.get(col);
                    addAntinodes(fistPoint,secondPoint,antinodes, harmonics);
                }
            }
        }

        for(Point point: antinodes){
            if(inBounds(point.x, point.y, maze)){
                antinodeCount++;
            }
        }

        return antinodeCount;
    }

    private static char[][] parseMaze(List<String> fileContents) {
        return fileContents.stream()
            .map(String::toCharArray)
            .toArray(char[][]::new);
    }

    public static boolean inBounds(int x, int y, char[][] grid){
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
    }

    private static void addAntinodes(Point firstPoint, Point secondPoint, Set<Point> antinodes, boolean harmonics){
        int diffX = firstPoint.x - secondPoint.x;
        int diffY = firstPoint.y - secondPoint.y;

        if (harmonics) {
            int gcd = ArithmeticUtils.gcd(diffY, diffX);

            for(int i = 0; i < 51; i++) {
                antinodes.add(new Point(secondPoint.x - (i * diffX/gcd), secondPoint.y - (i * diffY/gcd)));
                antinodes.add(new Point(secondPoint.x + (i * diffX/gcd), secondPoint.y + (i * diffY/gcd)));
            }
        }
        else {
            antinodes.add(new Point(secondPoint.x - diffX, secondPoint.y - diffY));
            antinodes.add(new Point(firstPoint.x + diffX, firstPoint.y + diffY));
        }
    }
}
