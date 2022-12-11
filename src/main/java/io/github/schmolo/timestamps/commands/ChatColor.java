package io.github.schmolo.timestamps.commands;

import io.github.schmolo.timestamps.util.StringSplit;
import io.github.schmolo.timestamps.util.playernamehelper.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ChatColor implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("ChatColor")) {

            if (sender instanceof Player) {

                PlayerNameHelper playerNameHelper = PlayerNameHelper.getInstance();

                Player player = (Player) sender;
                String playername = player.getName();
                String uuid = player.getUniqueId().toString();
                // /ChatColor [Static, Gradient, Segmentet] [Static{color}, Gradient{start_color end_color}, Segmentet{Number_of_Segments for Number_of_Segments Color}]

                if (args[0].equalsIgnoreCase("static")) {
                    // /ChatColor static color
                    SingleName singleName = new SingleName(playername, args[1]);
                    // save to yml
                    playerNameHelper.addName(uuid, singleName);


                } else if (args[0].equalsIgnoreCase("gradient")) {
                    // /ChatColor gradient start_color end_color
                    Gradient gradient = new Gradient(args[1], args[2]);
                    GradientName gradientName = new GradientName(playername, gradient);

                    playerNameHelper.addName(uuid, gradientName);


                } else if (args[0].equalsIgnoreCase("segment")) {
                    // /ChatColor segment numberofsegments (for numberofsegments color)
                    int numberOfSegements = Integer.parseInt(args[1]);
                    List<String> chunks = StringSplit.StringSplit(playername, numberOfSegements);
                    List<Segment> segments = new ArrayList<>();

                    for (int i = 0; i != numberOfSegements; i++) {
                        int colorPos = i + 2;
                        Segment segment = new Segment(chunks.get(i), args[colorPos]);
                        segments.add(segment);
                    }
                    SegmentedName segmentedName = new SegmentedName(playername, segments);
                    playerNameHelper.addName(uuid, segmentedName);
                }
                playerNameHelper.updateNames();
                String newName = playerNameHelper.getName(uuid);
                player.setPlayerListName(newName);
            }
            return true;
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1) {
            ArrayList<String> changeTypes = new ArrayList<String>();
            changeTypes.add("static");
            changeTypes.add("gradient");
            changeTypes.add("segment");

            return changeTypes;
        }
        if (args.length > 1) {
            List<String> keys = new ArrayList<>(ColoredName.customHexCodes.keySet());
            return keys;
        }
        return null;
    }
}