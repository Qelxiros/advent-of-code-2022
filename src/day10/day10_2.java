package day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class day10_2 {
    public static void main(String[] args) throws IOException {
        String input = Files.readString(Paths.get("src/input/input10.txt"));
        String[] lines = input.split("\r?\n");

        int reg = 1;
        int lineNum = 0;
        boolean yes = false;
        for (int i = 0; i < 240; i++) {
            if (lines[lineNum].startsWith("noop")) {
                if (Math.abs(reg-(i%40)) > 1) {
                    System.out.print(" ");
                } else {
                    System.out.print("#");
                }
                lineNum++;
            }
            else {
                if (Math.abs(reg-(i%40)) > 1) {
                    System.out.print(" ");
                } else {
                    System.out.print("#");
                }
                if (yes) {
                    int num = Integer.parseInt(lines[lineNum].split(" ")[1]);
                    reg += num;
                    yes = false;
                    lineNum++;
                } else {
                    yes = true;
                }
            }
            if (i % 40 == 39) {
                System.out.println();
            }
        }
    }
}

