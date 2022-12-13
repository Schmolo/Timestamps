package io.github.schmolo.timestamps.util.playernamehelper;

import net.md_5.bungee.api.ChatColor;

import java.util.List;

import static io.github.schmolo.timestamps.util.TimsLib.PrintDebug;

public class SegmentedName extends ColoredName {

    public List<Segment> segments;
    String intermediatecolor;

    public SegmentedName(String name, List<Segment> segments) {
        super(name);
        String sum = "";
        for (Segment s : segments) {

            if (customHexCodes.containsKey(s.color)) {
                PrintDebug("Custom Hex Codes contains the color and it's: " + s.color);
                intermediatecolor = customHexCodes.get(s.color);
                PrintDebug("Now the SingleName.color field is: " + s.color);
            } else if(s.color.contains("#")) {
                intermediatecolor = s.color;
            }

            sum += ChatColor.of(intermediatecolor) + s.name;
        }

        this.name = sum;
        this.segments = segments;
    }

    public SegmentedName(String name, List<Segment> segments, Affix prefix, Affix suffix) {
        super(name, prefix, suffix);
        String sum = "";
        for (Segment s : segments) {

            if (customHexCodes.containsKey(s.color)) {
                PrintDebug("Custom Hex Codes contains the color and it's: " + s.color);
                intermediatecolor = customHexCodes.get(s.color);
                PrintDebug("Now the SingleName.color field is: " + s.color);
            } else if(s.color.contains("#")) {
                intermediatecolor = s.color;
            }

            sum += ChatColor.of(intermediatecolor) + s.name;
        }

        this.name = sum;
        this.segments = segments;
    }

    @Override
    public String toString() {
        return "SegmentedName{" +
                "name='" + name + '\'' +
                ", prefix=" + prefix +
                ", suffix=" + suffix +
                ", segments=" + segments +
                '}';
    }
}
