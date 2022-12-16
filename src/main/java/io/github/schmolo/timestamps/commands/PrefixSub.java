package io.github.schmolo.timestamps.commands;

import io.github.schmolo.timestamps.util.playernamehelper.PlayerNameHelper;
import org.bukkit.entity.Player;

import java.util.List;

import static io.github.schmolo.timestamps.util.TimsLib.PrintDebug;

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

        PlayerNameHelper playerNameHelper = PlayerNameHelper.getInstance();

        for (int i = 0; i < args.length; i++) {
            PrintDebug("args[" + i + "]: " + args[i]);
        }

        String prefixname = args[1];
        String prefixcolor = null;
        if (args.length > 2) prefixcolor = args[2].toLowerCase();

        String playeruuid = player.getUniqueId().toString();

        playerNameHelper.applyPrefix(playeruuid, prefixname, prefixcolor);

        String newName = playerNameHelper.getFullName(playeruuid);
        player.setPlayerListName(newName);
    }

    @Override
    public List<String> getArgs(Player player, String[] args) {
        return null;
    }
}
