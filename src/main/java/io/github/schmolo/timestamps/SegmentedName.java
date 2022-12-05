package io.github.schmolo.timestamps;

import net.md_5.bungee.api.ChatColor;

import java.util.List;

public class SegmentedName extends ColoredName {

    public SegmentedName(String name, List<Segment> segments) {
        super(name);
        StringBuilder sum = new StringBuilder();
        for (Segment s : segments) {
            sum.append(ChatColor.of(s.color)).append(s.name);
        }

        this.name = sum.toString();
    }
}
