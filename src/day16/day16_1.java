package day16;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class day16_1 {
    public static void main(String[] args) throws IOException {
        //        String input = Files.readString(Paths.get("src/input/input16.txt"));
        String input = Files.readString(Paths.get("src/day16/test.txt"));
//        String[] relevantValves = new String[]{"LM", "GJ", "CG", "AJ", "KU", "FR", "VD", "FV", "RC", "WI", "AX", "OF", "ZR", "PI", "QO"};
        String[] relevantValves = new String[]{"BB","CC","DD","EE","HH","JJ"};
        String[] lines = input.split("\r?\n");

        HashMap<String, Valve> valves = new HashMap<>();

        for (String line : lines) {
            valves.put(line.split(" ")[1],new Valve(line));
        }

        HashMap<String, Integer> distFromAA = new HashMap<>();
        for (String valve : valves.keySet()) {
            if (valves.get(valve).flowRate > 0) {
                distFromAA.put(valve, bfs("AA", valve, valves));
            }
        }

        HashMap<List<String>, Integer> dists = new HashMap<>();
        for (String v1 : relevantValves) {
            for (String v2 : relevantValves) {
                dists.put(List.of(v1,v2),bfs(v1,v2,valves));
            }
        }

        Config start = new Config(0, new HashSet<>(), valves.get("AA"), 0);
        PriorityQueue<Config> queue = new PriorityQueue<>();
        queue.add(start);
        int most = 0;
        while (!queue.isEmpty()) {
            Config current = queue.poll();
            List<Config> neighbors = current.getNeighbors(valves, relevantValves, dists, distFromAA);
            if (neighbors.size() == 0) {
                most = Math.max(most, current.pressureReleased);
                System.out.println(most);
                continue;
            }
            queue.addAll(neighbors);
        }
        System.out.println(most);
    }

    public static int bfs(String start, String end, HashMap<String, Valve> graph) {
        Map<String, String> predecessor = new HashMap<>();
        predecessor.put(start, null);
        Queue<String> toVisit = new LinkedList<>();
        toVisit.offer(start);
        boolean finished = false;

        while (!toVisit.isEmpty() && !finished) {
            String thisNode = toVisit.remove();
            for (String nbr : graph.get(thisNode).neighbors) {
                if (!predecessor.containsKey(nbr)) {
                    predecessor.put(nbr, thisNode);
                    toVisit.offer(nbr);
                    if (nbr.equals(end)) {
                        finished = true;
                    }
                }
            }
        }

        List<String> path = new LinkedList<>();
        path.add(0, end);
        String node = predecessor.get(end);
        while(node != null) {
            path.add(0, node);
            node = predecessor.get(node);
        }
        return path.size()-1;
    }
}

