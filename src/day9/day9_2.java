package day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;

public class day9_2 {
    private static final HashSet<ArrayList<Integer>> visited = new HashSet<>();

    public static void main(String[] args) throws IOException {
        String input = Files.readString(Paths.get("src/day9/input.txt"));
        String[] lines = input.split("\r\n");

        ArrayList<ArrayList<Integer>> knots = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            knots.add(new ArrayList<>());
        }
        for (ArrayList<Integer> a : knots) {
            a.add(0);
            a.add(0);
        }

        for (String line : lines) {
            int length;
            boolean temp;
            switch (line.charAt(0)) {
                case 'U':
                    length = Integer.parseInt(line.split(" ")[1]);
                    for (int i = 0; i < length; i++) {
                        knots.get(0).set(0, knots.get(0).get(0)-1);
                        for (int j = 0; j < 9; j++) {
                            temp = j == 8;
                            adjustTail(knots.get(j), knots.get(j+1), temp);
                        }
                    }
                    break;
                case 'D':
                    length = Integer.parseInt(line.split(" ")[1]);
                    for (int i = 0; i < length; i++) {
                        knots.get(0).set(0, knots.get(0).get(0)+1);
                        for (int j = 0; j < 9; j++) {
                            temp = j == 8;
                            adjustTail(knots.get(j), knots.get(j+1), temp);
                        }
                    }
                    break;
                case 'L':
                    length = Integer.parseInt(line.split(" ")[1]);
                    for (int i = 0; i < length; i++) {
                        knots.get(0).set(1, knots.get(0).get(1)-1);
                        for (int j = 0; j < 9; j++) {
                            temp = j == 8;
                            adjustTail(knots.get(j), knots.get(j+1), temp);
                        }
                    }
                    break;
                case 'R':
                    length = Integer.parseInt(line.split(" ")[1]);
                    for (int i = 0; i < length; i++) {
                        knots.get(0).set(1, knots.get(0).get(1)+1);
                        for (int j = 0; j < 9; j++) {
                            temp = j == 8;
                            adjustTail(knots.get(j), knots.get(j+1), temp);
                        }
                    }
                    break;
            }
        }
        System.out.println(visited.size());
    }

    private static void adjustTail(ArrayList<Integer> head, ArrayList<Integer> tail, boolean last) {
        int vertDiff = Math.abs(head.get(0) - tail.get(0));
        int horiDiff = Math.abs(head.get(1) - tail.get(1));
        if (vertDiff >= 2) {
            if (tail.get(0) < head.get(0)) {
                tail.set(0, head.get(0)-1);
            } else {
                tail.set(0, head.get(0)+1);
            }
            if (horiDiff == 1) {
                tail.set(1, head.get(1));
            }
        }
        if (horiDiff >= 2) {
            if (tail.get(1) < head.get(1)) {
                tail.set(1, head.get(1)-1);
            } else {
                tail.set(1, head.get(1)+1);
            }
            if (vertDiff == 1) {
                tail.set(0, head.get(0));
            }
        }
        ArrayList<Integer> temp = new ArrayList<>(tail);
        if (last) {
            visited.add(temp);
        }
    }
}

