package day08;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class day8_2 {
    public static void main(String[] args) throws IOException {
        String input = Files.readString(Paths.get("src/input/input08.txt"));

        String[] lines = input.split("\r\n");

        boolean[][] visibility = new boolean[lines.length][lines[0].length()];

        int[][] linesInts = new int[lines.length][lines[0].length()];
        for (int j = 0; j < lines.length; j++) {
            String line = lines[j];
            int[] lineInts = new int[line.length()];
            for (int i = 0; i < line.length(); i++) {
                lineInts[i] = Integer.parseInt(String.valueOf(line.charAt(i)));
            }
            linesInts[j] = lineInts;
        }
//        for (int i = 0; i < linesInts.length; i++) {
//            int[] line = linesInts[i];
//            for (int j = 0; j < line.length; j++) {
//                if (i == 0 || j == 0 || i == linesInts.length-1 || j == line.length-1) {
//                    visibility[i][j] = true;
//                }
//                boolean b1 = true;
//                for (int k = 0; k < j; k++) {
//                    if (linesInts[i][k] >= linesInts[i][j]) {
//                        b1 = false;
//                        break;
//                    }
//                }
//                boolean b2 = true;
//                for (int k = line.length-1; k > j; k--) {
//                    if (linesInts[i][k] >= linesInts[i][j]) {
//                        b2 = false;
//                        break;
//                    }
//                }
//                boolean b3 = true;
//                for (int k = 0; k < i; k++) {
//                    if (linesInts[k][j] >= linesInts[i][j]) {
//                        b3 = false;
//                        break;
//                    }
//                }
//                boolean b4 = true;
//                for (int k = linesInts.length-1; k > i; k--) {
//                    if (linesInts[k][j] >= linesInts[i][j]) {
//                        b4 = false;
//                        break;
//                    }
//                }
//                visibility[i][j] = b1 || b2 || b3 || b4;
//            }
//        }
//        int total = 0;
//        for (boolean[] a : visibility) {
//            for (boolean b : a) {
//                if (b) {
//                    total++;
//                }
//            }
//        }
        System.out.println(scenicScore(4,4,linesInts));
        int biggest = 0;
        for (int i = 0; i < linesInts.length; i++) {
            for (int j = 0; j < linesInts[i].length; j++) {
                biggest = Math.max(biggest, scenicScore(i,j,linesInts));
            }
        }
        System.out.println(biggest);
    }

    private static int scenicScore(int row, int col, int[][] board) {
        int height = board[row][col];
        int dir = 0;
        int total = 1;
        for (int i = row - 1; i >= 0; i--) {
            if (board[i][col] < height) {
                dir++;
            } else {
                dir++;
                break;
            }
        }
        total *= dir;
        dir = 0;
        for (int i = row + 1; i < board.length; i++) {
            if (board[i][col] < height) {
                dir++;
            } else {
                dir++;
                break;
            }
        }
        total *= dir;
        dir = 0;
        for (int i = col - 1; i >= 0; i--) {
            if (board[row][i] < height) {
                dir++;
            } else {
                dir++;
                break;
            }
        }
        total *= dir;
        dir = 0;
        for (int i = col + 1; i < board[row].length; i++) {
            if (board[row][i] < height) {
                dir++;
            } else {
                dir++;
                break;
            }
        }
        total *= dir;
        return total;
    }
}

