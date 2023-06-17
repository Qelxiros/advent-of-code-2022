package day16;

import java.util.*;

public class Config implements Comparable<Config> {
    public int minute;
    public HashSet<Valve> openValves;
    public Valve currentValve;
    public int pressureReleased;

    public Config(int minute, HashSet<Valve> openValves, Valve currentValve, int pressureReleased) {
        this.minute = minute;
        this.openValves = openValves;
        this.currentValve = currentValve;
        this.pressureReleased = pressureReleased;
    }

    public List<Config> getNeighbors(HashMap<String, Valve> graph, String[] relevantValves, HashMap<List<String>, Integer> dists, HashMap<String, Integer> distFromAA) {
        ArrayList<Config> neighbors = new ArrayList<>();
        HashSet<Valve> newOpenValves = new HashSet<>(openValves);
        if (currentValve.name.equals("AA")) {
            for (String r : relevantValves) {
                if (openValves.contains(graph.get(r))) {
                    continue;
                }
                neighbors.add(new Config(minute + distFromAA.get(r), newOpenValves, graph.get(r), pressureReleased));
            }
            return neighbors;
        }
        if (minute == 30) {
            return neighbors;
        }
        if (!openValves.contains(currentValve)) {
            newOpenValves.add(currentValve);
            neighbors.add(new Config(minute + 1, newOpenValves, currentValve, pressureReleased+currentValve.flowRate*(30-minute-1)));
        }
        for (String c : relevantValves) {
            int dist = dists.get(List.of(currentValve.name, c));
            if (dist == 0 || openValves.contains(graph.get(c))) {
                continue;
            }
            if (minute + dist < 30) {
                neighbors.add(new Config(minute + dists.get(List.of(currentValve.name, c)), newOpenValves, graph.get(c), pressureReleased));
            }
        }
        return neighbors;
    }

    @Override
    public int compareTo(Config o) {
        return o.pressureReleased-this.pressureReleased;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Config config = (Config) o;
        return minute == config.minute && pressureReleased == config.pressureReleased && openValves.equals(config.openValves) && currentValve.equals(config.currentValve);
    }

    @Override
    public int hashCode() {
        return Objects.hash(minute, openValves, currentValve, pressureReleased);
    }
}
