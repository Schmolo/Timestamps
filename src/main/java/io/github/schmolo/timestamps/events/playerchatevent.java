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

        if(event.getPlayer().getName().equals("PolyLogic"))
        {
            playername = ChatColor.of("#d4006e") + "Pol" + ChatColor.of("#9b4c94") + "yLo" + ChatColor.of("#0931a5") + "gic";
        }


        String colon = ChatColor.WHITE + ": ";

        String message = ChatColor.WHITE + event.getMessage();

        event.setFormat(time + playername + colon + message);
    }
}