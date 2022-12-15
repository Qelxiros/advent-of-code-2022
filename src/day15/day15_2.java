package day15;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class day15_2 {
    public static void main(String[] args) throws IOException {
        String input = Files.readString(Paths.get("src/input/input15.txt"));
//        String input = Files.readString(Paths.get("src/day15/test.txt"));
        int max = 4000000;
//        int max = 20;
        String[] lines = input.split("\r?\n");

        List<Integer> intercepts = new ArrayList<>();

        for (String line : lines) {
            int sx = Integer.parseInt(line.split("[=,:]")[1]);
            int sy = Integer.parseInt(line.split("[=,:]")[3]);
            int bx = Integer.parseInt(line.split("[=,:]")[5]);
            int by = Integer.parseInt(line.split("[=,:]")[7]);
//
            int dist = Math.abs(sx - bx) + Math.abs(sy - by);

            int p1 = sx - dist;
            int p2 = sy + dist;
            int p3 = sx + dist;
            int p4 = sy - dist;

            intercepts.addAll(List.of(p2-sx,sy-(p3-2*sx),sy-(p1-2*sx),p4-sx));
        }
        for (int i : intercepts) {
            for (int j : intercepts) {
                if (Math.abs(i-j) == 2) {
                    System.out.println(i);
                    System.out.println(j);
                }
            }
        }
        // I used desmos to find the resulting coordinates
    }
}

