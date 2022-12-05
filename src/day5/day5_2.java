package day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class day5_2 {
    public static void main(String[] args) throws IOException {
        String input = String.join("\n", Files.readAllLines(Paths.get("src/day5/input.txt")).toArray(new String[0]));

        String[] sections = input.split("\n\n");

        String[] board = sections[0].split("\n");

        int columns = (board[0].length()+1)/4 + 1;

        ArrayList<LinkedList<Character>> queues = new ArrayList<>();
        for (int i = 0; i < columns; i++) {
            queues.add(new LinkedList<>());
        }
        for (int i = board.length-2; i >= 0; i--) {
            for (int j = 1; j < board[i].length(); j+=4) {
                char c = board[i].charAt(j);
                if (c != 32) {
                    queues.get((j-1)/4).offer(c);
                }
            }
        }
        // 0-n is bottom up
        for (String command : sections[1].split("\n")) {
            String[] nums = command.split(" ");
            int quan = Integer.parseInt(nums[1]);
            int start = Integer.parseInt(nums[3]);
            int end = Integer.parseInt(nums[5]);

            char[] temp = new char[quan];
            for (int i = 0; i < quan; i++) {
                temp[i] = queues.get(start-1).removeLast();
            }
            for (int i = temp.length-1; i >= 0; i--) {
                queues.get(end-1).offer(temp[i]);
            }
        }

        String out = "";
        for (LinkedList<Character> l : queues) {
            out += l.getLast();
        }
        System.out.println(out);
    }
}
