package day18;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class day18_1 {
    public static void main(String[] args) throws IOException {
        String input = Files.readString(Paths.get("src/input/input18.txt"));
//        String input = Files.readString(Paths.get("src/day18/test.txt"));
        String[] lines = input.split("\r?\n");

        HashSet<List<Integer>> cells = new HashSet<>();

        for (String line : lines) {
            int[] coords = (Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray());
            cells.add(List.of(coords[0], coords[1], coords[2]));
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
        System.out.println(total);
    }
}

