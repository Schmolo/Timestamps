package io.github.schmolo.timestamps;

import net.md_5.bungee.api.ChatColor;

public class SingleName extends ColoredName {

    public SingleName(String name, String color) {
        super(name);
        this.name = ChatColor.of(color) + this.name;
    }
}
