package day_23;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class LANParty {

    public static long tComputerCount(List<String> fileContents) {
        return getLan(getNetworkMap(fileContents)).stream()
            .filter(set -> set.stream().anyMatch(s -> s.startsWith("t")))
            .count();
    }

    public static String partyPassword(List<String> fileContents) {
        Map<String, Set<String>> networkMap = getNetworkMap(fileContents);
        Set<Set<String>> lanSet = getLan(networkMap);

        for (String computer : networkMap.keySet()) {
            Set<String> connections = networkMap.get(computer);

            for (Set<String> lan : lanSet) {
                if (lan.contains(computer)) {
                    for (String connection : connections) {
                        if (!lan.contains(connection) && networkMap.get(connection).containsAll(lan)) {
                            lan.add(connection);
                        }
                    }
                }
            }
        }

        return lanSet.stream()
            .max(Comparator.comparingInt(Set::size))
            .orElse(Collections.emptySet())
            .stream()
            .sorted()
            .collect(Collectors.joining(","));
    }

    private static Set<Set<String>> getLan(Map<String, Set<String>> map) {
        Set<Set<String>> lanSet = new HashSet<>();

        for (String firstComputer : map.keySet()) {
            Set<String> connections = map.get(firstComputer);

            for (String secondComputer : connections) {
                for (String thirdComputer : map.get(secondComputer)) {
                    if (connections.contains(thirdComputer)) {
                        lanSet.add(new HashSet<>(List.of(firstComputer, secondComputer, thirdComputer)));
                    }
                }
            }
        }

        return lanSet;
    }

    private static Map<String, Set<String>> getNetworkMap(List<String> input) {
        Map<String, Set<String>> networkMap = new HashMap<>();

        for (String line : input) {
            String[] connection = line.split("-");

            networkMap.computeIfAbsent(connection[0], k -> new HashSet<>()).add(connection[1]);
            networkMap.computeIfAbsent(connection[1], k -> new HashSet<>()).add(connection[0]);
        }

        return networkMap;
    }
}
