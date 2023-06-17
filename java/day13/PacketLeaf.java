package day13;

import java.util.Objects;

public class PacketLeaf extends PacketNode {
    public int value;

    public PacketLeaf(int value) {
        super(null);
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PacketLeaf that = (PacketLeaf) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), value);
    }
}
