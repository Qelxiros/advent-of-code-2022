package day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;

public class day6_1 {
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
            if (set.size() == 4) {
                System.out.println(i+4);
                System.out.println(set);
                break;
            }
        }
    }
}
