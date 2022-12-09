package day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;

public class day9_1 {
    private static final HashSet<ArrayList<Integer>> visited = new HashSet<>();

    public static void main(String[] args) throws IOException {
        String input = Files.readString(Paths.get("src/day9/input.txt"));
        String[] lines = input.split("\r\n");

        ArrayList<Integer> head = new ArrayList<>();
        ArrayList<Integer> tail = new ArrayList<>();

        head.add(0);
        head.add(0);
        tail.add(0);
        tail.add(0);

        for (String line : lines) {
            int length;
            switch (line.charAt(0)) {
                case 'U':
                    length = Integer.parseInt(line.split(" ")[1]);
                    for (int i = 0; i < length; i++) {
                        head.set(0, head.get(0)-1);
                        adjustTail(head, tail);
                    }
                    break;
                case 'D':
                    length = Integer.parseInt(line.split(" ")[1]);
                    for (int i = 0; i < length; i++) {
                        head.set(0, head.get(0)+1);
                        adjustTail(head, tail);
                    }
                    break;
                case 'L':
                    length = Integer.parseInt(line.split(" ")[1]);
                    for (int i = 0; i < length; i++) {
                        head.set(1, head.get(1)-1);
                        adjustTail(head, tail);
                    }
                    break;
                case 'R':
                    length = Integer.parseInt(line.split(" ")[1]);
                    for (int i = 0; i < length; i++) {
                        head.set(1, head.get(1)+1);
                        adjustTail(head, tail);
                    }
                    break;
            }
        }
        System.out.println(visited.size());
    }

    private static void adjustTail(ArrayList<Integer> head, ArrayList<Integer> tail) {
        int vertDiff = Math.abs(head.get(0) - tail.get(0));
        int horiDiff = Math.abs(head.get(1) - tail.get(1));
        if (vertDiff == 2) {
            if (tail.get(0) < head.get(0)) {
                tail.set(0, tail.get(0)+1);
            } else {
                tail.set(0, tail.get(0)-1);
            }
            if (horiDiff != 0) {
                tail.set(1, head.get(1));
            }
        }
        if (horiDiff == 2) {
            if (tail.get(1) < head.get(1)) {
                tail.set(1, tail.get(1)+1);
            } else {
                tail.set(1, tail.get(1)-1);
            }
            if (vertDiff != 0) {
                tail.set(0, head.get(0));
            }
        }
        ArrayList<Integer> temp = new ArrayList<>(tail);
        visited.add(temp);
    }
}

