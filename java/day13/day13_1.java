package day13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Stack;

public class day13_1 {
    public static void main(String[] args) throws IOException {
        String input = Files.readString(Paths.get("src/input/input13.txt"));
//        String input = Files.readString(Paths.get("src/day13/test.txt"));
        String[] pairs = input.split("\r?\n\r?\n");

        int total = 0;
        for (int i = 0; i < pairs.length; i++) {
            String pair = pairs[i];
            String left = pair.split("\r?\n")[0];
            String right = pair.split("\r?\n")[1];

            PacketNode leftNode = constructNode(left);
            PacketNode rightNode = constructNode(right);

            int result = comparePackets(leftNode, rightNode);
            if (result == 0) {
                total += i+1;
            }
        }
        System.out.println(total);
    }

    public static PacketNode constructNode(String input) {
        StringBuilder num = new StringBuilder();
        Stack<PacketNode> yes = new Stack<>();
        PacketNode workingNode = new PacketNode(new ArrayList<>());
        yes.push(workingNode);
        PacketNode output = workingNode;
        char prevChar = ' ';
        for (char c : input.toCharArray()) {
            switch (c) {
                case '[':
                    PacketNode temp = new PacketNode(new ArrayList<>());
                    workingNode.children.add(temp);
                    yes.push(temp);
                    workingNode = temp;
                    prevChar = c;
                    break;
                case ']':
                    if (prevChar != ']' && !String.valueOf(num).equals("")) {
                        workingNode.children.add(new PacketLeaf(Integer.parseInt(String.valueOf(num))));
                        num = new StringBuilder();
                    }
                    yes.pop();
                    workingNode = yes.peek();
                    prevChar = c;
                    break;
                case ',':
                    if (prevChar != ']' && !String.valueOf(num).equals("")) {
                        workingNode.children.add(new PacketLeaf(Integer.parseInt(String.valueOf(num))));
                        num = new StringBuilder();
                    }
                    prevChar = c;
                    break;
                default:
                    num.append(c);
                    prevChar = c;
                    break;
            }
        }
        return output;
    }

    public static int comparePackets(PacketNode left, PacketNode right) {
        if (left instanceof PacketLeaf && right instanceof PacketLeaf) {
            if (((PacketLeaf) left).value == ((PacketLeaf) right).value) {
                return 2;
            }
            return ((PacketLeaf) left).value < ((PacketLeaf) right).value ? 0 : 1;
        }
        else if (right instanceof PacketLeaf) {
            ArrayList<PacketNode> children = new ArrayList<>();
            children.add(right);
            PacketNode node = new PacketNode(children);
            int result = comparePackets(left, node);
            if (result == 0 || result == 1) {
                return result;
            }
        }
        else if (left instanceof PacketLeaf) {
            ArrayList<PacketNode> children = new ArrayList<>();
            children.add(left);
            PacketNode node = new PacketNode(children);
            int result = comparePackets(node, right);
            if (result == 0 || result == 1) {
                return result;
            }
        }
        else if (left.children.size() > 0 && right.children.size() > 0) {
            for (int i = 0; i < Math.min(left.children.size(), right.children.size()); i++) {
                int result = comparePackets(left.children.get(i), right.children.get(i));
                if (result == 0 || result == 1) {
                    return result;
                }
            }
            if (left.children.size() == right.children.size()) {
                return 2;
            }
            return left.children.size() < right.children.size() ? 0 : 1;
        }
        else {
            if (left.children.size() == right.children.size()) {
                return 2;
            }
            return left.children.size() < right.children.size() ? 0 : 1;
        }
        return -1;
    }
}

