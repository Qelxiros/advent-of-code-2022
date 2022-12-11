package day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Monkey implements Comparable<Monkey> {
    public int itemsInspected;
    public LinkedList<Long> itemsHeld;
    public int divisibility;
    public int trueMonkey;
    public int falseMonkey;
    public char op;
    public int arg;

    public Monkey(String items, String op, String test) {
        long[] temp = Arrays.stream(items.split(", ")).mapToLong(Long::parseLong).toArray();
        itemsHeld = new LinkedList<>();
        for (long i : temp) {
            itemsHeld.add(i);
        }
        this.op = op.split(" ")[4].charAt(0);
        try {
            this.arg = Integer.parseInt(op.split(" ")[5]);
        } catch (Exception e) {
            this.arg = -1;
        }
        String[] args = test.split("[ =]");
        divisibility = Integer.parseInt(args[3]);
        trueMonkey = Integer.parseInt(args[9]);
        falseMonkey = Integer.parseInt(args[15]);
    }

    public void inspectItems(ArrayList<Monkey> monkeys, int total) {
        while (!itemsHeld.isEmpty()) {
            long curVal = itemsHeld.removeFirst();
            switch (op) {
                case '+':
                    if (arg == -1) {
                        curVal += curVal;
                    } else {
                        curVal += arg;
                    }
                    break;
                case '*':
                    if (arg == -1) {
                        curVal *= curVal;
                    } else {
                        curVal *= arg;
                    }
                    break;
            }

            curVal = curVal % total;

            if (curVal % divisibility == 0) {
                monkeys.get(trueMonkey).itemsHeld.add(curVal);
            } else {
                monkeys.get(falseMonkey).itemsHeld.add(curVal);
            }

            itemsInspected++;
        }
    }

    public void inspectItems2(ArrayList<Monkey> monkeys, int total) {
        while (!itemsHeld.isEmpty()) {
            long curVal = itemsHeld.removeFirst();
            switch (op) {
                case '+':
                    if (arg == -1) {
                        curVal += curVal;
                    } else {
                        curVal += arg;
                    }
                    break;
                case '*':
                    if (arg == -1) {
                        curVal *= curVal;
                    } else {
                        curVal *= arg;
                    }
                    break;
            }

            curVal = curVal % total;

            if (curVal % divisibility == 0) {
                monkeys.get(trueMonkey).itemsHeld.add(curVal);
            } else {
                monkeys.get(falseMonkey).itemsHeld.add(curVal);
            }

            itemsInspected++;
        }
    }

    @Override
    public int compareTo(Monkey o) {
        return o.itemsInspected-this.itemsInspected;
    }
}
