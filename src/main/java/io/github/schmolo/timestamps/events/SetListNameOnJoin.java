package io.github.schmolo.timestamps.events;

import io.github.schmolo.timestamps.util.playernamehelper.PlayerNameHelper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.UUID;

public class SetListNameOnJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        PlayerNameHelper playerNameHelper = PlayerNameHelper.getInstance();

        String newName = playerNameHelper.getName(String.valueOf(uuid));
        // player.setPlayerListName(newName);


    }
}