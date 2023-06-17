package day18;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class day18_2 {
    public static HashSet<List<Integer>> cells = new HashSet<>();
    public static HashSet<List<Integer>> trapped = new HashSet<>();
    public static int maxX = 0;
    public static int maxY = 0;
    public static int maxZ = 0;

    public static void main(String[] args) throws IOException {
        String input = Files.readString(Paths.get("src/input/input18.txt"));
//        String input = Files.readString(Paths.get("src/day18/test.txt"));
        String[] lines = input.split("\r?\n");

        for (String line : lines) {
            int[] coords = (Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray());
            cells.add(List.of(coords[0], coords[1], coords[2]));
            maxX = Math.max(maxX, coords[0]);
            maxY = Math.max(maxY, coords[1]);
            maxZ = Math.max(maxZ, coords[2]);
        }

        int total = 0;

        for (List<Integer> cell : cells) {
            int area = 6;
            if (cells.contains(List.of(cell.get(0) -1, cell.get(1), cell.get(2)))) {
                area--;
            }
            if (cells.contains(List.of(cell.get(0) +1, cell.get(1), cell.get(2)))) {
                area--;
            }
            if (cells.contains(List.of(cell.get(0), cell.get(1) -1, cell.get(2)))) {
                area--;
            }
            if (cells.contains(List.of(cell.get(0), cell.get(1) +1, cell.get(2)))) {
                area--;
            }
            if (cells.contains(List.of(cell.get(0), cell.get(1), cell.get(2) -1))) {
                area--;
            }
            if (cells.contains(List.of(cell.get(0), cell.get(1), cell.get(2) +1))) {
                area--;
            }
            total += area;
        }

        for (int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                for (int z = 0; z < maxZ; z++) {
                    List<Integer> cell = List.of(x,y,z);
                    checkCell(cell, new HashSet<>());
                }
            }
        }

        for (List<Integer> cell : trapped) {
            for (List<Integer> c : List.of(List.of(cell.get(0) -1, cell.get(1), cell.get(2)),List.of(cell.get(0) +1, cell.get(1), cell.get(2)),List.of(cell.get(0), cell.get(1) -1, cell.get(2)),List.of(cell.get(0), cell.get(1) +1, cell.get(2)),List.of(cell.get(0), cell.get(1), cell.get(2) -1),List.of(cell.get(0), cell.get(1), cell.get(2) +1))) {
                if (cells.contains(c)) {
                    total--;
                }
            }
        }

        System.out.println(total);
    }

    public static void checkCell(List<Integer> start, HashSet<List<Integer>> visited) {
        if (cells.contains(start)) {
            return;
        }
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            List<Integer> cell = queue.poll();
            visited.add(cell);
            for (List<Integer> c : List.of(List.of(cell.get(0) - 1, cell.get(1), cell.get(2)), List.of(cell.get(0) + 1, cell.get(1), cell.get(2)), List.of(cell.get(0), cell.get(1) - 1, cell.get(2)), List.of(cell.get(0), cell.get(1) + 1, cell.get(2)), List.of(cell.get(0), cell.get(1), cell.get(2) - 1), List.of(cell.get(0), cell.get(1), cell.get(2) + 1))) {
                if (!cells.contains(c) && !visited.contains(c) && !queue.contains(c)) {
                    if (c.get(0) < 0 || c.get(0) > maxX || c.get(1) < 0 || c.get(1) > maxY || c.get(2) < 0 || c.get(2) > maxZ) {
                        return;
                    }
                    queue.add(c);
                }
            }
        }
        trapped.add(start);
    }
}