package io.github.schmolo.timestamps;

import net.md_5.bungee.api.ChatColor;

public class SingleName extends ColoredName {

    public String color;

    public SingleName(String name, String color) {
        super(name);
        this.color = color;
        this.name = ChatColor.of(color) + this.name;
    }
}
