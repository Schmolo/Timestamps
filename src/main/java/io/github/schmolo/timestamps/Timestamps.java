package io.github.schmolo.timestamps;

import io.github.schmolo.timestamps.events.betterwhisper;
import io.github.schmolo.timestamps.events.playerchatevent;
import io.github.schmolo.timestamps.events.serversayevent;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Timestamps extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getServer().getPluginManager().registerEvents(new playerchatevent(), this);

        getCommand("say").setExecutor(new serversayevent());

        getCommand("msg").setExecutor(new betterwhisper());

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[TimeStamps]: enabled");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[TimeStamps]: disabled");
    }
}

/*
    TODO: Implement Config file system
    TODO: Add way to edit colors for Player names ( not only chat )
*/
