package io.github.schmolo.timestamps;

import io.github.schmolo.timestamps.events.betterwhisper;
import io.github.schmolo.timestamps.events.playerchatevent;
import io.github.schmolo.timestamps.events.serversayevent;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Timestamps extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getConfig().options().copyDefaults();
        saveDefaultConfig();


        File file = new File(Bukkit.getServer().getPluginManager().getPlugin("Timestamps").getDataFolder() + "/playernames.yml");

        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) { }
        }

        TimsLib.setConfig(getConfig().getBoolean("debug_mode"));


        getServer().getPluginManager().registerEvents(new playerchatevent(), this);

        getCommand("say").setExecutor(new serversayevent());

        getCommand("msg").setExecutor(new betterwhisper());

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[TimeStamps]: enabled");
        if(getConfig().getBoolean("debug_mode")) {
            getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "[TimeStamps] DEBUG MODE: enabled");
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[TimeStamps]: disabled");
    }


}

/*
    TODO: Add way to edit colors for Player names ( not only chat )
*/
