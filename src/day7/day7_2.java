package day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class day7_2 {
    public static void main(String[] args) throws IOException {
        String input = Files.readString(Paths.get("src/day7/input.txt"));
        String[] lines = input.split("\r\n");

        Node2 curr_dir = new Directory2(new ArrayList<>(), "/", null);
        Node2 top_dir = curr_dir;

        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] words = line.split(" ");
            if (words[0].equals("$")) {
                switch (words[1]) {
                    case "cd":
                        if (words[2].equals("..")) {
                            curr_dir = curr_dir.parent;
                            break;
                        }
                        if (words[2].equals("/")) {
                            curr_dir = top_dir;
                            break;
                        }
                        for (Node2 c : curr_dir.children) {
                            if (c.name.equals(words[2])) {
                                curr_dir = c;
                                break;
                            }
                        }
                    case "ls":
                        for (int j = i+1; j < lines.length; j++) {
                            if (lines[j].startsWith("$")) {
                                break;
                            }
                            if (lines[j].startsWith("dir")) {
                                curr_dir.addChild(new Directory2(new ArrayList<>(), lines[j].split(" ")[1], curr_dir));
                            }  else {
                                curr_dir.addChild(new NodeFile2(Integer.parseInt(lines[j].split(" ")[0]), new ArrayList<>(), lines[j].split(" ")[1], curr_dir));
                            }
                        }
                }
            }
        }
//        int total = idfk(top_dir);
//        System.out.println(total);
        ArrayList<Node2> dirs = new ArrayList<>();
        recursion_pt2(top_dir, dirs);
        int unusedSpace = 70000000 - top_dir.getSize();
        int spaceToBeFreed = 30000000 - unusedSpace;
        System.out.println(spaceToBeFreed);
        Collections.sort(dirs);
        System.out.println(top_dir.getSize());
        for (Node2 n : dirs) {
            if (n.getSize() >= spaceToBeFreed) {
                System.out.println(n.getSize());
                break;
            }
        }
//        System.out.println(total);
    }

    public static int idfk(Node2 n) {
        int total = 0;
        if (n.getSize() <= 100000 && n instanceof Directory2) {
            total += n.getSize();
        }
        for (Node2 child : n.children) {
            if (child.getSize() <= 100000 && child instanceof Directory2) {
                total += idfk(child);
            }
        }
        return total;
    }

    public static void recursion_pt2(Node2 n, ArrayList<Node2> list) {
        if (n instanceof Directory2) {
            list.add(n);
        }
        for (Node2 c : n.children) {
            recursion_pt2(c, list);
        }
    }
}

abstract class Node2 implements Comparable<Node2> {
    String name;
    ArrayList<Node2> children;
    Node2 parent;

    public Node2(ArrayList<Node2> children, String name, Node2 parent) {
        this.children = children;
        this.name = name;
        this.parent = parent;
    }

    public void addChild(Node2 c) {
        this.children.add(c);
    }

    public int getSize() {
        if (this instanceof NodeFile2) {
            return ((NodeFile2) this).size;
        } else {
            int total = 0;
            for (Node2 c : this.children) {
                total += c.getSize();
            }
            return total;
        }
    }

    @Override
    public String toString() {
        return String.valueOf(this.getSize());
    }

    @Override
    public int compareTo(Node2 o) {
        return this.getSize() - o.getSize();
    }
}

class Directory2 extends Node2 {
    public Directory2(ArrayList<Node2> children, String name, Node2 parent) {
        super(children, name, parent);
    }
}

class NodeFile2 extends Node2 {
    int size;

    public NodeFile2(int size, ArrayList<Node2> children, String name, Node2 parent) {
        super(children, name, parent);
        this.size = size;
    }
}

