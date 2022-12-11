package io.github.schmolo.timestamps;

import io.github.schmolo.timestamps.commands.Whisper;
import io.github.schmolo.timestamps.commands.ChatColor;
import io.github.schmolo.timestamps.commands.SayCommand;
import io.github.schmolo.timestamps.events.*;
import io.github.schmolo.timestamps.util.TimsLib;
import io.github.schmolo.timestamps.util.playernamehelper.PlayerNameHelper;
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

        // Its unused and just there to load the Playernames Hashmap from the config
        PlayerNameHelper.getNamesfromConfig();


        getServer().getPluginManager().registerEvents(new SetListNameOnJoin(), this);

        getServer().getPluginManager().registerEvents(new PlayerChatEvent(), this);

        getCommand("say").setExecutor(new SayCommand());

        getCommand("msg").setExecutor(new Whisper());

        this.getCommand("ChatColor").setExecutor(new ChatColor());

        getServer().getConsoleSender().sendMessage(net.md_5.bungee.api.ChatColor.GREEN + "[TimeStamps]: enabled");
        if(getConfig().getBoolean("debug_mode")) {
            getServer().getConsoleSender().sendMessage(net.md_5.bungee.api.ChatColor.GOLD + "[TimeStamps] DEBUG MODE: enabled");
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        getServer().getConsoleSender().sendMessage(net.md_5.bungee.api.ChatColor.RED + "[TimeStamps]: disabled");
    }


}

/*
    TODO: Change the onTab of /ChatColor behavior to create errors for wrong inputs
    TODO: Add and change some of the colors in the customHexCodes Hashmap
    TODO: Add way to edit colors for Player names ( not only chat )
*/
