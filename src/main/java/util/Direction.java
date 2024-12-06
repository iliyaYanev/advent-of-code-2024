package util;

import java.awt.Point;

public enum Direction {

    NORTH, SOUTH, EAST, WEST;

    public static Point getPoint(Direction direction, Point start) {
        return switch (direction) {
            case NORTH -> new Point(start.x, start.y - 1);
            case SOUTH -> new Point(start.x, start.y + 1);
            case EAST -> new Point(start.x + 1, start.y);
            case WEST -> new Point(start.x - 1, start.y);
        };
    }

    public static Direction turn(Direction direction, boolean clockwise) {
        return switch (direction) {
            case NORTH -> clockwise ? EAST : WEST;
            case SOUTH -> clockwise ? WEST : EAST;
            case EAST -> clockwise ? SOUTH : NORTH;
            case WEST -> clockwise ? NORTH : SOUTH;
        };
    }

    public static Direction reverse(Direction direction) {
        return switch (direction) {
            case NORTH -> SOUTH;
            case SOUTH -> NORTH;
            case EAST -> WEST;
            case WEST -> EAST;
        };
    }
}
