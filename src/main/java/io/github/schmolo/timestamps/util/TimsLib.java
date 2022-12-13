package io.github.schmolo.timestamps.util;

import net.md_5.bungee.api.ChatColor;


import static org.bukkit.Bukkit.getServer;

public class TimsLib {

    private static boolean debug_mode;

    public static void setConfig(boolean mode) {
        debug_mode = mode;
    }


    public static void PrintDebug(String message) {
        if (debug_mode) {
            getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "[TimeStamps DEBUG] " + message);
        }

    }
}
