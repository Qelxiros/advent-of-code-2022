package day16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class ElephantConfig implements Comparable<ElephantConfig> {
    public HashSet<Valve> openValves;
    public Valve currentGoal;
    public Valve elephantGoal;
    public int currentTime;
    public int elephantTime;
    public int pressureReleased;

    public ElephantConfig(HashSet<Valve> openValves, Valve currentGoal, Valve elephantGoal, int currentTime, int elephantTime, int pressureReleased) {
        this.openValves = openValves;
        this.currentGoal = currentGoal;
        this.elephantGoal = elephantGoal;
        this.currentTime = currentTime;
        this.elephantTime = elephantTime;
        this.pressureReleased = pressureReleased;
    }

    public List<ElephantConfig> getNeighbors(HashMap<String, Valve> graph, String[] relevantValves, HashMap<List<String>, Integer> dists, HashMap<String, Integer> distFromAA) {
//        if (openValves.size() == relevantValves.length && minute < 26) {
//            System.out.printf("%d, %d%n", minute, pressureReleased);
//        }
        ArrayList<ElephantConfig> neighbors = new ArrayList<>();
        if (currentGoal.name.equals("AA")) {
            for (String r1 : relevantValves) {
                for (String r2 : relevantValves) {
                    if (r2.equals(r1)) {
                        continue;
                    }
                    HashSet<Valve> newOpenValves = new HashSet<>(openValves);
                    int newPressure = pressureReleased;
                    newOpenValves.add(graph.get(r1));
                    newPressure += graph.get(r1).flowRate*(26-distFromAA.get(r1)-1);
                    newOpenValves.add(graph.get(r2));
                    newPressure += graph.get(r2).flowRate*(26-distFromAA.get(r2)-1);
                    neighbors.add(new ElephantConfig(newOpenValves, graph.get(r1), graph.get(r2), distFromAA.get(r1)+1, distFromAA.get(r2)+1, newPressure));
                }
            }
            return neighbors;
        }
        else if (currentTime < elephantTime) {
            for (String v : relevantValves) {
                Valve valve = graph.get(v);
                if (openValves.contains(valve) || currentTime+dists.get(List.of(currentGoal.name, valve.name))+1 > 26) {
                    continue;
                }
                HashSet<Valve> newOpenValves = new HashSet<>(openValves);
                int newPressure = pressureReleased;
                newOpenValves.add(valve);
                newPressure += valve.flowRate*(26-(currentTime+dists.get(List.of(currentGoal.name, valve.name))+1));
                neighbors.add(new ElephantConfig(newOpenValves, valve, elephantGoal, currentTime+dists.get(List.of(currentGoal.name, valve.name))+1, elephantTime, newPressure));
            }
        } else {
            for (String v : relevantValves) {
                Valve valve = graph.get(v);
                if (openValves.contains(valve) || elephantTime+dists.get(List.of(elephantGoal.name, valve.name))+1 > 26) {
                    continue;
                }
                HashSet<Valve> newOpenValves = new HashSet<>(openValves);
                int newPressure = pressureReleased;
                newOpenValves.add(valve);
                newPressure += valve.flowRate*(26-(elephantTime+dists.get(List.of(elephantGoal.name, valve.name))+1));
                neighbors.add(new ElephantConfig(newOpenValves, currentGoal, valve, currentTime, elephantTime+dists.get(List.of(elephantGoal.name, valve.name))+1, newPressure));
            }
        }
        return neighbors;
    }

    @Override
    public int compareTo(ElephantConfig o) {
        int result = o.openValves.size()-this.openValves.size();
        if (result == 0) {
            result = o.pressureReleased-this.pressureReleased;
        }
        return result;
    }
}
