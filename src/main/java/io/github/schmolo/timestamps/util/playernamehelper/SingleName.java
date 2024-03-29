package io.github.schmolo.timestamps.util.playernamehelper;

import net.md_5.bungee.api.ChatColor;

public class SingleName extends ColoredName {

    public String color;
    String intermediatecolor;

    public SingleName(String name, String color) {
        super(name);
        this.color = color;
        if (customHexCodes.containsKey(color)) {
            intermediatecolor = customHexCodes.get(color);
        } else if(color.contains("#")) {
            intermediatecolor = color;
        }
        this.name = ChatColor.of(intermediatecolor) + this.name;
    }

    public SingleName(String name, String color, Affix prefix, Affix suffix) {
        super(name, prefix, suffix);
        this.color = color;
        if (customHexCodes.containsKey(color)) {
            intermediatecolor = customHexCodes.get(color);
        } else if(color.contains("#")) {
            intermediatecolor = color;
        }
        this.name = ChatColor.of(intermediatecolor) + this.name;
    }

    @Override
    public String toString() {
        return "SingleName{" +
                "name='" + name + '\'' +
                ", prefix=" + prefix +
                ", suffix=" + suffix +
                ", color='" + color + '\'' +
                '}';
    }
}
