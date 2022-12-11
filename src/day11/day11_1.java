package day11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class day11_1 {
    public static void main(String[] args) throws IOException {
        String input = Files.readString(Paths.get("src/day11/input.txt"));
//        String input = Files.readString(Paths.get("src/day11/test.txt"));
        String[] monkeyStrings = input.split("\r?\n\r?\n");

        ArrayList<Monkey> monkeys = new ArrayList<>();
        ArrayList<Integer> divs = new ArrayList<>();

        for (String m : monkeyStrings) {
            String[] lines = m.split("\r?\n");
            String items = lines[1].substring(18);
            Monkey monkey = new Monkey(items, lines[2].strip(), String.join("=", lines[3].strip(), lines[4].strip(), lines[5].strip()));
            monkeys.add(monkey);
            divs.add(Integer.parseInt(lines[3].strip().split(" ")[3]));
        }

        int total = 1;
        for (int i : divs) {
            total *= i;
        }

        for (int ignored = 0; ignored < 20; ignored++) {
            for (Monkey m : monkeys) {
                m.inspectItems(monkeys, total);
            }
        }

        Collections.sort(monkeys);

        System.out.println((long)monkeys.get(0).itemsInspected*(long)monkeys.get(1).itemsInspected);
    }
}

