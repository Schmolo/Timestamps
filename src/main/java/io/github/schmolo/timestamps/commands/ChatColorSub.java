package io.github.schmolo.timestamps.commands;

import io.github.schmolo.timestamps.util.StringSplit;
import io.github.schmolo.timestamps.util.playernamehelper.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ChatColorSub extends SubCommand{
    @Override
    public String getName() {
        return "chatcolor";
    }

    @Override
    public String getDescription() {
        return "Change the Color of your Name.";
    }

    @Override
    public String getUsage() {
        return "/ts chatcolor <[static, gradient, segment]> <[color, start_color, segments]> <[  , end_color, for every segment a color...]>";
    }

    @Override
    public void perform(Player player, String[] args) {
        PlayerNameHelper playerNameHelper = PlayerNameHelper.getInstance();

        String playername = player.getName();
        String uuid = player.getUniqueId().toString();

        if (args[1].equalsIgnoreCase("static")) {
            SingleName singleName = new SingleName(playername, args[2]);
            // save to yml
            playerNameHelper.addName(uuid, singleName);


        } else if (args[1].equalsIgnoreCase("gradient")) {
            Gradient gradient = new Gradient(args[2], args[3]);
            GradientName gradientName = new GradientName(playername, gradient);

            playerNameHelper.addName(uuid, gradientName);


        } else if (args[1].equalsIgnoreCase("segment")) {
            int numberOfSegments = Integer.parseInt(args[2]);
            List<String> chunks = StringSplit.StringSplit(playername, numberOfSegments);
            List<Segment> segments = new ArrayList<>();

            for (int i = 0; i != numberOfSegments; i++) {
                int colorPos = i + 3;
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

    @Override
    public List<String> getArgs(Player player, String[] args) {
        if (args.length == 2) {
            ArrayList<String> changeTypes = new ArrayList<>();
            changeTypes.add("static");
            changeTypes.add("gradient");
            changeTypes.add("segment");

            return changeTypes;
        }
        if (args[1].equals("static")) {
            return new ArrayList<>(ColoredName.customHexCodes.keySet());
        }
        if (args[1].equals("gradient")) {
            if (args.length <= 4) {
                return new ArrayList<>(ColoredName.customHexCodes.keySet());
            }
        }
        if (args[1].equals("segment")) {
            if (args.length == 3) {
                List<String> range = new ArrayList<>();

                for (int i = 1; i <= player.getName().length(); i++) {
                    range.add(String.valueOf(i));
                }

                return range;
            }
            if (args.length <= (Integer.parseInt(args[2]) + 3)) {
                return new ArrayList<>(ColoredName.customHexCodes.keySet());
            }
        }
        return null;
    }
}
