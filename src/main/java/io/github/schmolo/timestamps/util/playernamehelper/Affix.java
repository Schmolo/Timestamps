package io.github.schmolo.timestamps.util.playernamehelper;


import net.md_5.bungee.api.ChatColor;

import static io.github.schmolo.timestamps.util.playernamehelper.ColoredName.customHexCodes;

public class Affix {
    public String name;
    public String color;

    public Affix(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public Affix() {

    }

    public String getName() {
        String intermediatecolor = null;

        if (customHexCodes.containsKey(color)) {
            intermediatecolor = customHexCodes.get(color);
        } else if(color.contains("#")) {
            intermediatecolor = color;
        }
        return ChatColor.of(intermediatecolor) + "[" + this.name + "]";
    }

    @Override
    public String toString() {
        return "Affix{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
