package day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class day3_2 {
    public static void main(String[] args) throws IOException {
        int total = 0;
        String[] lines = Files.readAllLines(Paths.get("src/day3/input.txt")).toArray(new String[0]);
        for (int i = 0; i < lines.length / 3; i++) {
            String bag1 = lines[i*3];
            String bag2 = lines[i*3+1];
            String bag3 = lines[i*3+2];

            for (char c : bag1.toCharArray()) {
                if (bag2.contains(c+"") && bag3.contains(c+"")) {
                    total += checkVal(c);
                    break;
                }
            }
        }
        System.out.println(total);
    }

    private static int checkVal(char c) {
        if (c > 'Z') {
            return c - 'a' + 1;
        }
        return c -'A' + 27;
    }
}
