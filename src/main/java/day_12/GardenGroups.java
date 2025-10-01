package day_12;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import util.ArrayUtils;
import util.Region;

public class GardenGroups {

    public static long fencingPricing(List<String> fileContents, boolean newPricing) {
        Map<Character, List<Region>> regions = getRegions(fileContents);

        return regions.values().stream()
            .flatMap(Collection::stream)
            .mapToInt(region -> newPricing ? region.plots().size() * region.corners() :
                region.plots().size() * region.perimeter())
            .sum();
    }

    private static Map<Character, List<Region>> getRegions(List<String> input) {
        char[][] grid = ArrayUtils.getCharGrid(input);
        int gridSize = grid.length;
        Map<Character, List<Region>> regions = new HashMap<>();

        for (int y = 0; y < gridSize; y++) {
            for (int x = 0; x < gridSize; x++) {
                char type = grid[y][x];
                Point point = new Point(x, y);

                regions.computeIfAbsent(type, _ -> new ArrayList<>());
                List<Region> regionsToMerge = new ArrayList<>();

                for (Region region : regions.get(type)) {
                    if (region.isAdjacent(point)) {
                        regionsToMerge.add(region);
                    }
                }

                if (regionsToMerge.isEmpty()) {
                    regions.get(type).add(new Region(new HashSet<>(List.of(point))));
                } else {
                    Region mainRegion = regionsToMerge.getFirst();
                    mainRegion.plots().add(point);

                    for (int i = 1; i < regionsToMerge.size(); i++) {
                        mainRegion.merge(regionsToMerge.get(i));
                        regions.get(type).remove(regionsToMerge.get(i));
                    }
                }
            }
        }

        return regions;
    }
}
