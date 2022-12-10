package io.github.schmolo.timestamps;

import io.github.schmolo.timestamps.events.betterwhisper;
import io.github.schmolo.timestamps.events.chatcolor;
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

        // Its unused and just there to load the Playernames Hashmap from the config
        PlayerNameHelper.getNamesfromConfig();



        getServer().getPluginManager().registerEvents(new playerchatevent(), this);

        getCommand("say").setExecutor(new serversayevent());

        getCommand("msg").setExecutor(new betterwhisper());

        this.getCommand("chatcolor").setExecutor(new chatcolor());

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
    TODO: Change the onTab of /chatcolor behavior to create errors for wrong inputs
    TODO: Add and change some of the colors in the customHexCodes Hashmap
    TODO: Add way to edit colors for Player names ( not only chat )
*/
