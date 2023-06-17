package day16;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Valve {
    public String name;
    public int flowRate;
    public List<String> neighbors;

    public Valve(String line) {
        String[] words = line.split(" ");
        name = words[1];
        flowRate = Integer.parseInt(words[4].split("[=;]")[1]);
        ArrayList<String> nbrs = new ArrayList<>();
        for (int i = 9; i < words.length; i++) {
            nbrs.add(words[i].split(",")[0]);
        }
        neighbors = nbrs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Valve valve = (Valve) o;
        return name.equals(valve.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
