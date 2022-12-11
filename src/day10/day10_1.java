package day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class day10_1 {
    public static void main(String[] args) throws IOException {
        String input = Files.readString(Paths.get("src/day10/input.txt"));
        String[] lines = input.split("\r?\n");

        // 20th, 60th, 100th, 140th, 180th, and 220th cycles
        int reg = 1;
        int lineNum = 0;
        int total = 0;
        boolean yes = false;
        for (int i = 1; i <= 220; i++) {
            if (lines[lineNum].startsWith("noop")) {
                if (i == 20 || i == 60 || i == 100 || i == 140 || i == 180 || i == 220) {
                    total += i*reg;
                }
                lineNum++;

            }
            else {
                if (yes) {
                    int num = Integer.parseInt(lines[lineNum].split(" ")[1]);
                    if (i == 20 || i == 60 || i == 100 || i == 140 || i == 180 || i == 220) {
                        total += i*reg;
                    }
                    reg += num;

                    yes = false;
                    lineNum++;
                } else {
                    if (i == 20 || i == 60 || i == 100 || i == 140 || i == 180 || i == 220) {
                        total += i*reg;
                    }
                    yes = true;
                }
            }
        }
        System.out.println(total);
    }
}

