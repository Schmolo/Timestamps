package io.github.schmolo.timestamps.commands;

import io.github.schmolo.timestamps.util.playernamehelper.PlayerNameHelper;
import org.bukkit.entity.Player;

import java.util.List;


import static io.github.schmolo.timestamps.util.TimsLib.PrintDebug;

public class SuffixSub extends SubCommand{
    @Override
    public String getName() {
        return "suffix";
    }

    @Override
    public String getDescription() {
        return "set a Suffix";
    }

    @Override
    public String getUsage() {
        return "/ts suffix <suffix> <color>";
    }

    @Override
    public void perform(Player player, String[] args) {

        PlayerNameHelper playerNameHelper = PlayerNameHelper.getInstance();

        for (int i = 0; i < args.length; i++) {
            PrintDebug("args[" + i + "]: " + args[i]);
        }

        String suffixname = args[1];
        String suffixcolor = null;
        if (args.length > 3) suffixcolor = args[2].toLowerCase();

        String playeruuid = player.getUniqueId().toString();

        playerNameHelper.applySuffix(playeruuid, suffixname, suffixcolor);

        String newName = playerNameHelper.getFullName(playeruuid);
        player.setPlayerListName(newName);
    }

    @Override
    public List<String> getArgs(Player player, String[] args) {
        return null;
    }
}
