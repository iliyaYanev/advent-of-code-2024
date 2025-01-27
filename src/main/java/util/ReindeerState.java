package util;

import java.awt.Point;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public record ReindeerState(Position position, int score, Set<Point> visited) {

    public List<ReindeerState> nextStates() {
        Point nextPoint = position.direction().move(position.point());
        Set<Point> newVisited = new HashSet<>(visited);
        newVisited.add(nextPoint);

        return List.of(
            new ReindeerState(new Position(position.point(), position.direction().rotate90(false)),
                score + 1000, visited),
            new ReindeerState(new Position(position.point(), position.direction().rotate90(true)),
                score + 1000, visited),
            new ReindeerState(new Position(nextPoint, position.direction()),
                score + 1, newVisited)
        );
    }
}
