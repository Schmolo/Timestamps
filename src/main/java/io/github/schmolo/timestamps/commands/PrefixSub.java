package io.github.schmolo.timestamps.commands;

import org.bukkit.entity.Player;

import java.util.List;

public class PrefixSub extends SubCommand{
    @Override
    public String getName() {
        return "prefix";
    }

    @Override
    public String getDescription() {
        return "set a Prefix";
    }

    @Override
    public String getUsage() {
        return "/ts prefix <prefix> <color>";
    }

    @Override
    public void perform(Player player, String[] args) {

    }

    @Override
    public List<String> getArgs(Player player, String[] args) {
        return null;
    }
}
