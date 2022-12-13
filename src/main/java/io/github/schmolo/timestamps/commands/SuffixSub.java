package io.github.schmolo.timestamps.commands;

import io.github.schmolo.timestamps.util.playernamehelper.ColoredName;
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
        return "/ts prefix <suffix> <color>";
    }

    @Override
    public void perform(Player player, String[] args) {

        PlayerNameHelper playerNameHelper = PlayerNameHelper.getInstance();

        for (int i = 0; i < args.length; i++) {
            PrintDebug("args[" + i + "]: " + args[i]);
        }

        String suffixname = args[1];
        String suffixcolor = args[2];

        String playeruuid = player.getUniqueId().toString();

        ColoredName playersname = playerNameHelper.getColoredName(playeruuid);

        playersname.setSuffix(suffixname, suffixcolor);
        playerNameHelper.updateName(playeruuid, playersname);
        //playerNameHelper.playernames.replace(playeruuid, playersname);

        playerNameHelper.updateNames();
        String newName = playerNameHelper.getFullName(playeruuid);
        player.setPlayerListName(newName);
    }

    @Override
    public List<String> getArgs(Player player, String[] args) {
        return null;
    }
}
