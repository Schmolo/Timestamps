package io.github.schmolo.timestamps.events;

import io.github.schmolo.timestamps.util.playernamehelper.PlayerNameHelper;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PlayerChatEvent implements Listener {

    @EventHandler
    public void chatFormat(AsyncPlayerChatEvent event){

        PlayerNameHelper playerNameHelper = PlayerNameHelper.getNamesfromConfig();

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formatDateTime = now.format(formatter);

        String time = "[" + formatDateTime + "] ";


        String playername = null;

        String colon = ChatColor.WHITE + ": ";

        String message = ChatColor.WHITE + event.getMessage();


        String playeruuid = event.getPlayer().getUniqueId().toString();


        playername = ChatColor.WHITE + event.getPlayer().getDisplayName();


        if(playerNameHelper.getFullName(playeruuid) != null) {
            playername = playerNameHelper.getFullName(playeruuid);
        }

        //event.setFormat(time + playername + colon + message);
        event.setCancelled(true);
        Bukkit.broadcastMessage(time + playername + colon + message);
    }
}