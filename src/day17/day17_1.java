// i forgot to switch files and ctrl z has a limit, so the data types accommodate part 2 bc im too lazy to fix them

package day17;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class day17_1 {
    public static HashSet<List<Long>> board = new HashSet<>();
    public static int index = 0;
    public static long highestRock = 0;
    public static String input;

    public static void main(String[] args) throws IOException {
        input = Files.readString(Paths.get("src/input/input17.txt"));
//        input = Files.readString(Paths.get("src/day17/test.txt"));
        String[] lines = input.split("\r?\n");

        for (int i = 0; i < 2022; i++) {
            dropRock(i);
//            string();
//            System.out.println(highestRock);
        }
        System.out.println(highestRock);
    }

    public static void dropRock(int i) {
        List<List<Integer>> rock = switch (i%5) {
            case 0 -> List.of(List.of(0, 0), List.of(1, 0), List.of(2, 0), List.of(3, 0));
            case 1 -> List.of(List.of(0, 1), List.of(1, 0), List.of(1, 1), List.of(1, 2), List.of(2, 1));
            case 2 -> List.of(List.of(0, 0), List.of(1, 0), List.of(2, 0), List.of(2, 1), List.of(2, 2));
            case 3 -> List.of(List.of(0, 0), List.of(0, 1), List.of(0, 2), List.of(0, 3));
            default -> List.of(List.of(0, 0), List.of(1, 0), List.of(0, 1), List.of(1, 1));
        };
        boolean downNext = false;
        List<Long> ll = List.of(2L, highestRock+4);
        while (true) {
            List<Long> prevLL = new ArrayList<>(ll);
            if (downNext) {
                ll = List.of(ll.get(0), ll.get(1)-1);
                downNext = false;

                if (!checkRock(ll, rock)) {
                    for (List<Integer> cell : rock) {
                        board.add(List.of(prevLL.get(0)+cell.get(0), prevLL.get(1)+cell.get(1)));
                        highestRock = Math.max(highestRock, prevLL.get(1)+cell.get(1));
                    }
                    return;
                }
            } else {
                char nextDir = input.charAt(index);
                index++;
                if (index >= input.length()) {
                    index = 0;
                }
                if (nextDir == '>') {
                    ll = List.of(ll.get(0)+1, ll.get(1));
                } else {
                    ll = List.of(ll.get(0)-1, ll.get(1));
                }
                downNext = true;

                if (!checkRock(ll, rock)) {
                    ll = prevLL;
                }
            }

        }
    }

    public static boolean checkRock(List<Long> lowerLeft, List<List<Integer>> rock) {
        long x = lowerLeft.get(0);
        long y = lowerLeft.get(1);

        for (List<Integer> cell : rock) {
            if (board.contains(List.of(x+cell.get(0), y+cell.get(1))) || x+cell.get(0) > 6 || x+cell.get(0) < 0 || y+cell.get(1) < 1) {
                return false;
            }
        }
        return true;
    }

    public static void string() {
        for (long i = highestRock; i >= 1; i--) {
            for (int j = 0; j < 7; j++) {
                if (board.contains(List.of(j, i))) {
                    System.out.print('#');
                } else {
                    System.out.print('.');
                }
            }
            System.out.println();
        }
        System.out.println("-------");
    }
}

