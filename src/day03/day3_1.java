package day03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class day3_1 {
    public static void main(String[] args) throws IOException {
        String[] lines = Files.readAllLines(Paths.get("src/input/input03.txt")).toArray(new String[0]);
//        String[] lines = new String[]{"jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL"};
        int total = 0;
        for (String line : lines) {
            String half1 = line.substring(0, line.length()/2);
            String half2 = line.substring(line.length()/2);

            for (char c : half1.toCharArray()) {
                if (half2.contains(c+"")) {
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
