package day04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class day4_2 {
    public static void main(String[] args) throws IOException {
        String[] lines = Files.readAllLines(Paths.get("src/input/input04.txt")).toArray(new String[0]);
//        String[] lines = new String[]{"2-4,6-8"};
        int total = 0;
        for (String pair : lines) {
            String[] ranges = pair.split(",");
            String[] range1 = ranges[0].split("-");
            String[] range2 = ranges[1].split("-");
            if (!(Integer.parseInt(range1[1]) < Integer.parseInt(range2[0])) && !(Integer.parseInt(range1[0]) > Integer.parseInt(range2[1]))) {
                total++;
            }
        }
        System.out.println(total);
    }
}
