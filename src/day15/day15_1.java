package day15;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;

public class day15_1 {
    public static void main(String[] args) throws IOException {
        String input = Files.readString(Paths.get("src/input/input15.txt"));
//        String input = Files.readString(Paths.get("src/day15/test.txt"));
        String[] lines = input.split("\r?\n");

        int row = 2000000;

        HashSet<Integer> xVals = new HashSet<>();
        HashSet<Integer> beaconXVals = new HashSet<>();

        for (String line : lines) {
            int sx = Integer.parseInt(line.split("[=,:]")[1]);
            int sy = Integer.parseInt(line.split("[=,:]")[3]);
            int bx = Integer.parseInt(line.split("[=,:]")[5]);
            int by = Integer.parseInt(line.split("[=,:]")[7]);

            if (by == row) {
                beaconXVals.add(bx);
            }

            int dist = Math.abs(sx-bx) + Math.abs(sy-by);
            for (int i = 0; i <= dist; i++) {
                if (row >= sy-dist+i && row <= sy+dist-i) {
                    xVals.add(sx+i);
                    xVals.add(sx-i);
                }
            }
        }

        for (int i : beaconXVals) {
            xVals.remove(i);
        }

        System.out.println(xVals.size());
    }
}

