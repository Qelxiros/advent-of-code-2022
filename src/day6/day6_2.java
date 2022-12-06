package day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;

public class day6_2 {
    public static void main(String[] args) throws IOException {
        final Path path = Paths.get("src/day6/input.txt");
        String[] lines = Files.readAllLines(path).toArray(new String[0]);
        String input = lines[0];

        for (int i = 0; i < input.length() - 3; i++) {
            HashSet<Character> set = new HashSet<>();
            set.add(input.charAt(i));
            set.add(input.charAt(i+1));
            set.add(input.charAt(i+2));
            set.add(input.charAt(i+3));
            set.add(input.charAt(i+4));
            set.add(input.charAt(i+5));
            set.add(input.charAt(i+6));
            set.add(input.charAt(i+7));
            set.add(input.charAt(i+8));
            set.add(input.charAt(i+9));
            set.add(input.charAt(i+10));
            set.add(input.charAt(i+11));
            set.add(input.charAt(i+12));
            set.add(input.charAt(i+13));
            if (set.size() == 14) {
                System.out.println(i+14);
                System.out.println(set);
                break;
            }
        }
    }
}
