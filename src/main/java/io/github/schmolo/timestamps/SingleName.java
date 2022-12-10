package io.github.schmolo.timestamps;

import net.md_5.bungee.api.ChatColor;

import static io.github.schmolo.timestamps.TimsLib.PrintDebug;

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
}
