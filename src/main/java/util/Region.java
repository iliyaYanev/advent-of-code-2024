package util;

import java.awt.Point;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public record Region(Set<Point> plots) {

    public boolean isAdjacent(Point point) {
        return Arrays.stream(Direction.values())
            .map(direction -> direction.move(point))
            .anyMatch(plots::contains);
    }

    public void merge(Region other) {
        plots.addAll(other.plots);
    }

    public int perimeter() {
        int perimeter = 0;

        for (Point plot : plots) {
            for (Direction direction : Direction.values()) {
                Point neighbor = direction.move(plot);
                if (!plots.contains(neighbor)) {
                    perimeter++;
                }
            }
        }

        return perimeter;
    }

    public int corners() {
        int total = 0;

        for (final Point point : plots) {
            int corners = 0;
            Point north = Direction.NORTH.move(point);
            Point south = Direction.SOUTH.move(point);
            Point east = Direction.EAST.move(point);
            Point west = Direction.WEST.move(point);
            Point northEast = Direction.EAST.move(north);
            Point northWest = Direction.WEST.move(north);
            Point southEast = Direction.EAST.move(south);
            Point southWest = Direction.WEST.move(south);

            corners = getCorners(corners, north, east, west, northEast, northWest);

            corners = getCorners(corners, south, west, east, southWest, southEast);

            total += corners;
        }

        return total;
    }

    private int getCorners(int corners, Point north, Point east, Point west, Point northEast,
        Point northWest) {
        if (containsNone(plots, List.of(west, north))
            || !plots.contains(northWest) && plots.containsAll(List.of(west, north))) {
            corners++;
        }

        if (containsNone(plots, List.of(east, north))
            || !plots.contains(northEast) && plots.containsAll(List.of(east, north))) {
            corners++;
        }
        return corners;
    }

    public static boolean containsNone(Set<Point> set, List<Point> points) {
        return points.stream().noneMatch(set::contains);
    }
}
