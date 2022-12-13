package day13;

import java.util.ArrayList;
import java.util.Objects;

public class PacketNode implements Comparable<PacketNode> {
    public ArrayList<PacketNode> children;

    public PacketNode(ArrayList<PacketNode> children) {
        this.children = children;
    }

    @Override
    public int compareTo(PacketNode o) {
        return 2*day13_2.comparePackets(this, o)-1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PacketNode that = (PacketNode) o;
        return Objects.equals(children, that.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(children);
    }
}