package day02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        String lines = new String(Files.readAllBytes(Paths.get("src/input/input02.txt")));
//        String lines = "A Y\n" + "B X\n" + "A Z";
        String[] games = lines.split("\n");
        int total = 0;
        for (String game : games) {
            int score = 0;
            char oppMove = game.charAt(0);
            char yourMove = game.charAt(2);
            int diff = (int) yourMove - (int) oppMove;
//            switch (diff) {
//                case 23:
//                    score += 3;
//                    break;
//                case 24:
//                case 21:
//                    score += 6;
//                    break;
//                case 22:
//                case 25:
//                    // loss
//                    break;
//            }
            switch (yourMove) {
                case 'X':
                    // lose
                    score = (((oppMove - 'A') % 3 + 1) + 1) % 3 + 1;
                    break;
                case 'Y':
                    // draw
                    score = oppMove - 'A' + 1 + 3;
                    break;
                case 'Z':
                    // win
                    score = ((((oppMove - 'A')) + 1) % 3) % 3 + 1 + 6;
                    break;
            }
            System.out.print(score);
            System.out.print(oppMove);
            System.out.println(yourMove);

            total += score;
        }
        System.out.println(total);
    }
}
