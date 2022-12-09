package io.github.schmolo.timestamps;

public class StringSplit {

    public String[] StringSplit(String name, int segments) {
        int chunkSize = name.length() / segments;

        String[] chunks = name.split("(?<=\\G.{" + chunkSize + "})");
        return chunks;
    }
}
