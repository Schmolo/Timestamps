package io.github.schmolo.timestamps;

import net.md_5.bungee.api.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class SegmentedName extends ColoredName {

    public List<Segment> segments;

    public SegmentedName(String name, List<Segment> segments) {
        super(name);
        String sum = "";
        for (Segment s : segments) {
            sum += ChatColor.of(s.color) + s.name;
        }

        this.name = sum;
        this.segments = segments;
    }
}
