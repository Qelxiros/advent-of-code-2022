package day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        String lines = new String(Files.readAllBytes(Paths.get("src/input/input01.txt")));
        String[] elves = lines.split(String.format("%n%n"));
        ArrayList<Integer> elfTotals = new ArrayList<>();
        for (String elf : elves) {
            String[] food = elf.split(String.format("%n"));
            int total = 0;
            for (String f : food) {
                total += Integer.parseInt(f);
            }
            elfTotals.add(total);
        }
        Collections.sort(elfTotals);
        Collections.reverse(elfTotals);
        System.out.println(elfTotals.get(0)+elfTotals.get(1)+elfTotals.get(2));
    }
}