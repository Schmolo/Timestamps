package io.github.schmolo.timestamps.events;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class playerchatevent implements Listener {

    @EventHandler
    public void chatFormat(AsyncPlayerChatEvent event){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formatDateTime = now.format(formatter);

        String time = "[" + formatDateTime + "] ";

        String playername = null;

        if (event.getPlayer().isOp()) {

            playername = ChatColor.RED + event.getPlayer().getDisplayName();

        } else {

            playername = ChatColor.WHITE + event.getPlayer().getDisplayName();

        }

        String colon = ChatColor.WHITE + ": ";

        String message = ChatColor.WHITE + event.getMessage();

        event.setFormat(time + playername + colon + message);
    }
}