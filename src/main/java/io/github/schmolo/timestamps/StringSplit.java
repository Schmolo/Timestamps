package io.github.schmolo.timestamps;

import java.util.ArrayList;
import java.util.List;

public class StringSplit {

    public static List<String> StringSplit(String name, int segments) {
        List<String> parts = new ArrayList<>();
        int len = name.length();
        int partLen = len / segments;
        int remaining = len % segments;
        int start = 0;

        for (int i = 0; i < segments; i++) {
            int end = start + partLen;
            if (remaining > 0) {
                end++;
                remaining--;
            }
            parts.add(name.substring(start, end));
            start = end;
        }

        return parts;
    }
}
