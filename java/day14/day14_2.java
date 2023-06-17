package day14;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class day14_2 {
    public static int lowestRock = -1;

    public static void main(String[] args) throws IOException {
        String input = Files.readString(Paths.get("src/input/input14.txt"));
//        String input = Files.readString(Paths.get("src/day14/test.txt"));
        String[] lines = input.split("\r?\n");

        HashMap<List<Integer>, Boolean> rocks = new HashMap<>();

        for (String line : lines) {
            String[] coords = line.split(" -> ");
            int prevX = -1;
            int prevY = -1;
            for (String coord : coords) {
                String[] nums = coord.split(",");
                int x = Integer.parseInt(nums[0]);
                int y = Integer.parseInt(nums[1]);

                if (prevX != -1 && prevY != -1) {
                    if (x == prevX && y > prevY) {
                        for (int i = prevY; i <= y; i++) {
                            rocks.put(List.of(x,i), true);
                            lowestRock = Math.max(lowestRock, i);
                        }
                    }
                    if (x == prevX && y < prevY) {
                        for (int i = y; i <= prevY; i++) {
                            rocks.put(List.of(x,i), true);
                            lowestRock = Math.max(lowestRock, i);
                        }
                    }
                    if (y == prevY && x > prevX) {
                        for (int i = prevX; i <= x; i++) {
                            rocks.put(List.of(i,y), true);
                            lowestRock = Math.max(lowestRock, y);
                        }
                    }
                    if (y == prevY && x < prevX) {
                        for (int i = x; i <= prevX; i++) {
                            rocks.put(List.of(i,y), true);
                            lowestRock = Math.max(lowestRock, y);
                        }
                    }
                }

                prevX = x;
                prevY = y;
            }
        }
        lowestRock+=2;

        int total = 0;
        while (dropSand(rocks, 500, 0)) {
            total++;
        }
        System.out.println(total);
    }

    public static boolean dropSand(HashMap<List<Integer>, Boolean> rocks, int x, int y) {
        if (y == lowestRock-1) {
            rocks.put(List.of(x,y), true);
            return true;
        }
        if (rocks.get(List.of(500,0)) != null) {
            return false;
        }
        if (rocks.get(List.of(x,y+1)) == null) {
            return dropSand(rocks,x,y+1);
        }
        if (rocks.get(List.of(x-1,y+1)) == null) {
            return dropSand(rocks, x-1, y+1);
        }
        if (rocks.get(List.of(x+1,y+1)) == null) {
            return dropSand(rocks, x+1, y+1);
        }
        rocks.put(List.of(x,y), true);
        return true;
    }
}

